package com.osiyotravel.osiyotravel;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootTest
class OsiyoTravelApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("123456"));


    }

}
