package com.thoughtworks.videorental.action;

import com.thoughtworks.videorental.domain.DetailedMovie;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.util.Feature;
import com.thoughtworks.videorental.util.ParamsUtil;

public class AddDetailedMovieAction extends AddMovieAction {
    private final boolean detailedMoviesFeatureEnabled;
    private String director = "";
    private String actor = "";
    private String actress = "";
    private String category = "";

    public AddDetailedMovieAction(MovieRepository movieRepository) {
        super(movieRepository);
        if (!Feature.DetailedMovies.isEnabled()) {
            throw new RuntimeException("Detailed Movies feature not enabled");
        }
        detailedMoviesFeatureEnabled = Feature.DetailedMovies.isEnabled();
    }

    public boolean isDetailedMoviesFeatureEnabled() {
        return detailedMoviesFeatureEnabled;
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

    @Override
    protected boolean validInput() {
        if (ParamsUtil.nullOrEmpty(director, actor, actress, category)) {
            super.setFlashError("Must enter all fields");
            return false;
        }
        return super.validInput();
    }

    @Override
    public String execute() throws Exception {
        if (firstVisit()) { return INPUT; }

        if (!validInput()) { return ERROR; }

        DetailedMovie movie = new DetailedMovie(getTitle(), Movie.NEW_RELEASE);
        movie.setDirector(director);
        movie.setActor(actor);
        movie.setActress(actress);
        movie.setCategory(category);
        getMovieRepository().add(movie);
        return SUCCESS;
    }
}
