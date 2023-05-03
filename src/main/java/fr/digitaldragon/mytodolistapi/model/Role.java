package fr.digitaldragon.mytodolistapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name="roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
//    public static final String USER = "USER";
//    public static final String ROLE_USER = "ROLE_USER";
//    public static final String ROLE_ADMIN = "ROLE_ADMIN";
//    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

//    // bi-directional many-to-many association to User
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;

    public Role(){

    }
    public Role(ERole name) {
        this.name = name;
    }

}
