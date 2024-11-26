package project.models;

import java.time.LocalDateTime;

public class Patron {
    private final int id;
    private final String name;
    private final String email;
    private final String contact;
    private final LocalDateTime registrationDate;

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
}
