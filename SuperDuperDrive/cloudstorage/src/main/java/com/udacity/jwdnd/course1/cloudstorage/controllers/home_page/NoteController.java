package com.udacity.jwdnd.course1.cloudstorage.controllers.home_page;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    /*
    Mappings for Note CRUD methods
     */

    @PostMapping("/addOrUpdateNote")
    public String createNote(NoteForm noteForm, Model model, Authentication authentication){

        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userID = user.getUserID();

        Note note = new Note(noteForm.getNoteID(), noteForm.getNoteTitle(),
                noteForm.getNoteDescription(), userID);


        if(note.getNoteID() == 0){
            model.addAttribute("successfulNoteSubmission", "Your note has been added!");
            noteService.addNote(note);
        }

        else if(note.getNoteID() > 0){
            model.addAttribute("successfulNoteEdit", "Your note changes have been saved!");
            noteService.updateNote(note);
        }

        else{
            model.addAttribute("noteSubmissionError", "There was an issue adding your note!");
        }

        return "result";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") int noteID, Model model) {

        if (noteID > 0) {
            model.addAttribute("successfulNoteDeletion", "The note has been successfully deleted!");
            noteService.deleteNote(noteID);

        }

        else{
            model.addAttribute("noteDeletionError", "ERROR: The note could not be deleted!");
        }

        return "result";
    }
}
