package com.osiyotravel.controller;


import com.osiyotravel.dto.AttachDTO;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.service.AttachService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/attach")
@RequiredArgsConstructor
@Api(tags = "Attach Controller 📄")
public class AttachController {

    private final AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<List<AttachDTO>>> create(@RequestParam() Map<String,MultipartFile> file) {
        log.info("upload attach ");
        return ResponseEntity.ok(attachService.upload(file));
    }


    @GetMapping(value = "/open/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        log.info("open_general");
        return attachService.open_general(fileName);
    }


    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("fileName") String fileName) {
        log.info("Delete attach = {}", fileName);
        return ResponseEntity.ok(attachService.delete(fileName));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageImpl<AttachDTO>>> pagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                       @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("Pagination List attach");
        return ResponseEntity.ok(attachService.getPaginationList(page, size));
    }
}
