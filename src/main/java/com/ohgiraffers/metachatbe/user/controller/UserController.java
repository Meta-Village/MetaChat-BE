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

import java.util.Objects;
import java.util.Optional;

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
