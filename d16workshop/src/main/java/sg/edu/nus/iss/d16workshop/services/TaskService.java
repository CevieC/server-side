package sg.edu.nus.iss.d16workshop.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.d16workshop.model.Task;

@Service
public class TaskService {

    public static Optional<Task> saveTask(Task task){

        task.setId(UUID.randomUUID().toString());

        task.setCreateTime(((new Date()).getTime()));

        return Optional.of(task);

    }
    
}
