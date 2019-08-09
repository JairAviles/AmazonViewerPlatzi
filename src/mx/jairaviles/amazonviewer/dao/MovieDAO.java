package mx.jairaviles.amazonviewer.dao;

import mx.jairaviles.amazonviewer.model.Movie;

import java.util.ArrayList;

public interface MovieDAO {

    default Movie setMoviewViewed(Movie movie) {
        return movie;
    }

    default ArrayList<Movie> read() {
        ArrayList<Movie> movies = new ArrayList<>();
        return movies;
    }

    private boolean getMovieViewed() {
        return false;
    }

}
