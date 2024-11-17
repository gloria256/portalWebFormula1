package master.webapp.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseUtil {
    private String status = ConstantsUtil.SUCCESS;
    private List<ErrorUtil> errors = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorUtil> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorUtil> errors) {
        this.errors = errors;
    }
}
