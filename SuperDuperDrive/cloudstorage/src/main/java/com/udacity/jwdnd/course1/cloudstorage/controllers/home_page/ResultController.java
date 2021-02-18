package com.udacity.jwdnd.course1.cloudstorage.controllers.home_page;

import com.udacity.jwdnd.course1.cloudstorage.services.home_page.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
Controller for displaying any success, error, or no data messages
 */
@Controller
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private FileService fileService;


    @GetMapping
    public String getResult() {
        return "result";

    }

}
