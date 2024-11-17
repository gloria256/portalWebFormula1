package master.webapp.util;

public class ValidationsUtil {
    public  static boolean emailIsValid(String email) {
        email = email == null ? "" : email;
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(EMAIL_REGEX);
    }
}
