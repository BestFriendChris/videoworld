package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.NewReleasePrice;
import com.thoughtworks.videorental.domain.Price;
import com.thoughtworks.videorental.domain.repository.MovieRepository;

public class AddMovieAction extends ActionSupport {

    private final MovieRepository movieRepository;

    private String title = "";

    private String flashError = "";
    private String adding;

    public AddMovieAction(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlashError() {
        return flashError;
    }

    public void setAdding(String adding) {
        this.adding = adding;
    }

    private boolean nullOrEmpty(String... params) {
        for (String param : params) {
            if (param == null || param.isEmpty()) { return true; }
        }
        return false;
    }

	@Override
	public String execute() throws Exception {
        if (adding == null) { return INPUT; }

        if (nullOrEmpty(title)) {
            flashError = "Must enter all fields";
			return ERROR;
		}

        if (movieRepository.containsMovie(title)) {
            flashError = "Movie '" + title + "' already exists";
            return ERROR;
        }

        movieRepository.add(new Movie(title, new NewReleasePrice()));

		return SUCCESS;
	}
}
