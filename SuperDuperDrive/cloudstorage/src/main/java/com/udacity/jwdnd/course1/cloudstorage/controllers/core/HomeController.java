package com.udacity.jwdnd.course1.cloudstorage.controllers.core;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.security_services.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;
    private UserService userService;
    private EncryptionService encryptionService;


    public HomeController(FileService fileService,
                          NoteService noteService,
                          CredentialService credentialService,
                          UserService userService,
                          EncryptionService encryptionService) {

        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    /*
    Methods that bind the note and credential forms to the model
     */
    @ModelAttribute("noteForm")
    public NoteForm getNoteForm() {
        return new NoteForm();
    }

    @ModelAttribute("credentialForm")
    public CredentialForm getCredentialForm(){
        return new CredentialForm();
    }

    @GetMapping()
    public String getHomePage(Authentication auth, Model model) {

        User user = userService.getUser(auth.getName());
        int userID = user.getUserID();

        List<File> files = fileService.getAllFiles(userID);
        List<Note> notes = noteService.getAllNotes(userID);
        List<Credential> credentials = credentialService.getAllCredentials(userID);

        model.addAttribute("encryptionService", encryptionService);

        if(files.size() == 0 || notes.size() == 0 || credentials.size() == 0){
            if(files.size() == 0){
                model.addAttribute("noFiles", "You do not have any files.");
            }

            if(notes.size() == 0){
                model.addAttribute("noNotes", "You do not have any notes.");
            }

            if(credentials.size() == 0){
                model.addAttribute("noCredentials", "You do not have any credential entries.");
            }
        }

        if(files.size() > 0){
            model.addAttribute("files", files);
            model.addAttribute("showFileTable", true);
        }

        if(notes.size() > 0){
            model.addAttribute("notes", notes);
            model.addAttribute("showNoteTable", true);
        }

        if(credentials.size() > 0){
            model.addAttribute("credentials", credentials);
            model.addAttribute("showCredentialTable", true);

        }

        model.addAttribute("loggedInUser", auth.getName());

        return "home";
    }



}