package com.udacity.jwdnd.course1.cloudstorage.page_objects.files;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileElements {

    /*
    Elements associated with Files
     */

    //Elements displayed on the Files tab
    @FindBy(id = "fileToUpload")
    private WebElement fileToUploadButton;

    @FindBy(id = "uploadNewFileButton")
    private WebElement uploadNewFileButton;

    @FindBy(id = "deleteFileButton")
    private WebElement deleteFileButton;

    @FindBy(id = "viewFileButton")
    private WebElement viewFileButton;

    @FindBy(className = "filenames")
    private List<WebElement> filenames;

    //Button in the Delete File Modal
    @FindBy(id = "confirmDeleteFileButton")
    private WebElement confirmDeleteFileButton;

    //Elements associated with File CRUD transactions
    @FindBy(id = "successfulFileUpload")
    private WebElement successfulFileUpload;

    @FindBy(id = "successfulFileDeletion")
    private WebElement successfulFileDeletion;

    @FindBy(id ="duplicateFileError")
    private WebElement duplicateFileUploadError;

    @FindBy(id = "noFiles")
    private WebElement noFilesMessage;

    //Variables used for File tests
    private final String homeProperty = System.getProperty("user.home");
    private final String fileDownloadDirectory = System.getProperty("user.dir");

    private String filename = "HelloWorld.txt";
    private final String uploadFilePath = homeProperty + File.separator + "Documents" + File.separator + filename;
    private final String downloadFilePath = fileDownloadDirectory + File.separator + filename;

    Path downloadedFilePath = Paths.get(fileDownloadDirectory, filename);
    String downloadedFilePathToString = downloadedFilePath.toString();
    
    public FileElements(WebDriver driver){
        PageFactory.initElements(driver, this);
    }



    public void uploadNewFile() throws InterruptedException {
        Thread.sleep(1500);

        fileToUploadButton.sendKeys(uploadFilePath);

        Thread.sleep(2500);

        uploadNewFileButton.submit();
    }

    public void viewFile() throws InterruptedException {
        Thread.sleep(1500);

        viewFileButton.click();
    }

    public void deleteFile() throws InterruptedException {
        Thread.sleep(1500);

        deleteFileButton.click();

        /*Thread.sleep(1500);

        confirmDeleteFileButton.click();*/
    }

    /*
    Methods returning messages corresponding to
    File CRUD transactions
     */
    public String getSuccessfulFileUploadMessage(){
        return successfulFileUpload.getText();
    }

    public String getDuplicateFileUploadMessage(){
        return duplicateFileUploadError.getText();
    }

    public String getSuccessfulFileDeletionMessage(){
        return successfulFileDeletion.getText();
    }

    public String getNoFilesBanner(){
        return noFilesMessage.getText();
    }

    //Fetches data displayed on the Files tab
    public String getFirstFilenameEntry(){
        return filenames.get(0).getText();
    }

    public int getNumberOfFiles(){
        return filenames.size();
    }

    public String getFilename(){
        return filename;
    }

    public String getDownloadedFilePath(){
        return downloadedFilePathToString;
    }
}
