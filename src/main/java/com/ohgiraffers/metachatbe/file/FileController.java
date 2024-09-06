package com.ohgiraffers.metachatbe.file;

import io.minio.errors.MinioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@Tag(name = "파일 관리", description = "파일 업로드 및 조회 관련 API를 제공하는 컨트롤러입니다.")
public class FileController {

    @Autowired
    private MinioService minioService;

    @Operation(summary = "파일 업로드", description = "파일을 업로드하고 서버에 저장된 파일의 URL을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일이 성공적으로 업로드되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.", content = @Content)
    })
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadFile(
            @Parameter(description = "업로드할 파일", required = true,
                    content = @Content(mediaType = "multipart/form-data"))
            @RequestParam("file") MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        // 파일 업로드 및 URL 반환 처리
        String fileNames = minioService.uploadFile(file);
        return ResponseEntity.ok(fileNames);
    }

    @Operation(summary = "파일 URL 조회", description = "파일 이름을 기반으로 저장된 파일의 URL을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일의 URL이 성공적으로 반환되었습니다.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "파일을 찾을 수 없습니다.", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.", content = @Content)
    })
    @GetMapping("/url")
    public ResponseEntity<String> getFileUrl(@RequestParam("filename") String filename) {
        try {
            boolean doesFileExist = minioService.doesFileExist(filename);
            if (!doesFileExist) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일을 찾을 수 없습니다.");
            }

            String url = minioService.getFileUrl(filename);
            return ResponseEntity.ok(url);
        } catch (MinioException e) {
            String errorMessage = "MinIO 접근 중 오류 발생: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "파일 URL 생성 중 오류 발생: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
