package master.webapp.dto;

import lombok.Data;

@Data
public class LoginDtoOut {
    private String accessToken;
    private String tokenType = "Bearer ";

    public LoginDtoOut(String accessToken) {
        this.accessToken = accessToken;
    }
}
