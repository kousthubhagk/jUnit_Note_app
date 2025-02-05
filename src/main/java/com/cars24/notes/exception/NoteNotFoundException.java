package com.cars24.notes.exception;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(String messsage){
        super(messsage);
    }
}
