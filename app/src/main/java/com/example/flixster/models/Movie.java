package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String poster_path;
    String title;
    String overview;
    public Movie(JSONObject jsonObject) throws JSONException {
        poster_path = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movie_json_array) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movie_json_array.length(); i++){
            movies.add(new Movie(movie_json_array.getJSONObject(i)));
        }
        return movies;
    }

    public String getPoster_path() {
        // currently hard coded image size
        // should call the client here again, later
        return String.format("https://image.tmdb.org/t/p/w342/%s", poster_path);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
