package com.ohgiraffers.metachatbe.filter;

import com.ohgiraffers.metachatbe.summary.command.application.service.AiCommunicationService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.io.PrintWriter;


@Component
@WebFilter(urlPatterns = "/voice")
public class ResponseFilter implements Filter {

    private final AiCommunicationService aiCommunicationService;

    public ResponseFilter(AiCommunicationService aiCommunicationService) {
        this.aiCommunicationService = aiCommunicationService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 로직 (필요할 경우)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // HttpServletResponse를 래핑하여 응답을 가로챕니다.
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");  // 캐릭터셋 설정
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        // 다음 필터 또는 서블릿을 호출합니다.
        chain.doFilter(request, responseWrapper);

        // 응답 데이터를 가져옵니다.
        String responseData = responseWrapper.getCaptureAsString();

        // saveSummary 호출
        aiCommunicationService.saveSummary(responseData);  // 비동기적으로 실행

        // 원래 응답 스트림으로 데이터를 전송합니다.
        PrintWriter out = response.getWriter();
        out.write(responseData);
        out.flush();  // 응답을 클라이언트로 보내기 위해 flush() 호출
    }

    @Override
    public void destroy() {
        // 종료 로직 (필요할 경우)
    }
}

