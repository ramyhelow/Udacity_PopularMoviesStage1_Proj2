package com.ramyhelow.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ramyhelow.popularmoviesstage1.adapters.MovieAdapter;
import com.ramyhelow.popularmoviesstage1.model.Movie;
import com.ramyhelow.popularmoviesstage1.utils.MovieJsonUtils;
import com.ramyhelow.popularmoviesstage1.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private ArrayList<Movie> movieArrayList;
    private MovieAdapter movieAdapter;

    private TextView errorMessage;
    private ProgressBar progressBar;

    private Button topRatedButton;
    private Button popularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorMessage = findViewById(R.id.error_message_tv);
        progressBar = findViewById(R.id.progress_bar);
        topRatedButton = findViewById(R.id.top_rated_button);
        popularButton = findViewById(R.id.popular_button);

        movieRecyclerView = findViewById(R.id.movies_rv);
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        new MovieAsyncTask().execute(NetworkUtils.buildUrl(this, NetworkUtils.POPULAR_MOVIES_URl));


        popularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MovieAsyncTask().execute(NetworkUtils.buildUrl(MainActivity.this, NetworkUtils.POPULAR_MOVIES_URl));
                popularButton.setTextColor(Color.parseColor("#FFFFFF"));
                topRatedButton.setTextColor(Color.parseColor("#C7C6C6"));
            }
        });

        topRatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MovieAsyncTask().execute(NetworkUtils.buildUrl(MainActivity.this, NetworkUtils.TOP_RATED_MOVIES_URl));
                topRatedButton.setTextColor(Color.parseColor("#FFFFFF"));
                popularButton.setTextColor(Color.parseColor("#C7C6C6"));
            }
        });
    }



    public class MovieAsyncTask extends AsyncTask<URL, Void, String> implements MovieAdapter.ItemClickListener{

        @Override
        protected void onPreExecute() {
            movieRecyclerView.setVisibility(View.INVISIBLE);
            errorMessage.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL tmdbUrl = urls[0];
            String jsonResult = null;

            try {
                jsonResult = NetworkUtils.getResponseFromHttpUrl(tmdbUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        }

        @Override
        protected void onPostExecute(String s) {

            progressBar.setVisibility(View.INVISIBLE);

            if (s == null || s == "") {
                errorMessage.setVisibility(View.VISIBLE);
            } else {

                movieRecyclerView.setVisibility(View.VISIBLE);
                try {
                    movieArrayList = MovieJsonUtils.getMovieList(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                movieAdapter = new MovieAdapter(movieArrayList, this);
                movieRecyclerView.setAdapter(movieAdapter);
            }



        }

        @Override
        public void onListItemClick(int clickedItemIndex) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.MOVIE_INTENT_EXTRA, movieArrayList.get(clickedItemIndex));
            startActivity(intent);
        }
    }



}
