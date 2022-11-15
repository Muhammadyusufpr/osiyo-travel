package com.osiyotravel.controller;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ProfileCreateDTO;
import com.osiyotravel.dto.update.ProfileUpdateDTO;
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


    @ApiOperation(value = "Create", notes = "Method used for create Profile only ADMIN")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<ApiResponse<?>> create(@RequestBody @Valid ProfileCreateDTO dto) {
        log.info("Create Profile:{}", dto);
        return ResponseEntity.ok(profileService.create(dto));
    }

    @ApiOperation(value = "Get", notes = "Method used for get profile by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        log.info("Get Profile:{}", id);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @ApiOperation(value = "Update", notes = "Method used for update profile detail")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProfileUpdateDTO dto) {
        log.info("Update profile detail:{}{}", id, dto);
        return ResponseEntity.ok(profileService.update(dto, id));
    }

    @ApiOperation(value = "Delete", notes = "Method used for delete profile by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        log.info("Delete profile:{}", id);
        return ResponseEntity.ok(profileService.delete(id));
    }


}