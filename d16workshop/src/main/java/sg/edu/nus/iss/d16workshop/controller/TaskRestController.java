package sg.edu.nus.iss.d16workshop.controller;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.d16workshop.model.Task;
import sg.edu.nus.iss.d16workshop.services.TaskService;

@RestController
@RequestMapping(path = "/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTask(@RequestBody String payload){

        Optional<Task> opt = Task.toTask(payload);

        

        JsonObject taskObj = Json.createObjectBuilder()
            .add("id", opt.getId())
            .add("createTime", opt.getCreateTime())
            .build();

        return ResponseEntity.ok(opt.toString());

    }

    

    
    
}

