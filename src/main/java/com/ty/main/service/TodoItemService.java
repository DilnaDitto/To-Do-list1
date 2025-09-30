package com.ty.main.service;

import com.ty.main.model.TodoItem;
import com.ty.main.repository.TodoItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Marks this as a service component.
public class TodoItemService {

    @Autowired // Spring automatically injects an instance of TodoItemRepository.
    private TodoItemRepository todoItemRepository;

    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> getTodoItemById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem saveTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public void deleteTodoItem(Long id) {
        todoItemRepository.deleteById(id);
    }
}