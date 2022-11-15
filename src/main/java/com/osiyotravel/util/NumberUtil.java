package com.osiyotravel.util;

import java.util.Random;

public class NumberUtil {

    public static String getRandomSmsCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000, 9999));
    }

}
