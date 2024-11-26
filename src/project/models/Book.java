package project.models;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final String isbn;
    private final int copiesAvailable;

    // Constructor
    public Book(int id, String title, String author, String isbn, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copiesAvailable = copiesAvailable;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

}
