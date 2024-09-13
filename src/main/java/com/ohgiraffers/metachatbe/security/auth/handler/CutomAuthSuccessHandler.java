package com.ohgiraffers.metachatbe.security.auth.handler;

import com.ohgiraffers.metachatbe.file.MinioService;
import com.ohgiraffers.metachatbe.security.auth.model.DetailsUser;
import com.ohgiraffers.metachatbe.security.common.AuthConstants;
import com.ohgiraffers.metachatbe.security.common.utils.ConvertUtil;
import com.ohgiraffers.metachatbe.security.common.utils.TokenUtils;
import com.ohgiraffers.metachatbe.user.entity.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Configuration
public class CutomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    public MinioService minioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User user  = ((DetailsUser) authentication.getPrincipal()).getUser();
        JSONObject jsonValue = (JSONObject) ConvertUtil.convertObjectToJsonObject(user);

        // Check if userFileName is not null before calling isEmpty()
        if(user.getUserFileName() != null && !user.getUserFileName().isEmpty()){
            try {
                jsonValue.put("fileUrl", minioService.getFileUrl(user.getUserFileName()));
            } catch (Exception e) {
                // Log the exception or handle it appropriately
                e.printStackTrace();
            }
        }

        HashMap<String, Object> responseMap = new HashMap<>();

        JSONObject jsonObject;
        if(user.getState().equals("N")){
            responseMap.put("userInfo", jsonValue);
            responseMap.put("message","휴먼상태인 계정입니다.");
        }else{
            String token = TokenUtils.generateJwtToken(user);
            responseMap.put("userInfo", jsonValue);
            responseMap.put("message", "로그인 성공");
            response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
        }

        jsonObject = new JSONObject(responseMap);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}