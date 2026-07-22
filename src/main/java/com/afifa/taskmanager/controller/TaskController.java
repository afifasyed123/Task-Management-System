package com.afifa.taskmanager.controller;

import com.afifa.taskmanager.entity.Task;
import com.afifa.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String showTaskForm(){
        return  "add-task";
    }
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task){

        service.saveTask(task);
        return "redirect:/tasks";
    }
    @GetMapping
    public String getAllTasks(Model model){

        model.addAttribute("tasks", service.getAllTasks());

        return "tasks";
    }
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){

        service.deleteTask(id);

        return "redirect:/tasks";
    }
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model){

        Task task = service.getTaskById(id);

        model.addAttribute("task", task);

        return "edit-task";
    }
    @GetMapping("/search")
    public String searchTasks(@RequestParam String keyword, Model model) {

        model.addAttribute("tasks", service.searchTasks(keyword));

        return "tasks";
    }
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        System.out.println("Due Date = " + task.getDueDate());
        service.saveTask(task);

        return "redirect:/tasks";
    }
}
