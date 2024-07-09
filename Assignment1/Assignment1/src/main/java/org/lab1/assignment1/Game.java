package org.lab1.assignment1;

public class Game {
    private int id;
    private String title;
    private String genre;
    private String platform;
    private int releaseYear;
    private double salesMillion;

    // Constructor
    public Game(int id, String title, String genre, String platform, int releaseYear, double salesMillion) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.salesMillion = salesMillion;
    }

    // Getters and Setters (generated using IDE)
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getSalesMillion() {
        return salesMillion;
    }

    public void setSalesMillion(double salesMillion) {
        this.salesMillion = salesMillion;
    }

    @Override
    public String toString() {
        return title; // Display title in ComboBox or ListView
    }
}

