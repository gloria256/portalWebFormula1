package master.webapp.util;

import java.util.List;

public class ConstantsUtil {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    public static final String STATE_PENDING = "Pendiente";
    public static final String STATE_APPROVED = "Aprobado";
    public static final String STATE_REJECTED = "Rechazado";
    public static final List<String> states = List.of(STATE_PENDING, STATE_APPROVED, STATE_REJECTED);
}