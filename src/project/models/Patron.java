package project.models;

import java.time.LocalDateTime;

public class Patron {
    private int id;
    private String name;
    private String email;
    private String contact;
    private LocalDateTime registrationDate;

    // Constructor
    public Patron(int id, String name, String email, String contact, LocalDateTime registrationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.registrationDate = registrationDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    // Optionally, you can add setters if needed:
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
