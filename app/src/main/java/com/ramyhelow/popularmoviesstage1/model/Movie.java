package com.ramyhelow.popularmoviesstage1.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private String movie_title;
    private String movie_poster;
    private String movie_backdrop;
    private String movie_plot;
    private String movie_rating;
    private String movie_release_date;

    public Movie(String movie_title, String movie_poster, String movie_backdrop, String movie_plot, String movie_rating, String movie_release_date) {
        this.movie_title = movie_title;
        this.movie_poster = movie_poster;
        this.movie_backdrop = movie_backdrop;
        this.movie_plot = movie_plot;
        this.movie_rating = movie_rating;
        this.movie_release_date = movie_release_date;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public void setMovie_poster(String movie_poster) {
        this.movie_poster = movie_poster;
    }

    public String getMovie_plot() {
        return movie_plot;
    }

    public void setMovie_plot(String movie_plot) {
        this.movie_plot = movie_plot;
    }

    public String getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(String movie_rating) {
        this.movie_rating = movie_rating;
    }

    public String getMovie_release_date() {
        return movie_release_date;
    }

    public void setMovie_release_date(String movie_release_date) {
        this.movie_release_date = movie_release_date;
    }

    public String getMovie_backdrop() {
        return movie_backdrop;
    }

    public void setMovie_backdrop(String movie_backdrop) {
        this.movie_backdrop = movie_backdrop;
    }
}
