package com.udacity.jwdnd.course1.cloudstorage.forms;

/*
A form-backed object for storing credential data
 */
public class CredentialForm {
    private Integer credentialID;
    private String url;
    private String username;
    private String key;
    private String password;


    public CredentialForm () {

    }

    public Integer getCredentialID() {
        return credentialID;
    }

    public void setCredentialID(Integer credentialID) {
        this.credentialID = credentialID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
