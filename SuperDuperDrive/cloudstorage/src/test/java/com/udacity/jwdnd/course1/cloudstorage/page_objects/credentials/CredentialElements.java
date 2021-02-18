package com.udacity.jwdnd.course1.cloudstorage.page_objects.credentials;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CredentialElements {

    /*
    Elements associated with Credentials
     */

    //Elements found in the Credential modals
    @FindBy(id = "credentialURL")
    private WebElement credentialURL;

    @FindBy(id = "credentialUsername")
    private WebElement credentialUsername;

    @FindBy(id = "credentialPassword")
    private WebElement credentialPassword;

    @FindBy(id = "credentialSubmitButton")
    private WebElement credentialSubmitButton;

    @FindBy(id = "confirmDeleteCredentialButton")
    private WebElement confirmDeleteCredentialButton;

    @FindBy(id = "credentialModalCloseButton")
    private WebElement credentialModalCloseButton;


    //Elements associated with Credential CRUD transactions
    @FindBy(id = "successfulCredentialSubmission")
    private WebElement successfulCredentialSubmission;

    @FindBy(id = "successfulCredentialEdit")
    private WebElement successfulCredentialEdit;

    @FindBy(id = "successfulCredentialDeletion")
    private WebElement successfulCredentialDeletion;

    @FindBy(id = "noCredentials")
    private WebElement noCredentialsMessage;


    /*
    Elements displayed on the Credentials tab

    Outside of the modals
     */
    @FindBy(id = "addNewCredentialButton")
    private WebElement addNewCredentialButton;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(id = "credentialURLOutput")
    private WebElement credentialURLOutput;

    @FindBy(id = "credentialUsernameOutput")
    private WebElement credentialUsernameOutput;

    @FindBy(id = "credentialPasswordOutput")
    private WebElement credentialPasswordOutput;

    @FindBy(className = "credentialURLCell")
    private List<WebElement> credentialURLs;

    @FindBy(className = "credentialUsernameCell")
    private List<WebElement> credentialUsernames;

    @FindBy(className = "credentialPasswordCell")
    private List<WebElement> credentialPasswords;


    //Constructor
    public CredentialElements(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //Helper method
    public void fillInCredentialModal(String url, String username, String password) throws InterruptedException {
        Thread.sleep(1500);

        credentialURL.clear();
        credentialUsername.clear();
        credentialPassword.clear();

        credentialURL.sendKeys(url);
        Thread.sleep(1500);
        credentialUsername.sendKeys(username);
        Thread.sleep(1500);
        credentialPassword.sendKeys(password);

        credentialSubmitButton.submit();
    }


    public void addCredentialEntry(String url, String username, String password) throws InterruptedException {
        addNewCredentialButton.click();
        fillInCredentialModal(url, username, password);
    }

    public void editCredentialEntry(String url, String username, String password) throws InterruptedException {
        editCredentialButton.click();
        fillInCredentialModal(url, username, password);
    }

    public void deleteCredentialEntry() throws InterruptedException {
        Thread.sleep(1500);

        deleteCredentialButton.click();

        /*Thread.sleep(1500);

        confirmDeleteCredentialButton.click();*/

    }

    /*
    Methods returning messages corresponding to
    Credential CRUD transactions
     */
    public String getSuccessfulCredentialSubmissionMessage(){
        return successfulCredentialSubmission.getText();
    }

    public String getSuccessfulCredentialEditMessage(){
        return successfulCredentialEdit.getText();
    }

    public String getSuccessfulCredentialDeletionMessage(){
        return successfulCredentialDeletion.getText();
    }

    public String getNoCredentialsBanner(){
        return noCredentialsMessage.getText();
    }

    //Fetches data displayed on the Credentials tab
    public String getFirstCredentialURLEntry(){
        return credentialURLs.get(0).getText();
    }

    public String getFirstCredentialUsernameEntry(){
        return credentialUsernames.get(0).getText();
    }

    public String getFirstCredentialPasswordEntry(){
        return credentialPasswords.get(0).getText();
    }
}
