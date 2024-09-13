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

import java.util.Optional;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MinioService minioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping(value = "/signup",consumes = "application/json",produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {

            // 비밀번호 인코딩
            user.setUserPass(passwordEncoder.encode(user.getUserPass()));
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
    @PutMapping(value =  "/{userId}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateUser(@PathVariable String userId,
                                        @ModelAttribute User updatedUser,
                                        @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        try {
            Optional<User> userOptional = userRepository.findByUserId(userId);
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            User existingUser = userOptional.get();

            // 업데이트할 필드들 설정
            if (updatedUser.getUserName() != null) {
                existingUser.setUserName(updatedUser.getUserName());
            }
            if (updatedUser.getUserEmail() != null) {
                existingUser.setUserEmail(updatedUser.getUserEmail());
            }
            if (updatedUser.getUserPass() != null) {
                existingUser.setUserPass(passwordEncoder.encode(updatedUser.getUserPass()));
            }

            // 새 파일이 업로드된 경우
            if (multipartFile != null && !multipartFile.isEmpty()) {
                // 기존 파일 삭제
                if (existingUser.getUserFileName() != null) {
                    minioService.deleteFile(existingUser.getUserFileName());
                }
                // 새 파일 업로드
                String newFileName = minioService.uploadFile(multipartFile);
                existingUser.setUserFileName(newFileName);
            }

            // 사용자 정보 저장
            User savedUser = userRepository.save(existingUser);

            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }
}
