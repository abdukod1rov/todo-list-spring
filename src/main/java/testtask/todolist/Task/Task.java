package testtask.todolist.Task;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public enum TaskStatus{
        IN_PROGRESS,
        COMPLETED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private  String description;
    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.IN_PROGRESS;
    public Task(Long id, String name, String description, String status, LocalDate creationDate, LocalDate updateDate) {
        this.Id = id;
        this.name = name;
        this.description = description;
        this.status = TaskStatus.valueOf(status);
        this.creationDate = creationDate.atStartOfDay();
        this.updateDate = updateDate.atStartOfDay();
    }


    public Task() {
    }



    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = TaskStatus.valueOf(status);
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate.atStartOfDay();
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate.atStartOfDay();
    }
}
