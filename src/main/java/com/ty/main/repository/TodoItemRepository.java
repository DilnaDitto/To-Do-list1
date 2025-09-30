package com.ty.main.repository;

import com.ty.main.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a Spring Data repository.
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    // Spring Data JPA provides all the basic CRUD (Create, Read, Update, Delete)
    // methods like findAll(), findById(), save(), deleteById() out of the box.
    // We don't need to write any code here!
}