package testtask.todolist.Task;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testtask.todolist.Task.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todos")
public class TaskController {
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public List<Task> getTasks() {       // Retrieves all the tasks the user has created with default 2 tasks
        return taskService.getTasks();
    }

    @PostMapping
    public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        Task createdTask = taskService.addTask(task);  // Endpoint for creating a new task
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping(path = "{taskId}")   // Get the specified task by giving the id
    public ResponseEntity<?> getTask(@PathVariable("taskId") Long taskId) {
        try {
            Optional<Task> task = taskService.getTask(taskId);
            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            throw new TaskNotFoundException(taskId);
        }
    }

    @DeleteMapping(path = "{taskId}")   // Delete any task with the id
    public ResponseEntity.BodyBuilder deleteTaskById(@PathVariable("taskId") Long taskId) {
        try {


            taskService.deleteTask(taskId);
            return ResponseEntity.status(HttpStatus.ACCEPTED);
        } catch (TaskNotFoundException exception){
            throw exception;
        }
    }

    @PutMapping(path = "{taskId}")   // For editing the tasks
    public ResponseEntity<?> updateTask(
            @PathVariable("taskId") Long taskId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description
    ) {
        try {
            Task task = taskService.updateTask(taskId, name, description);
            return ResponseEntity.ok(task);

        }catch (EntityNotFoundException exception){
            throw new RuntimeException();
        }

    }


    }



