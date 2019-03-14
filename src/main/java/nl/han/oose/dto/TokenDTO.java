package nl.han.oose.dto;

public class TokenDTO {

    private String token;
    private String user;

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public TokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
