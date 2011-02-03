package com.thoughtworks.videorental.domain;

import com.thoughtworks.videorental.util.Feature;

public class DetailedMovie extends Movie {
    private String director;
    private String actor;
    private String actress;
    private String category;

    public DetailedMovie(String title, Price price) {
        super(title, price);
        if (!Feature.DetailedMovies.isEnabled()) {
            throw new RuntimeException("Detailed Movies Feature not enabled");
        }
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
