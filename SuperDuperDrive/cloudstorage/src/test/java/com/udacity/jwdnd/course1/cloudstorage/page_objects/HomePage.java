package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;

    /*
    Attributes associated with:

    *Tabs in the top-bar
    *Links to the home page
     */
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(className = "homeButton")
    private WebElement homeButton;


    //Constructor
    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Methods for navigating between universal links and tabs
    public void logout(){
        logoutButton.click();
    }

    public void goToFiles(){
        filesTab.click();
    }

    public void goToNotes(){
        notesTab.click();
    }

    public void goToCredentials(){
        credentialsTab.click();
    }

    public void goToHome(){
        homeButton.click();
    }

}
