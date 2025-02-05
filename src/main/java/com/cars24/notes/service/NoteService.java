package com.cars24.notes.service;

import com.cars24.notes.exception.NoteNotFoundException;
import com.cars24.notes.model.Note;
import com.cars24.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public Note getNoteById(String id){
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with this id not found!"));
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note createNote(Note note){
        return noteRepository.save(note);
    }

    public Note updateNote(String id, Note noteDetails){
        Note note = getNoteById(id);
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setUpdatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public void deleteNoteById(String id){
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }
}
