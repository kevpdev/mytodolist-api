package fr.digitaldragon.mytodolistapi.controller;

import fr.digitaldragon.mytodolistapi.config.security.jwt.JwtUtils;
import fr.digitaldragon.mytodolistapi.config.security.services.UserDetailsImpl;
import fr.digitaldragon.mytodolistapi.model.*;
import fr.digitaldragon.mytodolistapi.repository.RoleRepository;
import fr.digitaldragon.mytodolistapi.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authentificateUser(@Valid @RequestBody SigninRequest signinRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new SigninResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail()));
    }
//
//    @GetMapping
//    public List<User> getAllUsers(){
//        List<User> users = new ArrayList<>();
//        userRepository.findAll().iterator().forEachRemaining(users::add);
//        System.out.println("MY userlist" + users);
//        return users;
//    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){

        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already taken"));
        }else if(userRepository.existsByEmail(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error : userName is already taken"));
        }
        // Create new user's account
        User user = new User(signupRequest.getEmail(),
                signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getDateCreation());

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role user is not found."));
            roles.add(userRole);
        }else {
            strRoles.forEach(role -> {
                logger.info("role : " + role);
                switch (role){
                    case "ROLE_USER" :
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role user is not found."));
                        roles.add(userRole);
                        break;
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));
                        roles.add(adminRole);
                        break;
                }
            });
        }


        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



}
