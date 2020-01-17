package com.semantyca.entity;

public class UserCredentials {
    //these fields should be encrypted at db level as well
    private String privateKey, publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(final String publicKey) {
        this.publicKey = publicKey;
    }
}
