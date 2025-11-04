package com.plantcare.plantcare_api.utils;

import com.plantcare.plantcare_api.exceptions.SenhaException;

import java.util.regex.Pattern;

public class SenhaUtils {
    private static final Pattern SENHA_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$"
    );

    public static boolean isSenhaValida(String senha) {
        if (senha == null || !SENHA_PATTERN.matcher(senha).matches()) {
            throw new SenhaException("A senha deve ter pelo menos 8 dígitos, uma letra maiúscula e um caractere especial.");
        }
        return true;
    }
}