package com.invoice.utilities;

public class Cryptography {


    public void TestEncryption() {
        Crypto crypto = new BasicCrypto();

        String data = "Hpmlvta8";
        String enc = new String(crypto.encrypt(data.getBytes()));
        String dec = new String(crypto.decrypt(enc.getBytes()));
        System.out.println("Original: " + data);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }


}

