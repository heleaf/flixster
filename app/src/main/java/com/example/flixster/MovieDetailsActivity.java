package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie; // to display
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ivPoster;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // unwrap the movie
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        tvTitle = (TextView) findViewById(R.id.tvTitleDetails);
        tvOverview = (TextView) findViewById(R.id.tvOverviewDetails);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        ivPoster = (ImageView) findViewById(R.id.ivPosterDetails);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        float voteAvg = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAvg / 2.0f);
        context = this;

        String imgUrl =
                context.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE ?
                        movie.getPosterPath() : movie.getBackdropPath();

        int imgPlaceholder =
                context.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE ?
                        R.drawable.flicks_movie_placeholder : R.drawable.flicks_backdrop_placeholder;

        Glide.with(context)
                .load(imgUrl)
                .placeholder(imgPlaceholder)
                .fitCenter()
                .into(ivPoster);

        ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieDetailsActivity", "clicked");
                if (movie.getId() != null) {
                    // intent for the new activity
                    Intent intent = new Intent(context, MovieTrailerActivity.class);
                    intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie.getId()));
                    context.startActivity(intent);
                }
            }
        });

    }

}
