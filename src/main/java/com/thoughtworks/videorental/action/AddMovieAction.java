package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.NewReleasePrice;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.util.ParamsUtil;

public class AddMovieAction extends ActionSupport {

    private final MovieRepository movieRepository;

    private String title = "";

    private String flashError = "";
    private String adding;

    public AddMovieAction(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    protected MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFlashError(String flashError) {
        this.flashError = flashError;
    }

    public String getFlashError() {
        return flashError;
    }

    public void setAdding(String adding) {
        this.adding = adding;
    }

    protected boolean firstVisit() {
        return adding == null;
    }

    protected boolean validInput() {
        if (ParamsUtil.nullOrEmpty(title)) {
            flashError = "Must enter all fields";
			return false;
        }
        if (movieRepository.containsMovie(title)) {
            flashError = "Movie '" + title + "' already exists";
            return false;
        }
        return true;
    }

	@Override
	public String execute() throws Exception {
        if (firstVisit()) { return INPUT; }

        if (!validInput()) {
            return ERROR;
        }

        movieRepository.add(new Movie(title, new NewReleasePrice()));

		return SUCCESS;
	}
}
