package master.webapp.util;

public class ValidationsUtil {
    public  static boolean emailIsValid(String email) {
        email = email == null ? "" : email;
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(EMAIL_REGEX);
    }

    public  static boolean twitterIsValid(String data) {
        data = data == null ? "" : data;
        String REGEX = "^@[a-zA-Z0-9_.]{1,}$";
        return data.matches(REGEX);
    }
}
