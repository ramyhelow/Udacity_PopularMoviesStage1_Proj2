package com.ramyhelow.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ramyhelow.popularmoviesstage1.model.Movie;
import com.ramyhelow.popularmoviesstage1.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    public static final String MOVIE_INTENT_EXTRA = "MOVIE_INTENT_EXTRA";

    private ImageView backdropIv;
    private TextView titleTv;
    private TextView plotTv;
    private TextView ratingTv;
    private TextView releaseDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        backdropIv = findViewById(R.id.movie_detail_backdrop);
        titleTv = findViewById(R.id.movie_detail_title);
        plotTv = findViewById(R.id.movie_detail_plot);
        ratingTv = findViewById(R.id.movie_detail_rating);
        releaseDateTv = findViewById(R.id.movie_detail_release_date);

        Intent receivedIntent = getIntent();
        Movie selectedMovie = (Movie) receivedIntent.getSerializableExtra(MOVIE_INTENT_EXTRA);

        setTitle(selectedMovie.getMovie_title());

        Picasso.get().load(NetworkUtils.BACKDROP_BASE_URl+selectedMovie.getMovie_backdrop()).into(backdropIv);
        titleTv.setText(selectedMovie.getMovie_title());
        plotTv.setText(selectedMovie.getMovie_plot());
        ratingTv.setText(selectedMovie.getMovie_rating());
        releaseDateTv.setText(selectedMovie.getMovie_release_date());
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
