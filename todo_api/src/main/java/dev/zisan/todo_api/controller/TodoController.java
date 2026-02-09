package dev.zisan.todo_api.controller;

import dev.zisan.todo_api.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Explore these concepts:
// Bean
// Singleton Pattern
// Dependency Injection

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final List<Todo> todoList = new ArrayList<>();

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Integer id) {
        for (Todo todo : todoList) {
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        todoList.add(todo);
//        return todo;
        return ResponseEntity.status(201).body(todo);
    }

    @PutMapping
    public Todo updateTodo(@RequestBody Todo todo) {
        for (Todo t : todoList) {
            if (t.getId().equals(todo.getId())) {
                t.setTitle(todo.getTitle());
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Integer id) {
        todoList.removeIf(t -> t.getId().equals(id));
    }
}

// GET - all todos
// GET - single todo by id
// POST - create todo
// PUT - update todo
// DELETE - delete todo
