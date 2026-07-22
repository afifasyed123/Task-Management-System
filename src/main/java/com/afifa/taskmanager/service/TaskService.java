package com.afifa.taskmanager.service;


import com.afifa.taskmanager.entity.Task;
import com.afifa.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }
    public Task saveTask(Task task){

        System.out.println("Saving task: " + task.getTitle());

        Task saved = repository.save(task);

        System.out.println("Saved ID: " + saved.getId());

        return saved;
    }
    public List<Task> getAllTasks(){
        return repository.findAll();
    }
    public List<Task> searchTasks(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public void deleteTask(Long id){
        repository.deleteById(id);
    }
    public Task getTaskById(Long id){
        return repository.findById(id).orElse(null);
    }
}
