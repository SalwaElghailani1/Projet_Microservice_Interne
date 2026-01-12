// faculte.service_interne.repository.TaskRepository
package faculte.service_interne.repository;

import faculte.service_interne.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedTo(Integer userId);
    List<Task> findByStatus(String status);
}