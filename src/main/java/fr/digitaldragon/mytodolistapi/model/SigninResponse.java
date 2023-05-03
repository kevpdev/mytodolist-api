package fr.digitaldragon.mytodolistapi.model;

import lombok.Data;

@Data
public class SigninResponse {

    private Long userId;

    private String username;

    private String email;

    public SigninResponse(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
