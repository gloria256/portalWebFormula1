package master.webapp.dto;

import java.util.Optional;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import master.webapp.entidades.UsuarioRegistrado;

@Data
@Getter
@Setter
public class LoginDtoOut {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Optional<UsuarioRegistrado> user;

    public LoginDtoOut(String accessToken, Optional<UsuarioRegistrado> user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
