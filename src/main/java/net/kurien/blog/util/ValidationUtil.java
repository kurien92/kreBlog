package net.kurien.blog.util;

public class ValidationUtil {
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

    public static boolean email(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean length(String str, int min, int max) {
        if(str.length() < min) {
            return false;
        }

        if(str.length() > max) {
            return false;
        }

        return true;
    }

    public static boolean password(String password) {
        if(password.length() < 8) {
            return false;
        }

        return true;
    }
}
