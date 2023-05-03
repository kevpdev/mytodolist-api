package fr.digitaldragon.mytodolistapi.service.impl;

import fr.digitaldragon.mytodolistapi.dto.TaskDTO;
import fr.digitaldragon.mytodolistapi.model.Task;
import fr.digitaldragon.mytodolistapi.repository.TaskRepository;
import fr.digitaldragon.mytodolistapi.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<TaskDTO> getAllTask() {

        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().iterator().forEachRemaining(tasks::add);
        List<TaskDTO> tasksDTO = new ArrayList<>();
        tasks.forEach(task -> tasksDTO.add(mapper.map(task, TaskDTO.class)));

        return tasksDTO;
    }

    @Override
    public List<TaskDTO> getAllTaskByUserId(Long userId) {

        List<Task> tasks = new ArrayList<>();
        Iterable<Long> ids = Arrays.asList(userId);
        taskRepository.findAllById(ids).iterator().forEachRemaining(tasks::add);
        List<TaskDTO> tasksDTO = new ArrayList<>();
        tasks.forEach(task -> tasksDTO.add(mapper.map(task, TaskDTO.class)));

        return tasksDTO;
    }

    @Override
    public TaskDTO getTask(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        TaskDTO taskResponse = mapper.map(task, TaskDTO.class);
        return taskResponse;
    }

    @Override
    public TaskDTO saveTask(TaskDTO task) {

        Task taskSaved = taskRepository.save(mapper.map(task, Task.class));
        return mapper.map(taskSaved, TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(TaskDTO newTask) {
        Optional<Task> existingTask = taskRepository.findById(newTask.getId());
        if(existingTask.isPresent()){

            existingTask.get().setTitle(newTask.getTitle());
            existingTask.get().setContent(newTask.getContent());
            existingTask.get().setDateCreation(newTask.getDateCreation());
            existingTask.get().setDateModification(newTask.getDateModification());
            existingTask.get().setTaskState(newTask.getTaskState());

            Task taskUpdated = taskRepository.save(existingTask.get());
            return  mapper.map(taskUpdated, TaskDTO.class);

        }
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
