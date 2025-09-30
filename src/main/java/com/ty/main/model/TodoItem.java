package com.ty.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // Tells JPA that this class is an entity that maps to a database table.
public class TodoItem {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the ID value.
    private Long id;

    private String task;

    private boolean completed;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Ensures the date from the form is parsed correctly.
    private LocalDateTime dueDate;
}