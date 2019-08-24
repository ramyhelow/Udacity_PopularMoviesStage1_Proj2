package com.ramyhelow.popularmoviesstage1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramyhelow.popularmoviesstage1.R;
import com.ramyhelow.popularmoviesstage1.model.Movie;
import com.ramyhelow.popularmoviesstage1.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private ArrayList<Movie> movieArrayList;

    final private ItemClickListener mOnClickListener;

    public MovieAdapter(ArrayList<Movie> movieArrayList, ItemClickListener itemClickListener) {
        this.mOnClickListener = itemClickListener;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item,parent,false);
        return new MovieAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {
        String thumbnailUrl = NetworkUtils.POSTER_BASE_URl + movieArrayList.get(position).getMovie_poster();
        Picasso.get().load(thumbnailUrl).into(holder.movie_thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView movie_thumbnail;

        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            movie_thumbnail = itemView.findViewById(R.id.movie_thumbnail);
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface ItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }
}
