package com.invoice.model;

public class CustomerModel {

    private String name;
    private int uniqueId;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {

        return "Customer id: " + uniqueId + "\n" + "Name: " + name + "\n" + "Customer Email: " + email;
    }
}
