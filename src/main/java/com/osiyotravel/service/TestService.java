package com.osiyotravel.service;

import com.osiyotravel.entity.ClientEntity;
import com.osiyotravel.entity.FilialEntity;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.FilialStatus;
import com.osiyotravel.enums.Gender;
import com.osiyotravel.enums.ProfileRole;
import com.osiyotravel.repository.ClientRepository;
import com.osiyotravel.repository.FilialRepository;
import com.osiyotravel.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestService {
    private final ClientRepository clientRepository;
    private final ProfileRepository profileRepository;
    private final FilialRepository filialRepository;


    @GetMapping("/init/data")
    public ResponseEntity<?> init() {
        log.info("Start init method");
        try {

            String s = init_Client();
            String profile = init_Profile();
            String s1 = init_Filial(profile);
            return ResponseEntity.ok("Success!");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    public String init_Client() {
        log.info("Init Client");
        ClientEntity client = ClientEntity.builder()
                .name("Vali")
                .surname("Valiev")
                .phone("+998896954875")
                .gender(Gender.MALE)
                .price("1750")
                .build();

        clientRepository.save(client);
        return client.getId();
    }
    public String init_Profile() {
        log.info("Init Profile");

        ProfileEntity profile = ProfileEntity.builder()
                .fullName("Ali Aliev")
                .role(ProfileRole.ROLE_SUPER_ADMIN)
                .phone("+998930047135")
                .username("Ali")
                .password("$2a$10$LtqlnUofO.xA0RzxaXWRK.Xu.kdWlnInqJFcC2l5V50ur.uns0Pnq")
                .build();
        profileRepository.save(profile);
        return profile.getId();
    }
    public String init_Filial(String ownerId) {
        log.info("Init Filial");

        FilialEntity filial = FilialEntity.builder()
                .name("Test")
                .status(FilialStatus.ACTIVE)
                .ownerId(ownerId)
                .build();
        filialRepository.save(filial);
        return filial.getId();
    }

}
