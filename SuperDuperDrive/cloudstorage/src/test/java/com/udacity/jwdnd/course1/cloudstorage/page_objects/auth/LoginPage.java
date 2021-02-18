package com.udacity.jwdnd.course1.cloudstorage.page_objects.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
Elements displayed on the login page
 */
public class LoginPage {

    @FindBy(id = "loginUsername")
    private WebElement loginUsername;

    @FindBy(id = "loginPassword")
    private WebElement loginPassword;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(id = "loginErrorMessage")
    private WebElement loginErrorMessage;

    @FindBy(id = "logoutMessage")
    private WebElement logoutMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void loginClick(String username, String password) throws InterruptedException {
        loginUsername.sendKeys(username);
        Thread.sleep(2000);

        loginPassword.sendKeys(password);
        Thread.sleep(2000);

        loginButton.submit();
        Thread.sleep(2000);
    }

    public String getLoginErrorMessage(){
        return loginErrorMessage.getText();
    }

    public String getLogoutMessage(){
        return logoutMessage.getText();
    }
}

