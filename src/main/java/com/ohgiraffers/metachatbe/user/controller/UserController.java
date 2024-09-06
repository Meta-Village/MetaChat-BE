package com.ohgiraffers.metachatbe.user.controller;

import com.ohgiraffers.metachatbe.file.MinioService;
import com.ohgiraffers.metachatbe.user.repository.UserRepository;
import com.ohgiraffers.metachatbe.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RestController
@Tag(name = "사용자 관리", description = "사용자 관련 API를 제공하는 컨트롤러입니다.")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MinioService minioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Operation(summary = "회원가입", description = "사용자 정보를 기반으로 회원가입을 처리하고 파일을 업로드할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "500", description = "회원가입 처리 중 오류 발생")
    })
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
