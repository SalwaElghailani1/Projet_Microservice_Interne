package faculte.service_interne.service;

import faculte.service_interne.entities.Task;
import faculte.service_interne.entities.TaskStatus;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getTasksByUser(Integer userId);
    Task updateTaskStatus(Integer taskId, TaskStatus status);
}
