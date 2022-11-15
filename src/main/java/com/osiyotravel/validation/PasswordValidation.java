package com.osiyotravel.validation;


import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {
    private static final String
            PASSWORD_DESIGN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_DESIGN);


    public  static boolean isValid(String password) {
        if (Optional.ofNullable(password).isPresent()) {
            Matcher matcher = PASSWORD_PATTERN.matcher(password);
            return matcher.matches();
        }
        return false;
    }
}
