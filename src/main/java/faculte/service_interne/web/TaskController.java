// faculte.service_interne.web.TaskController
package faculte.service_interne.web;

import faculte.service_interne.entities.Task;
import faculte.service_interne.entities.TaskStatus;
import faculte.service_interne.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
@PreAuthorize("hasRole('HOUSEKEEPING') or hasRole('MANAGER') or hasRole('ADMIN')")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<List<Task>> getMyTasks(@AuthenticationPrincipal Jwt jwt) {
        Integer userId = ((Long) jwt.getClaim("userId")).intValue();
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Integer taskId,
            @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, status));
    }
}