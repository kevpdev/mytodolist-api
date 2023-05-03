package fr.digitaldragon.mytodolistapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {

    private Long userId;

    private String username;

    private String email;

    @JsonIgnore
    private String password;
}
