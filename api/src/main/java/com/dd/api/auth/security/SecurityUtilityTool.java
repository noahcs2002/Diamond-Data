package com.dd.api.auth.security;

public class SecurityUtilityTool {
    public static boolean assertPasswordRequirements(String password) {
        PasswordRequirements requirements = new PasswordRequirements();

        boolean hasSpecialChar = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (char c : password.toCharArray()) {
            if (requirements.getSpecialCharacters().contains(c)) {
                hasSpecialChar = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
        }

        return hasSpecialChar && hasUpperCase && hasLowerCase && password.length() < requirements.getLengthRequirement();
    }

    public static String protect(String password) {
        return Salt.applyDoubleEndedSalt(Salt.applySalt(password));
    }
}
