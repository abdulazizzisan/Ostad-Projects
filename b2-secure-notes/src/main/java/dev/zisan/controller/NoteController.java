package dev.zisan.controller;

import dev.zisan.entity.Note;
import dev.zisan.exception.CustomException;
import dev.zisan.payload.NoteRequest;
import dev.zisan.repository.NoteRepository;
import dev.zisan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository repository;

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteRequest request) {
        Note note = repository.save(
                Note.builder()
                        .note(request.getNote())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Note not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody NoteRequest request) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new CustomException("Note not found with id: " + id));

        note.setNote(request.getNote());
        Note updatedNote = repository.save(note);

        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new CustomException("Note not found with id: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Note>> getNotesByUser(@PathVariable Long userId) {
//        return ResponseEntity.ok(repository.findByUserId(userId));
//    }
}
