package com.udacity.jwdnd.course1.cloudstorage.page_objects.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    /*
    Elements displayed on the signup page.
     */

    @FindBy(id = "signupFirstName")
    private WebElement signupFirstName;

    @FindBy(id = "signupLastName")
    private WebElement signupLastName;

    @FindBy(id = "signupUsername")
    private WebElement signupUsername;

    @FindBy(id = "signupPassword")
    private WebElement signupPassword;

    @FindBy(id = "signupButton")
    private WebElement signupButton;

    @FindBy(id = "signupSuccessMessage")
    private WebElement signupSuccessMessage;

    @FindBy(id = "signupErrorMessage")
    private WebElement signupErrorMessage;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void signupClick(String firstName, String lastName, String username, String password) throws InterruptedException {

        signupFirstName.sendKeys(firstName);
        Thread.sleep(2000);
        signupLastName.sendKeys(lastName);
        Thread.sleep(2000);
        signupUsername.sendKeys(username);
        Thread.sleep(2000);
        signupPassword.sendKeys(password);
        Thread.sleep(2000);

        signupButton.submit();

        Thread.sleep(2000);
    }

    public String getSignupSuccessMessage(){
        return signupSuccessMessage.getText();
    }

    public String getSignupErrorMessage(){
        return signupErrorMessage.getText();
    }


}

