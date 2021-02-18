package com.udacity.jwdnd.course1.cloudstorage.controllers.home_page;


import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/home/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    /*
    Mappings for File CRUD methods
     */

    @PostMapping("/uploadNewFile")
    public String uploadNewFile(@RequestParam("fileToUpload") MultipartFile mpFile, Model model, Authentication authentication) throws IOException {

        String username = authentication.getName();
        User user = userService.getUser(username);
        String filename = mpFile.getOriginalFilename();

        if (mpFile.isEmpty()) {
            model.addAttribute("emptyFileError", "You did not select a file to upload! Please choose a file.");
        }

        else if(!fileService.isFilenameAvailable(filename)){
            model.addAttribute("duplicateFileError", "A file with the given name already exists! Please rename the file or upload a different file.");
        }

        else{
            fileService.uploadNewFile(mpFile, user.getUserID());
            model.addAttribute("successfulFileUpload", "\"" + filename + "\" has been uploaded successfully!");
        }

        return "result";
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam(value = "id", required = false) Integer fileID, Model model){

        if(fileID > 0){
            model.addAttribute("successfulFileDeletion", "The file has been deleted successfully!");
            fileService.deleteFileByID(fileID);
        }

        else{
            model.addAttribute("fileDeletionError", "The file could not be deleted!");
        }

        return "result";
    }
}
