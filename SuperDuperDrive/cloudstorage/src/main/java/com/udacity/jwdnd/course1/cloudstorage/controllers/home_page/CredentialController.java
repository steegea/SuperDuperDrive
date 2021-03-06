package com.udacity.jwdnd.course1.cloudstorage.controllers.home_page;

import com.udacity.jwdnd.course1.cloudstorage.constants.WebpageMessages;
import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.home_page.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.security_services.HashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home/credentials")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(CredentialController.class);

    /*
    Mappings for Credential CRUD methods
     */
    @PostMapping("/addOrUpdateCredentialEntry")
    public String createCredentials(CredentialForm credentialForm, Model model, Authentication authentication) {

        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userID = user.getUserID();

        String successfulCredentialCreationMessage = WebpageMessages.SUCCESSFUL_CREDENTIAL_CREATION_MESSAGE;
        String successfulCredentialEditMessage = WebpageMessages.SUCCESSFUL_CREDENTIAL_EDIT_MESSAGE;
        String credentialSubmissionErrorMessage = WebpageMessages.CREDENTIAL_SUBMISSION_ERROR;

        Credential credential = new Credential(credentialForm.getCredentialID(), credentialForm.getUrl(),
                credentialForm.getUsername(), credentialForm.getKey(), credentialForm.getPassword(), userID);

        if(credential.getCredentialID() == 0){
            model.addAttribute("successfulCredentialSubmission", successfulCredentialCreationMessage);
            credentialService.addCredential(credential);
        }

        else if(credential.getCredentialID() > 0){
            model.addAttribute("successfulCredentialEdit", successfulCredentialEditMessage);
            credentialService.updateCredential(credential);
        }

        else{
            model.addAttribute("credentialSubmissionError", credentialSubmissionErrorMessage);
        }


        return "result";
    }

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam("id") int credentialID, Model model) {

        String successfulCredentialDeletionMessage = WebpageMessages.SUCCESSFUL_CREDENTIAL_DELETION_MESSAGE;
        String credentialDeletionErrorMessage = WebpageMessages.CREDENTIAL_DELETION_ERROR;

        if (credentialID > 0) {
            model.addAttribute("successfulCredentialDeletion", successfulCredentialDeletionMessage);
            credentialService.deleteCredential(credentialID);
        }

        else{
            model.addAttribute("credentialDeletionError", credentialDeletionErrorMessage);
        }

        return "result";
    }
}
