package com.ty.p1.controller;

import com.ty.p1.model.Todo;
import com.ty.p1.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        long completedTasks = todos.stream().filter(Todo::isCompleted).count();
        
        model.addAttribute("todos", todos);
        model.addAttribute("totalTasks", todos.size());
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("pendingTasks", todos.size() - completedTasks);
        
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam("task") String task) {
        if (task != null && !task.trim().isEmpty()) {
            todoService.saveTodo(new Todo(task));
        }
        return "redirect:/";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateTodoStatus(@PathVariable("id") Long id) {
        Optional<Todo> optionalTodo = todoService.getTodoById(id);
        if (optionalTodo.isPresent()) {
            boolean newStatus = !optionalTodo.get().isCompleted();
            todoService.updateTodoStatus(id, newStatus);
        }
        return "redirect:/";
    }
    
    // Make sure you have the edit methods if you are using them
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        todoService.getTodoById(id).ifPresent(todo -> model.addAttribute("todo", todo));
        return "edit_task";
    }

    @PostMapping("/updateTask")
    public String updateTask(@RequestParam("id") Long id, @RequestParam("task") String task) {
        todoService.updateTaskText(id, task);
        return "redirect:/";
    }
}