package fr.digitaldragon.mytodolistapi.model;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Date dateCreation;
    private Set<String> roles;
}
