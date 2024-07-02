package carpelune.tasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import carpelune.tasks.models.Task;

@Repository
public interface TasksRepository extends JpaRepository<Task, String>{

}
