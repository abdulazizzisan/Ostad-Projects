package dev.zisan.repository;

import dev.zisan.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNoteByUserId(Long userId);

    Long id(Long id);
}
