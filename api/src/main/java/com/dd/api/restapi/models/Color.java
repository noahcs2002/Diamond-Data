package com.dd.api.restapi.models;

public class Color {
    private char[] hexCode;

    public Color() {
        // Empty constructor for Jackson processor.
    }

    public Color(String color) {
        hexCode = new char[6];

        if (color.length()!=6) {
            throw new RuntimeException("Colour Codes must be exactly 6 characters. If you used a pound sign (#fff000) retry without the pound sign.");
        }
        else {
            for (int i = 0; i < color.length(); i += 1) {
                hexCode[i] = color.charAt(i);
            }
        }
    }

    public char[] getHexCode() {
        return hexCode;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(char c : hexCode) {
            builder.append(c);
        }

        return builder.toString();
    }

    public String fullyQualifiedToString() {
        StringBuilder builder = new StringBuilder().append('#');

        for (char c : this.hexCode) {
            builder.append(c);
        }

        return builder.toString();
    }
}