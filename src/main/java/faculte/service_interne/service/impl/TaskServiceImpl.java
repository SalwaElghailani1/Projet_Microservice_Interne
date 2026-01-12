package faculte.service_interne.service.impl;

import faculte.service_interne.entities.Task;
import faculte.service_interne.entities.TaskStatus;
import faculte.service_interne.repository.TaskRepository;
import faculte.service_interne.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByUser(Integer userId) {
        return taskRepository.findByAssignedTo(userId);
    }

    @Override
    public Task updateTaskStatus(Integer taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
}
