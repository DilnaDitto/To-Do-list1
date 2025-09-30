package com.ty.main.controller;

import com.ty.main.model.TodoItem;
import com.ty.main.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Marks this as a Spring MVC controller.
public class TodoController {

    @Autowired
    private TodoItemService todoItemService;

    // Display the main to-do list page
    @GetMapping("/")
    public String showTodoList(Model model) {
        model.addAttribute("todoItems", todoItemService.getAllTodoItems());
        return "index"; // Returns the index.html template
    }

    // Show the form to add a new task
    @GetMapping("/new")
    public String showNewTodoForm(Model model) {
        model.addAttribute("todoItem", new TodoItem());
        return "form"; // Returns the form.html template
    }

    // Process the form for saving a new or updated task
    @PostMapping("/save")
    public String saveTodoItem(@ModelAttribute("todoItem") TodoItem todoItem) {
        todoItemService.saveTodoItem(todoItem);
        return "redirect:/"; // Redirects to the main list page
    }

    // Show the form to edit an existing task
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TodoItem todoItem = todoItemService.getTodoItemById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid todo item Id:" + id));
        model.addAttribute("todoItem", todoItem);
        return "form"; // Re-uses the same form for editing
    }

    // Delete a task
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
        return "redirect:/";
    }

    // Toggle the 'completed' status of a task
    @GetMapping("/toggle/{id}")
    public String toggleCompleted(@PathVariable Long id) {
        TodoItem todoItem = todoItemService.getTodoItemById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid todo item Id:" + id));
        todoItem.setCompleted(!todoItem.isCompleted()); // Flip the boolean value
        todoItemService.saveTodoItem(todoItem);
        return "redirect:/";
    }
}