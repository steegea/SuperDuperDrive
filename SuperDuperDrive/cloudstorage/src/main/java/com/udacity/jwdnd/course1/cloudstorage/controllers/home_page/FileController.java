package com.udacity.jwdnd.course1.cloudstorage.controllers.home_page;


import com.udacity.jwdnd.course1.cloudstorage.constants.WebpageMessages;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.juli.WebappProperties;
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

        String successfulFileUploadSuffix = WebpageMessages.SUCCESSFUL_FILE_CREATION_SUFFIX;
        String duplicateFileError = WebpageMessages.DUPLICATE_FILE_ERROR;


        if(!fileService.isFilenameAvailable(filename)){
            model.addAttribute("duplicateFileError", duplicateFileError);
        }

        else{
            fileService.uploadNewFile(mpFile, user.getUserID());
            model.addAttribute("successfulFileUpload", "\"" + filename + "\"" + successfulFileUploadSuffix);
        }

        return "result";
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam(value = "id", required = false) Integer fileID, Model model){

        String successfulFileDeletionMessage = WebpageMessages.SUCCESSFUL_FILE_DELETION_MESSAGE;
        String fileDeletionError = WebpageMessages.FILE_DELETION_ERROR;

        if(fileID > 0){
            model.addAttribute("successfulFileDeletion", successfulFileDeletionMessage);
            fileService.deleteFileByID(fileID);
        }

        else{
            model.addAttribute("fileDeletionError", fileDeletionError);
        }

        return "result";
    }
}
