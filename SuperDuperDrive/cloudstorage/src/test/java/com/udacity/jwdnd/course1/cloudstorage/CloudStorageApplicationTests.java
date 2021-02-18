package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page_objects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.credentials.CredentialElements;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.files.FileElements;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.notes.NoteElements;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.auth.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.auth.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	//Declarations for variables used across all the unit tests
	private static WebDriver driver;

	private static SignupPage signupPage;
	private static LoginPage loginPage;
	private static HomePage homePage;
	private static FileElements fileElements;
	private static NoteElements noteElements;
	private static CredentialElements credentialElements;

	private String baseURL, signupURL, loginURL, homeURL, resultURL;
	private String firstName, lastName, username, password;
	private String nonExistentUsername, nonExistentPassword;

	private String inputNoteTitle, inputNoteDescription,
					editedNoteTitle, editedNoteDescription;

	private String inputCredentialURL, inputCredentialUsername, inputCredentialPassword,
					editedCredentialURL, editedCredentialUsername, editedCredentialPassword;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		signupPage = new SignupPage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		fileElements = new FileElements(driver);
		noteElements = new NoteElements(driver);
		credentialElements = new CredentialElements(driver);
	}

	@AfterAll
	static void afterAll(){
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeEach
	public void beforeEach() throws InterruptedException {

		baseURL = "http://localhost:" + port;
		signupURL = baseURL + "/signup";
		loginURL = baseURL + "/login";
		homeURL = baseURL + "/home";
		resultURL = baseURL + "/result";

		firstName = "John";
		lastName = "Doe";
		username = "jdoe";
		password = "123abc";

		nonExistentUsername = "jsmith";
		nonExistentPassword = "nonexistentPW";

		//Variables for the Note tests
		inputNoteTitle = "Hello World";
		inputNoteDescription = "Test123";

		editedNoteTitle = "Shopping List";
		editedNoteDescription = "Eggs, Milk, Spinach";

		//Variables for the Credential tests
		inputCredentialURL = "http://www.google.com";
		inputCredentialUsername = "jdoe";
		inputCredentialPassword = "123abc";

		editedCredentialURL = "http://www.facebook.com";
		editedCredentialUsername = "jdoe2";
		editedCredentialPassword = "HelloWorld789";

		Thread.sleep(2000);
	}

	//Signup & Login helper methods
	public void executeSignup(String firstName, String lastName, String username, String password) throws InterruptedException {
		driver.get(signupURL);
		signupPage.signupClick(firstName, lastName, username, password);
	}

	public void executeLogin(String username, String password) throws InterruptedException {
		driver.get(loginURL);
		loginPage.loginClick(username, password);
	}

	//File upload helper method
	public void executeFileUpload() throws InterruptedException {
		homePage.goToFiles();

		fileElements.uploadNewFile();

		Thread.sleep(2000);
	}


	/*
	Signup & Login tests
	 */
	@Test
	@Order(1)
	public void getSignupPage(){
		driver.get(signupURL);
		assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	@Order(2)
	public void getLoginPage() {
		driver.get(loginURL);
		assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(3)
	public void testUnauthorizedUserAccess() throws InterruptedException {
		driver.get(homeURL);
		assertEquals("Login", driver.getTitle());

		Thread.sleep(2000);

		driver.get(resultURL);
		assertEquals("Login", driver.getTitle());
	}

	@Test
	@Order(4)
	public void testSignupAndLogin_Successful() throws InterruptedException {

		//Sign-up test code
		String successfulSignUpMessage = "You successfully signed up! Please continue to the login page.";

		executeSignup(firstName, lastName, username, password);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("signupSuccessMessage")));

		String returnedSignupMessage = signupPage.getSignupSuccessMessage();

		assertEquals(successfulSignUpMessage, returnedSignupMessage);

		Thread.sleep(2000);

		//Call to login helper method
		executeLogin(username, password);

		Thread.sleep(2000);

		homePage.logout();

		Thread.sleep(2000);

	}

	@Test
	@Order(5)
	public void testDuplicateSignup() throws InterruptedException {
		String duplicateSignupMessage = "ERROR: A user with the name \"" + username + "\" already exists!";

		executeSignup(firstName, lastName, username, password);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("signupErrorMessage")));

		String returnedSignupMessage = signupPage.getSignupErrorMessage();

		assertEquals(duplicateSignupMessage, returnedSignupMessage);

		Thread.sleep(2000);
	}

	//Tests different invalid login scenarios
	@Test
	@Order(6)
	public void testInvalidLogin() throws InterruptedException {
		String invalidLoginMessage = "Invalid username and/or password!";

		executeLogin(nonExistentUsername, password);
		String loginErrorMessage = loginPage.getLoginErrorMessage();

		assertEquals(invalidLoginMessage, loginErrorMessage);

		executeLogin(username, nonExistentPassword);
		loginErrorMessage = loginPage.getLoginErrorMessage();
		assertEquals(invalidLoginMessage, loginErrorMessage);

		executeLogin(nonExistentUsername, nonExistentPassword);
		loginErrorMessage = loginPage.getLoginErrorMessage();
		assertEquals(invalidLoginMessage, loginErrorMessage);

	}

	/*
	File Tests
	 */
	@Test
	@Order(7)
	public void testUploadFile() throws InterruptedException {
		String filename = fileElements.getFilename();
		String successfulFileUploadMessage = "\"" + filename + "\" has been uploaded successfully!";


		executeLogin(username, password);
		executeFileUpload();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulFileUpload")));

		String fileUploadMessage = fileElements.getSuccessfulFileUploadMessage();
		assertEquals(successfulFileUploadMessage, fileUploadMessage);

		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToFiles();

		String returnedFilename = fileElements.getFirstFilenameEntry();
		assertEquals(filename, returnedFilename);

		Thread.sleep(2000);
	}

	@Test
	@Order(8)
	public void testDuplicateFileUpload() throws InterruptedException {
		String duplicateFileUploadMessage = "A file with the given name already exists! " +
				"Please rename the file or upload a different file.";
		int numberOfFiles = fileElements.getNumberOfFiles();

		executeLogin(username, password);
		executeFileUpload();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("duplicateFileError")));

		String fileUploadMessage = fileElements.getDuplicateFileUploadMessage();
		assertEquals(duplicateFileUploadMessage, fileUploadMessage);

		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToFiles();

		assertEquals(1, numberOfFiles);

	}

	@Test
	@Order(9)
	public void testViewFile() throws InterruptedException {
		String downloadedFilePath = fileElements.getDownloadedFilePath();
		String filename = fileElements.getFilename();

		executeLogin(username, password);

		homePage.goToFiles();

		fileElements.viewFile();

		Thread.sleep(2000);

		assertTrue(downloadedFilePath.contains(filename));

		Thread.sleep(2000);
	}

	@Test
	@Order(10)
	public void testDeleteFile() throws InterruptedException {
		String successfulFileDeletionMessage = "The file has been deleted successfully!";
		String noFilesMessage = "You do not have any files.";

		executeLogin(username, password);

		homePage.goToFiles();

		Thread.sleep(2000);

		fileElements.deleteFile();

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulFileDeletion")));

		String fileDeletionMessage = fileElements.getSuccessfulFileDeletionMessage();

		assertEquals(successfulFileDeletionMessage, fileDeletionMessage);

		homePage.goToHome();
		Thread.sleep(2500);
		homePage.goToFiles();

		String noFilesBanner = fileElements.getNoFilesBanner();

		assertEquals(noFilesMessage, noFilesBanner);

		Thread.sleep(3000);



	}

	/*
	Note Tests
	 */
	@Test
	@Order(11)
	public void testCreateNote() throws InterruptedException {

		String successfulNoteSubmissionText = "Your note has been added!";

		executeLogin(username, password);

		homePage.goToNotes();

		noteElements.addNoteInfo(inputNoteTitle, inputNoteDescription);

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulNoteSubmission")));

		String noteSubmissionText = noteElements.getSuccessfulNoteSubmissionMessage();

		assertEquals(successfulNoteSubmissionText, noteSubmissionText);


		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToNotes();

		String returnedNoteTitle = noteElements.getFirstNoteTitleCell();
		String returnedNoteDescription = noteElements.getFirstNoteDescriptionCell();

		assertEquals(inputNoteTitle, returnedNoteTitle);
		assertEquals(inputNoteDescription, returnedNoteDescription);

		Thread.sleep(2000);

	}

	@Test
	@Order(12)
	public void testEditNote() throws InterruptedException {

		String successfulNoteEditText = "Your note changes have been saved!";

		executeLogin(username, password);

		homePage.goToNotes();

		noteElements.editNote(editedNoteTitle, editedNoteDescription);

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulNoteEdit")));

		String noteEditText = noteElements.getSuccessfulNoteEditMessage();

		assertEquals(successfulNoteEditText, noteEditText);

		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToNotes();

		String returnedNoteTitle = noteElements.getFirstNoteTitleCell();
		String returnedNoteDescription = noteElements.getFirstNoteDescriptionCell();

		assertEquals(editedNoteTitle, returnedNoteTitle);
		assertEquals(editedNoteDescription, returnedNoteDescription);

		Thread.sleep(3000);

	}

	@Test
	@Order(13)
	public void testDeleteNote() throws InterruptedException {
		String successfulNoteDeletionText = "The note has been successfully deleted!";
		String noNotesMessage = "You do not have any notes.";

		executeLogin(username, password);

		homePage.goToNotes();

		Thread.sleep(2000);

		noteElements.deleteNote();

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulNoteDeletion")));

		String noteDeletionText = noteElements.getSuccessfulNoteDeletionMessage();

		assertEquals(successfulNoteDeletionText, noteDeletionText);

		homePage.goToHome();
		Thread.sleep(2500);
		homePage.goToNotes();

		String noNotesBanner = noteElements.getNoNotesBanner();

		assertEquals(noNotesMessage, noNotesBanner);

		Thread.sleep(3000);

	}

	/*
	Credential Tests
	 */
	@Test
	@Order(14)
	public void testCreateCredential() throws InterruptedException {

		String successfulCredentialSubmissionMessage = "Your credential entry has been added!";

		executeLogin(username, password);

		homePage.goToCredentials();

		credentialElements.addCredentialEntry(inputCredentialURL, inputCredentialUsername, inputCredentialPassword);

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulCredentialSubmission")));
		String credentialSubmissionMessage = credentialElements.getSuccessfulCredentialSubmissionMessage();

		assertEquals(successfulCredentialSubmissionMessage, credentialSubmissionMessage);

		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToCredentials();

		String returnedCredentialURL = credentialElements.getFirstCredentialURLEntry();
		String returnedCredentialUsername = credentialElements.getFirstCredentialUsernameEntry();
		String returnedCredentialPassword = credentialElements.getFirstCredentialPasswordEntry();

		assertEquals(inputCredentialURL, returnedCredentialURL);
		assertEquals(inputCredentialUsername, returnedCredentialUsername);
		assertNotEquals(inputCredentialPassword, returnedCredentialPassword);

		Thread.sleep(2000);

	}

	@Test
	@Order(15)
	public void testEditCredential() throws InterruptedException {

		String successfulCredentialEditMessage = "Your changes have been saved!";
		
		executeLogin(username, password);

		homePage.goToCredentials();

		credentialElements.editCredentialEntry(editedCredentialURL, editedCredentialUsername, editedCredentialPassword);

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulCredentialEdit")));

		String credentialEditMessage = credentialElements.getSuccessfulCredentialEditMessage();

		assertEquals(successfulCredentialEditMessage, credentialEditMessage);

		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToCredentials();

		String returnedCredentialURL = credentialElements.getFirstCredentialURLEntry();
		String returnedCredentialUsername = credentialElements.getFirstCredentialUsernameEntry();
		String returnedCredentialPassword = credentialElements.getFirstCredentialPasswordEntry();

		assertEquals(editedCredentialURL, returnedCredentialURL);
		assertEquals(editedCredentialUsername, returnedCredentialUsername);
		assertNotEquals(editedCredentialPassword, returnedCredentialPassword);


	}

	@Test
	@Order(16)
	public void testDeleteCredential() throws InterruptedException {
		String successfulCredentialDeletionMessage = "The credential entry has been successfully deleted!";
		String noCredentialsMessage = "You do not have any credential entries.";

		executeLogin(username, password);

		homePage.goToCredentials();

		Thread.sleep(2000);

		credentialElements.deleteCredentialEntry();

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(webDriver -> webDriver.findElement(By.id("successfulCredentialDeletion")));

		String credentialDeletionMessage = credentialElements.getSuccessfulCredentialDeletionMessage();

		assertEquals(successfulCredentialDeletionMessage, credentialDeletionMessage);

		homePage.goToHome();
		Thread.sleep(2000);
		homePage.goToCredentials();

		String noCredentialsBanner = credentialElements.getNoCredentialsBanner();

		assertEquals(noCredentialsMessage, noCredentialsBanner);

		Thread.sleep(2000);

	}

}




