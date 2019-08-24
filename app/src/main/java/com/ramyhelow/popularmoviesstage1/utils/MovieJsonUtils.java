package com.ramyhelow.popularmoviesstage1.utils;

import com.ramyhelow.popularmoviesstage1.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieJsonUtils {

    final static String MOVIE_RESULTS = "results";

    final static String POSTER_PATH = "poster_path";
    final static String BACKDROP_PATH = "backdrop_path";
    final static String RELEASE_DATE = "release_date";
    final static String TITLE = "title";
    final static String VOTE_AVERAGE = "vote_average";
    final static String OVERVIEW = "overview";

    public static ArrayList<Movie> getMovieList(String movieJsonString) throws JSONException {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        JSONObject movieJsonRoot = new JSONObject(movieJsonString);
        JSONArray movieResults = movieJsonRoot.getJSONArray(MOVIE_RESULTS);

        for (int position = 0; position < movieResults.length(); position++){
            JSONObject movieJsonItem = movieResults.getJSONObject(position);
            String movieTitle = movieJsonItem.getString(TITLE);
            String moviePosterPath = movieJsonItem.getString(POSTER_PATH);
            String movieBackdropPath = movieJsonItem.getString(BACKDROP_PATH);
            String movieOverview = movieJsonItem.getString(OVERVIEW);
            String movieReleaseDate = movieJsonItem.getString(RELEASE_DATE);
            String movieVoteAverage = movieJsonItem.getString(VOTE_AVERAGE);

            movieArrayList.add(new Movie(movieTitle, moviePosterPath, movieBackdropPath, movieOverview, movieVoteAverage, movieReleaseDate));
        }
        return movieArrayList;
    }
}
