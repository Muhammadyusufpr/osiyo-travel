package com.osiyotravel.osiyotravel;

import com.osiyotravel.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class OsiyoTravelApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void contextLoads() {
        System.out.println(MD5Util.getMd5("123456"));
    }

}
