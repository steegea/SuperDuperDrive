package com.udacity.jwdnd.course1.cloudstorage.constants;

/*
A constant class that stores messages that may be displayed on the UI
 */
public final class WebpageMessages{

    //  Variables pertaining to messages about no entries in a tab
    public static final String NO_FILES_MESSAGE = "You do not have any files.";
    public static final String NO_NOTES_MESSAGE = "You do not have any notes.";
    public static final String NO_CREDENTIALS_MESSAGE = "You do not have any credential entries.";

    //  Messages associated with successful File CRUD operations
    public static final String SUCCESSFUL_FILE_CREATION_SUFFIX = " has been uploaded successfully!";
    public static final String SUCCESSFUL_FILE_DELETION_MESSAGE = "The file has been deleted successfully!";

    //  Messages corresponding to successful Note CRUD actions
    public static final String SUCCESSFUL_NOTE_CREATION_MESSAGE = "Your note has been added!";
    public static final String SUCCESSFUL_NOTE_EDIT_MESSAGE = "Your note changes have been saved!";
    public static final String SUCCESSFUL_NOTE_DELETION_MESSAGE = "The note has been successfully deleted!";

    //  Messages tied to successful Credential CRUD processes
    public static final String SUCCESSFUL_CREDENTIAL_CREATION_MESSAGE = "Your credential entry has been added!";
    public static final String SUCCESSFUL_CREDENTIAL_EDIT_MESSAGE = "Your credential changes have been saved!";
    public static final String SUCCESSFUL_CREDENTIAL_DELETION_MESSAGE = "The credential entry has been successfully deleted!";

    //  Variables corresponding to error messages
    public static final String DUPLICATE_FILE_ERROR = "A file with the given name already exists! " +
            "Please rename the file or upload a different file.";
    public static final String FILE_DELETION_ERROR = "The file could not be deleted!";

    public static final String NOTE_SUBMISSION_ERROR = "There was an issue saving your changes!";
    public static final String NOTE_DELETION_ERROR = "ERROR: The note could not be deleted!";

    public static final String CREDENTIAL_SUBMISSION_ERROR = "There was an issue adding your credential entry!";
    public static final String CREDENTIAL_DELETION_ERROR = "The credential entry could not be deleted!";


    //  Private constructor
    //  Prevents the class from being instantiated in other classes
    private WebpageMessages(){

    }
}

