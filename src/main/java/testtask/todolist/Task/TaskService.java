package testtask.todolist.Task;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.todolist.Task.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(Long taskId){
        return Optional.ofNullable(taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId)));
    }
    public Task addTask(Task task){
        taskRepository.save(task);
        System.out.println("Task saved!");
        return task;
    }

    public void deleteTask(Long taskId){
        boolean exists = taskRepository.existsById(taskId);
        if (!exists){
            throw new TaskNotFoundException(taskId);
        }
        taskRepository.deleteById(taskId);
        System.out.println("Task deleted!");
    }
    @Transactional
    public Task updateTask(Long taskId, String name, String description){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (name != null && !Objects.equals(task.getName(), name)){
            task.setName(name);
        }
        if (description != null && !Objects.equals(task.getDescription(), description)){
            task.setDescription(description);
         }
        System.out.println("Task updated!");
        taskRepository.save(task);
        return task;
    }


}
