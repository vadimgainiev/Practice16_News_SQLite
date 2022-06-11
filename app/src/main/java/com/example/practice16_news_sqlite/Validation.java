package com.example.practice16_news_sqlite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final Pattern VALID_LOGIN_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._-]{3,}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&*^%]).{6,}$");

    public boolean validate_user(User toValidate) {
        return validate_login(toValidate.Login)
                && validate_password(toValidate.Password);
    }

    public boolean validate_login(String login) {
        Matcher matcher = VALID_LOGIN_PATTERN.matcher(login);
        return matcher.find();
    }

    public boolean validate_password(String password) {
        Matcher matcher = VALID_PASSWORD_PATTERN.matcher(password);
        return matcher.find();
    }
}
