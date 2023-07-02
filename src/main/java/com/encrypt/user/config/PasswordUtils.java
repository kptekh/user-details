package com.encrypt.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Configuration
public class PasswordUtils {

    Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public String decrypt(String encstr) {
        return encoder.matches(encstr, encoder.encode(encstr)) == true ? encstr : null;
    }
}
