package com.ramyhelow.popularmoviesstage1.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.ramyhelow.popularmoviesstage1.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public class NetworkUtils {
    public static final String POSTER_BASE_URl = "http://image.tmdb.org/t/p/w185/";
    public static final String BACKDROP_BASE_URl = "http://image.tmdb.org/t/p/w500/";
    public static final String POPULAR_MOVIES_URl = "https://api.themoviedb.org/3/movie/popular";
    public static final String TOP_RATED_MOVIES_URl = "https://api.themoviedb.org/3/movie/top_rated";

    public static final String API_KEY_PARAM = "api_key";
    public static URL buildUrl(Context context, String wantedUrl) {
        Uri builtUri = Uri.parse(wantedUrl).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, context.getResources().getString(R.string.tmdb_api_key))
                .build();

        Log.e("rmy",builtUri.toString());

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
