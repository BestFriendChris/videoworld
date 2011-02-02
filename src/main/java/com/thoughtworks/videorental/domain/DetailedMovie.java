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

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
