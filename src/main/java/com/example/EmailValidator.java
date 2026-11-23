package com.example;

public class EmailValidator {
    // Регулярное выражение:
    // ^[^\\s@]+  -> начало строки, один и более символов, не пробел и не @
    // @          -> ровно один символ @
    // [^\\s@]+   -> один и более символов, не пробел и не @ (имя домена)
    // \\.[^\\s@]+$ -> точка и один и более символов до конца строки (зона, например com, ru)
    private static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

    public boolean isValid(String email) {
        if(email == null || email.isEmpty()) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}
