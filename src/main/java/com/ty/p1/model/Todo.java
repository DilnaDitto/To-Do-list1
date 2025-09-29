package com.ty.p1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

// All Lombok annotations have been removed
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;

    private boolean completed = false;

    // 1. No-argument constructor (required by JPA)
    public Todo() {
    }

    // 2. Convenience constructor for creating new tasks
    public Todo(String task) {
        this.task = task;
    }
    
    // 3. Constructor with all arguments
    public Todo(Long id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
    }

    // 4. Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 5. toString(), equals(), and hashCode() methods (good practice)
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return completed == todo.completed && Objects.equals(id, todo.id) && Objects.equals(task, todo.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, completed);
    }
}