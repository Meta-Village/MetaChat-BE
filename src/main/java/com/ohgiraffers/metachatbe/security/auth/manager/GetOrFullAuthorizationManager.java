package com.ohgiraffers.metachatbe.security.auth.manager;

import com.ohgiraffers.metachatbe.security.auth.service.WebSecurityService;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class GetOrFullAuthorizationManager implements AuthorizationManager<AuthorizationContext> {

    private final WebSecurityService webSecurityService;

    public GetOrFullAuthorizationManager(WebSecurityService webSecurityService) {
        this.webSecurityService = webSecurityService;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, AuthorizationContext context) {
        Authentication authentication = authenticationSupplier.get();
        if (authentication == null) {
            return new AuthorizationDecision(false);
        }

        String method = context.getExchange().getRequest().getMethod().name();
        String userIdParam = context.getExchange().getRequest().getQueryParams().getFirst("userid");

        Long currentUserId = webSecurityService.getCurrentUserId(authentication);

        boolean granted;

        if (userIdParam != null) {
            Long userId = Long.parseLong(userIdParam);

            if (userId.equals(currentUserId)) {
                granted = true;  // 모든 요청(GET, POST, PUT, DELETE 등) 허용
            } else {
                granted = "GET".equalsIgnoreCase(method);  // GET 요청만 허용
            }
        } else {
            granted = "GET".equalsIgnoreCase(method);  // 기본적으로 GET 요청만 허용
        }

        return new AuthorizationDecision(granted);
    }



}
