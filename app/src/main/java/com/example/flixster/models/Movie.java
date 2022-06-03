package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String backdropPath;
    String title;
    String overview;
    Double voteAverage;
    Integer id;

    public static final String IMG_URL = "https://image.tmdb.org/t/p/w342/";

    public Movie(){}

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        voteAverage = jsonObject.getDouble("vote_average");
        id = jsonObject.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray movie_json_array) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movie_json_array.length(); i++){
            movies.add(new Movie(movie_json_array.getJSONObject(i)));
        }
        return movies;
    }

//    public void updateImgPaths(Context context, JSONArray posterSizes, JSONArray backdropSizes){
////        Integer screenWidthPx = Resources.getSystem().getDisplayMetrics().widthPixels;
////        Integer screenHeightPx = Resources.getSystem().getDisplayMetrics().heightPixels;
//        Integer densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
//        Log.d("Movie", Integer.toString(densityDpi));
//    }

    public String getPosterPath() {
        // currently hard coded image size
        return String.format(IMG_URL + "%s", posterPath);
    }

    public String getBackdropPath(){
        return String.format(IMG_URL + "%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() { return voteAverage; }

    public Integer getId() { return id; }
}
