package dev.zisan.secure_notes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> ping(Authentication auth) {
        return ResponseEntity.ok(((User)auth.getPrincipal()).getAuthorities());
    }
}
