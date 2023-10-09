package testtask.todolist.Task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskConfig {
    @Bean
    CommandLineRunner commandLineRunner(TaskRepository taskRepository) {
        return args -> {
            Task todo1 = new Task(
                    "Go to gym",
                    "Today, we must go to Freedom gym at 7pm"

            );
            Task todo2 = new Task(
                    "Read a book",
                    "Read 7 Habits of High Successful people"
            );
            taskRepository.saveAll(
                    List.of(todo1, todo2)
            );
        };
    }

}
