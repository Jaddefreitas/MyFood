package myfood.framework;

public class Email {
    public static boolean isValid(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }
        if (parts[0].isEmpty() || parts[1].isEmpty()) {
            return false;
        }
        if (!parts[1].contains(".")) {
            return false;
        }
        return true;
    }
}
