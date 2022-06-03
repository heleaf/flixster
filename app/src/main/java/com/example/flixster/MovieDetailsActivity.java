package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
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
//
//    public static final String API_KEY = "1164320e19fc1f0ad3ba8319e32019b2";
//    public static final String CONFIG_URL
//        = String.format("https://api.themoviedb.org/3/configuration?api_key=%s", API_KEY);
//    public static final String IMG_URL = "https://image.tmdb.org/t/p/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // unwrap the movie
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
//        Log.d("MovieDetailsActivity", String.format("Showing details for %s", movie.getTitle()));

        // movie.id

//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(CONFIG_URL, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Headers headers, JSON json) {
//                Log.d("MovieDetailsActivity", "onSuccess");
//                try {
//                    JSONObject images = json.jsonObject.getJSONObject("images");
//                    JSONArray posterSizes = images.getJSONArray("poster_sizes");
//                    JSONArray backdropSizes = images.getJSONArray("backdrop_sizes");
//
//                    movie.updateImgPaths(context, posterSizes, backdropSizes);
////                    MovieDetailsActivity.this.notify();
//                    // determine the appropriate size based on the screen
////                    Log.d("MovieDetailsActivity", Integer.toString(posterSizes.length()));
////                    Log.d("MovieDetailsActivity", String.valueOf(posterSizes));
////                    Log.d("MovieDetailsActivity", posterSizes.getString(0));
//
//                } catch (JSONException e) {
//                    Log.d("MovieDetailsActivity", String.valueOf(e));
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
//                Log.d("MovieDetailsActivity", "onFailure");
//            }
//        });

        tvTitle = (TextView) findViewById(R.id.tvTitleDetails);
        tvOverview = (TextView) findViewById(R.id.tvOverviewDetails);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        ivPoster = (ImageView) findViewById(R.id.ivPosterDetails);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        float voteAvg = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAvg / 2.0f);
        context = this;

        String imgUrl = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ?
                movie.getPosterPath() : movie.getBackdropPath();

        int imgPlaceholder = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ?
                R.drawable.flicks_movie_placeholder : R.drawable.flicks_backdrop_placeholder;

        Glide.with(context)
                .load(imgUrl)
                .placeholder(imgPlaceholder)
                .fitCenter()
                .into(ivPoster);

    }
}