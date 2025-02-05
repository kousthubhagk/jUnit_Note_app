package com.cars24.notes.service;

import com.cars24.notes.exception.NoteNotFoundException;
import com.cars24.notes.model.Note;
import com.cars24.notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/*
* This is just a test for service class - as of now assume nothing is connected to the service class, the service class in this case connects to the repository
* Now to check service class functionality you need to mock the functionality of the db
* */

public class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNoteById_Success(){
        Note note = new Note();
        note.setId("123");
        note.setTitle("Test note");
        //youre setting and telling this test code, from the database how are you getting the data
        when(noteRepository.findById("123")).thenReturn(Optional.of(note));

        //then youre going to check here actually to see if the service class is doing its work properly - if its calling whatever needs to be called then youre verifying according to the mocked database
        Note result = noteService.getNoteById("123");
        assertEquals("Test note", result.getTitle());
    }

    @Test
    public void testGetNoteById_NotFound(){
        when(noteRepository.findById("123")).thenReturn(Optional.empty());
        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById("123"));
    }
}
