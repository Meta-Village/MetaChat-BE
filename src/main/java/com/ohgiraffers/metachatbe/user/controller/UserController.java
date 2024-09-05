package com.ohgiraffers.metachatbe.user.controller;

import com.ohgiraffers.metachatbe.file.MinioService;
import com.ohgiraffers.metachatbe.user.repository.UserRepository;
import com.ohgiraffers.metachatbe.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MinioService minioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping(value = "/signup", consumes = "multipart/form-data")
    public ResponseEntity<?> signup(@ModelAttribute User user,
                                    @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        try {

            // 비밀번호 인코딩
            user.setUserPass(passwordEncoder.encode(user.getUserPass()));

            // 파일 업로드 처리
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String fileName = minioService.uploadFile(multipartFile);
                user.setUserFileName(fileName);
            }

            // 사용자 저장
            User savedUser = userRepository.save(user);

            if (savedUser != null) {
                return ResponseEntity.ok("회원가입 성공");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
