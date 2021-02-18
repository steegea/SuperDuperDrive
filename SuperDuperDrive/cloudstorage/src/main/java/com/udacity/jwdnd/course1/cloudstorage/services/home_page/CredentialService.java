package com.udacity.jwdnd.course1.cloudstorage.services.home_page;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.security_services.EncryptionService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/*
Service class that implements Credential mapper methods
 */
@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    //Helper function to create the key
    private String getGeneratedKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        return encodedKey;
    }

    public List<Credential> getAllCredentials(Integer userID){
        List<Credential> credentials = credentialMapper.getCredentialsByUserID(userID);

        return credentials;

    }

    public void addCredential(Credential credential) {

        String encodedKey = getGeneratedKey();
        String password = credential.getPassword();
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        credentialMapper.insertCredentials(credential);

    }

    public void updateCredential(Credential credential) {

        String encodedKey = getGeneratedKey();
        String password = credential.getPassword();
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        credentialMapper.updateCredentials(credential);
    }

    public void deleteCredential(int credentialID) {
        credentialMapper.deleteCredentials(credentialID);
    }
}