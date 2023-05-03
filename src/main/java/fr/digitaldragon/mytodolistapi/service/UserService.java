package fr.digitaldragon.mytodolistapi.service;

import fr.digitaldragon.mytodolistapi.dto.UserDTO;
import fr.digitaldragon.mytodolistapi.model.User;

public interface UserService {

    public UserDTO saveUser(User user);

    public UserDTO getUser();
}
