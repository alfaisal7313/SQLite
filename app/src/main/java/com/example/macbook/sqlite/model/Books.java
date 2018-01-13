package com.example.macbook.sqlite.model;

/**
 * Created by MacBook on 1/8/18.
 */

public class Books {

    private int id;
    private String title;
    private String author;
    private String categories;
    private String release;

    public Books() {
    }

    public Books(int id, String title, String author, String categories, String release) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.release = release;
    }

    public Books(String title, String author, String categories, String release) {
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.release = release;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
