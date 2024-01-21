package com.dd.api.auth.security;

import java.util.ArrayList;
import java.util.List;

public class PasswordRequirements {

    private final ArrayList<Character> specialCharacters;
    private  int lengthRequirement;
    private boolean requireUppercase;
    private boolean requireSpecialCharacter;

    public PasswordRequirements() {
        Character[] arr = new Character[]{'!','@','#','$','%','^','&','*','(',')','-','=','+','_','[','{',']','}','<','>','?','/','|','\\'};

        specialCharacters = new ArrayList<>(List.of(arr));
        this.lengthRequirement = 8;
        this.requireUppercase = true;
        this.requireSpecialCharacter = true;
    }

    public void disableRequirements() {
        this.lengthRequirement = 0;
        this.requireUppercase = false;
        this.requireSpecialCharacter = false;
    }

    public void enableRequirements(int lengthRequirement) {
        this.lengthRequirement = lengthRequirement;
        this.requireUppercase = true;
        this.requireSpecialCharacter = true;
    }

    public ArrayList<Character> getSpecialCharacters() {
        return specialCharacters;
    }

    public int getLengthRequirement() {
        return lengthRequirement;
    }
}