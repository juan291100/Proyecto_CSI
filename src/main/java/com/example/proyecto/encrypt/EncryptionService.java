package com.example.proyecto.encrypt;

import org.jasypt.util.text.AES256TextEncryptor;

public class EncryptionService {

    private final AES256TextEncryptor textEncryptor;
    private final String secretKey = "bochi";

    public EncryptionService() {
        textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(secretKey);
    }

    public String encrypt(String data) {
        return textEncryptor.encrypt(data);
    }

    public String decrypt(String encryptedData) {
        return textEncryptor.decrypt(encryptedData);
    }

    public boolean match(String plainText, String encryptedText) {
        return plainText.equals(decrypt(encryptedText));
    } 
    
}
