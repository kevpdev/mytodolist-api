package fr.digitaldragon.mytodolistapi.service;

import fr.digitaldragon.mytodolistapi.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    public List<TaskDTO> getAllTask();


    public List<TaskDTO> getAllTaskByUserId(Long userId);

    public TaskDTO getTask(Long taskId);

    public TaskDTO saveTask(TaskDTO task);


    public TaskDTO updateTask(TaskDTO newTask);

    public void deleteTask(Long taskId);

}
