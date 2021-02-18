package com.udacity.jwdnd.course1.cloudstorage.models;


public class Credential {
    private Integer credentialID;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userID;

    public Credential(Integer credentialID, String url, String username, String key, String password, Integer userID) {
        this.credentialID = credentialID;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userID = userID;
    }

    public Integer getCredentialID() {
        return credentialID;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public void setCredentialID(Integer credentialID) {
        this.credentialID = credentialID;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
