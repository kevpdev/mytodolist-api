package fr.digitaldragon.mytodolistapi.controller;

import fr.digitaldragon.mytodolistapi.dto.TaskDTO;
import fr.digitaldragon.mytodolistapi.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTask(){

        return new ResponseEntity(taskService.getAllTask(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> getAllTaskByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity(taskService.getAllTaskByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> getTask(@PathVariable("id") Long taskId){
        return new ResponseEntity(taskService.getTask(taskId), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> saveTask(@RequestBody TaskDTO newTask){
        return new ResponseEntity(taskService.saveTask(newTask), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> updateTask(@RequestBody TaskDTO newTask){
        return new ResponseEntity(taskService.updateTask(newTask), HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity("Task successfully deleted", HttpStatus.OK);
    }
}
