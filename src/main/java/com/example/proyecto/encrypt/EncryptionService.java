package com.example.proyecto.encrypt;

import org.jasypt.util.text.AES256TextEncryptor;

/**
 * La clase EncryptionService proporciona métodos para cifrar, descifrar y hacer coincidir datos
 * cifrados mediante cifrado AES256 con una clave secreta especificada.
 */
public class EncryptionService {

    private final AES256TextEncryptor textEncryptor;
    private final String secretKey = "bochi";

    public EncryptionService() {
        textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(secretKey);
    }

    /**
     * El método encrypt toma una entrada de String y devuelve la versión cifrada utilizando un
     * objeto textEncryptor.
     * 
     * @param data El parámetro data es el texto o la información que se desea cifrar.
     * @return El método encrypt devuelve el resultado de cifrar los datos.
     */
    public String encrypt(String data) {
        return textEncryptor.encrypt(data);
    }

    /**
     * El método decrypt toma un String de datos cifrada como entrada y devuelve el texto
     * descifrado utilizando un objeto textEncryptor.
     * 
     * @param encryptedData El parámetro encryptedData son los datos que han sido cifrados y necesitan ser descifrados.
     * @return El método decrypt devuelve los datos descifrados.
     */
    public String decrypt(String encryptedData) {
        return textEncryptor.decrypt(encryptedData);
    }
    
}
