package com.udacity.jwdnd.course1.cloudstorage.page_objects.notes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NoteElements {

    /*
    Elements associated with Notes
     */

    //Elements found in the Note modals
    @FindBy(id = "noteTitleInput")
    private WebElement noteTitleInput;

    @FindBy(id = "noteDescriptionInput")
    private WebElement noteDescriptionInput;

    @FindBy(id = "noteSubmitButton")
    private WebElement noteSubmitButton;

    @FindBy(id = "confirmDeleteNoteButton")
    private WebElement confirmDeleteNoteButton;

    //Elements associated with Note CRUD transactions
    @FindBy(id = "successfulNoteSubmission")
    private WebElement successfulNoteSubmission;

    @FindBy(id = "successfulNoteEdit")
    private WebElement successfulNoteEdit;

    @FindBy(id = "successfulNoteDeletion")
    private WebElement successfulNoteDeletion;

    @FindBy(id = "noNotes")
    private WebElement noNotesMessage;


    /*
    Elements displayed on the Notes tab

    Outside of the modals
     */
    @FindBy(id = "addNewNoteButton")
    private WebElement addNewNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "noteTitleOutput")
    private WebElement noteTitleOutput;

    @FindBy(id = "noteDescriptionOutput")
    private WebElement noteDescriptionOutput;

    @FindBy(className = "noteTitleCell")
    private List<WebElement> noteTitles;

    @FindBy(className = "noteDescriptionCell")
    private List<WebElement> noteDescriptions;

    //Constructor
    public NoteElements(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Helper method
    public void fillInNoteModal(String noteTitle, String noteDescription) throws InterruptedException {
        Thread.sleep(1500);

        noteTitleInput.clear();
        noteDescriptionInput.clear();

        noteTitleInput.sendKeys(noteTitle);
        Thread.sleep(1500);
        noteDescriptionInput.sendKeys(noteDescription);
        Thread.sleep(2000);

        noteSubmitButton.submit();
    }

    public void addNoteInfo(String noteTitle, String noteDescription) throws InterruptedException {

        addNewNoteButton.click();
        fillInNoteModal(noteTitle, noteDescription);
    }

    public void editNote(String noteTitle, String noteDescription) throws InterruptedException {
        editNoteButton.click();
        fillInNoteModal(noteTitle, noteDescription);
    }

    public void deleteNote() throws InterruptedException {
        Thread.sleep(1500);

        deleteNoteButton.click();

        /*Thread.sleep(1500);

        confirmDeleteNoteButton.click();*/
    }

    /*
    Methods returning messages corresponding to
    Note CRUD transactions
     */
    public String getSuccessfulNoteSubmissionMessage(){
        return successfulNoteSubmission.getText();
    }

    public String getSuccessfulNoteEditMessage(){
        return successfulNoteEdit.getText();
    }

    public String getSuccessfulNoteDeletionMessage(){
        return successfulNoteDeletion.getText();
    }

    public String getNoNotesBanner(){
        return noNotesMessage.getText();
    }

    //Fetches data displayed on the Notes tab
    public String getFirstNoteTitleCell(){
        return noteTitles.get(0).getText();
    }

    public String getFirstNoteDescriptionCell(){
        return noteDescriptions.get(0).getText();
    }


}
