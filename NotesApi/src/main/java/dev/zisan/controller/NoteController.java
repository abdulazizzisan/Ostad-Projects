package dev.zisan.controller;

import dev.zisan.entity.Note;
import dev.zisan.repository.NoteRepository;
import dev.zisan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Note> allNotes(Authentication auth) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String username = auth.getName();

        Long uid = userRepository.findUserByUsername(username)
                .map(u -> u.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepository.findNoteByUserId(uid);
    }


    @PostMapping
    public Note create(@RequestBody Note note, Authentication auth) {

        String username = auth.getName();

        Long uid = userRepository.findUserByUsername(username)
                .map(u -> u.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUserId(uid);

        return noteRepository.save(note);
    }

    @PutMapping
//    @PreAuthorize("hasRole('USER')")
    public Note update(@RequestBody Note note, Authentication auth) {

        String username = auth.getName();

        Long uid = userRepository.findUserByUsername(username)
                .map(u -> u.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note noteToUpdate = noteRepository.findById(note.getId())
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!noteToUpdate.getUserId().equals(uid)) {
            throw new RuntimeException("You cannot modify someone else's note");
        }

        if (note.getTitle() != null) {
            noteToUpdate.setTitle(note.getTitle());
        }
        if (note.getContent() != null) {
            noteToUpdate.setContent(note.getContent());
        }
        return noteRepository.save(noteToUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();

        Long uid = userRepository.findUserByUsername(username)
                .map(u -> u.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note noteToDelete = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!noteToDelete.getUserId().equals(uid)) {
            throw new RuntimeException("You cannot delete someone else's note");
        }
        noteRepository.deleteById(id);

    }

    @GetMapping
    public String hello() {
        Note note = new Note(
                null,
                "Sample Note",
                "This is a sample note content.",
                null
        );

        Note smartNote = Note.builder()
                .content("Thsi is a smart note content")
                .title("sdhfowieurfu ")
                .build();

        return "Hello, Notes!";
    }
}
