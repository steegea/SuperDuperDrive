package com.udacity.jwdnd.course1.cloudstorage.services.home_page;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/*
Service class that implements Note mapper methods
 */
@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public List<Note> getAllNotes(Integer userID){
        List<Note> notes = noteMapper.getNotesByUserID(userID);

        return notes;
    }

    public void addNote(Note note) {
        note.setNoteID(noteMapper.getLatestNoteID());
        noteMapper.insertNote(note);
    }

    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }

    public void deleteNote(int noteid) {
        noteMapper.deleteNote(noteid);
    }

    public boolean noteExists(Note note){
        logger.info(String.valueOf(noteMapper.getNoteById(note.getNoteID()) != null));

        return noteMapper.getNoteById(note.getNoteID()) != null;
    }
}
