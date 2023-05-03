package fr.digitaldragon.mytodolistapi.model;

import lombok.Data;

@Data
public class SigninRequest {

    private String username;
    private String password;
}
