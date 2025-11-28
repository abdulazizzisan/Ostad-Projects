package dev.zisan.controller;

import dev.zisan.entity.Note;
import dev.zisan.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final NoteRepository noteRepository;

    @GetMapping("{userId}")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable Long userId) {
        return ResponseEntity.ok(
                noteRepository.findNoteByUserId(userId)
        );
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(
                noteRepository.findAll()
        );
    }

    @DeleteMapping("{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable Long noteId) {
        noteRepository.deleteById(noteId);
        return ResponseEntity.ok("Note deleted successfully");
    }

    // Role Based Access Control


}
