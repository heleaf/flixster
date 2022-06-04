package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // inflate a layout from XML and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // put data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        // get movie at the passed in position
        Movie movie = movies.get(position);
        // bind the movie data into the viewholder
        holder.bind(movie);
    }

    // total number of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imgUrl = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ?
                    movie.getBackdropPath() : movie.getPosterPath();

            int imgPlaceholder = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ?
                    R.drawable.flicks_backdrop_placeholder : R.drawable.flicks_movie_placeholder;

            // rounding corners
            Glide.with(context)
                    .load(imgUrl)

                    .fitCenter()
                    .transform(new RoundedCorners(30))
                    .placeholder(imgPlaceholder)
                    .into(ivPoster);
        }

        // show movie details when a user clicks on a view
        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION){
                Movie movie = movies.get(pos);
                // intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);

                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                context.startActivity(intent);

                Log.d("MovieAdapter", pos + " " + movie.getTitle() + intent.toString());
            }
        }

//        @Override
//        public boolean onHover(View v, MotionEvent event) {
//            v.setAlpha(0.2f);
//            v.notify();
//            return true;
//        }
    }
}
