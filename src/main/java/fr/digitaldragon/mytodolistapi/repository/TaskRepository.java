package fr.digitaldragon.mytodolistapi.repository;

import fr.digitaldragon.mytodolistapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


}
