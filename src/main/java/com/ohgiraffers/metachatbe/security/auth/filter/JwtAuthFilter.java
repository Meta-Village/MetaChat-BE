package com.ohgiraffers.metachatbe.security.auth.filter;

import com.ohgiraffers.metachatbe.security.common.utils.JwtUtil;
import com.ohgiraffers.metachatbe.security.auth.service.CustomUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class JwtAuthFilter implements WebFilter {

    private final CustomUserService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public JwtAuthFilter(CustomUserService customUserDetailsService, JwtUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // JWT가 헤더에 있는 경우
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            // JWT 유효성 검증
            if (jwtUtil.validateToken(token)) {
                Long userId = jwtUtil.getUserId(token);
                String email = jwtUtil.getUserEmail(token);

                return customUserDetailsService.findByUsername(email)
                        .flatMap(userDetails -> {
                            UsernamePasswordAuthenticationToken authToken =
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                            SecurityContext securityContext = new SecurityContextImpl(authToken);
                            // SecurityContext 설정
                            return chain.filter(exchange)
                                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)))
                                    .doOnSuccess(aVoid -> exchange.getAttributes().put("userId", userId)); // Request 속성에 userId 추가
                        });
            }
        }

        return chain.filter(exchange); // 다음 필터로 넘기기
    }
}
