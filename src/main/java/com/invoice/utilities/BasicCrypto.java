package com.invoice.utilities;

public class BasicCrypto implements Crypto {

    @Override
    public byte[] encrypt(byte[] data) {

        byte[] encryptedString = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            encryptedString[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
        }

        return encryptedString;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        byte[] decryptedString = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            decryptedString[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
        }

        return decryptedString;
    }

}
