package com.udacity.jwdnd.course1.cloudstorage.forms;

/*
A form-backed object for storing note data
 */

public class NoteForm {
    private Integer noteID;
    private String noteTitle;
    private String noteDescription;
    private Integer userID;

    public NoteForm(){

    }

    public Integer getNoteID() {
        return noteID;
    }

    public void setNoteID(Integer noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}