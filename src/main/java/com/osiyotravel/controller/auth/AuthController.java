package com.osiyotravel.controller.auth;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ProfileAuthDTO;
import com.osiyotravel.dto.response.ProfileResDTO;
import com.osiyotravel.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = "Auth ✅")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

   /* @PutMapping("/client/password")
    @ApiOperation(value = "Update Password", notes = "Method used for update client password only PROFILE")
    public ResponseEntity<ApiResponse<?>> updatePassword(@RequestBody UpdatePasswordDTO dto,
                                                         @RequestHeader(value = "Accept-Language",
                                                                 defaultValue = "uz") LanguageEnum lang) {
        String profileId = EntityDetails.getId();
        log.info("Password Update id = {}", profileId);
        return ResponseEntity.ok(authService.updatePassword(profileId, dto, lang));
    }

    @PostMapping("/client/forgot/password")
    @ApiOperation(value = "Forgot Password", notes = "Method used for forgot client password only PROFILE")
    public ResponseEntity<ApiResponse<?>> forgotPassword(@RequestBody ForgotPasswordDTO dto,
                                                         @RequestHeader(value = "Accept-Language",
                                                                 defaultValue = "uz") LanguageEnum lang) {
        log.info("Password forgot id");
        return ResponseEntity.ok(authService.forgotPassword(dto, lang));
    }*/

    /*@PostMapping("/client/forgot/password/verification")
    @ApiOperation(value = "Verify Password", notes = "Method used for verify client password only PROFILE")
    public ResponseEntity<ApiResponse<?>> verifyPassword(@RequestBody VerifyPasswordDTO dto,
                                                         @RequestHeader(value = "Accept-Language",
                                                                 defaultValue = "uz") LanguageEnum lang) {
        log.info("Password verify");
        return ResponseEntity.ok(authService.verifyPassword(dto, lang));
    }
    @GetMapping("/client/reflesh-token")
    @ApiOperation(value = "Reflesh token client", notes = "Method used for reflesh token Only client")
    public ResponseEntity<ApiResponse<?>> refreshTokenProfile(@RequestHeader(value = "Accept-Language",
            defaultValue = "uz") LanguageEnum lang) {
        String id = EntityDetails.getId();
        log.info("Refresh client token = {}", id);
        return ResponseEntity.ok(authService.refreshTokenProfile(id, lang));
    }*/

    /**
     * Profile
     */

    @PostMapping("/profile/login")
    @ApiOperation(value = "Login Branch employee", notes = "Method used for login")
    public ResponseEntity<ApiResponse<ProfileResDTO>> login(@RequestBody @Valid ProfileAuthDTO dto) {
        log.info("Login employee dto = {}", dto);
        return ResponseEntity.ok(authService.login(dto));
    }


}
