package com.dd.api.auth.security;

public class Salt {
    private static final String mainSalt = "olep09kjsdnb56gf";
    private static final String leftEndedSalt = "93kldfmnchbse34y";
    private static final String rightEndedSalt = "9lopiuredfjhyn87";

    public static String applySalt(String text) {
        return text + mainSalt;
    }

    public static String applyDoubleEndedSalt(String text) {
        return leftEndedSalt + text + rightEndedSalt;
    }
}
