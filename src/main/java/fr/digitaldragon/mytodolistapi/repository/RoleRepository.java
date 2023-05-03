package fr.digitaldragon.mytodolistapi.repository;

import fr.digitaldragon.mytodolistapi.model.ERole;
import fr.digitaldragon.mytodolistapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
