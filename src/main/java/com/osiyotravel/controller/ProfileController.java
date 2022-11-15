package com.osiyotravel.controller;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ProfileCreateDTO;
import com.osiyotravel.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/profile")
@Slf4j
@Api(tags = "Profile Controller 👥")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;


    @PostMapping("")
    @ApiOperation(value = "Create", notes = "Method used for create Profile only ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody @Valid ProfileCreateDTO dto) {
        log.info("Create Profile:{}", dto);
        return ResponseEntity.ok(profileService.create(dto));
    }

}
