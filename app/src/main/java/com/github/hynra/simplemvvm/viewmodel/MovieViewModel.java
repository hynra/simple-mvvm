package com.github.hynra.simplemvvm.viewmodel;

import android.databinding.BaseObservable;

import com.github.hynra.simplemvvm.model.entity.Movie;

/**
 * Created by hynra on 15/05/2018.
 */

public class MovieViewModel extends BaseObservable {

    private final Movie mMovie;

    public MovieViewModel(Movie mMovie) {
        this.mMovie = mMovie;
    }

    public String getCoverUrl() {
        return mMovie.getImages().getSmall();
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public float getRating() {
        return mMovie.getRating().getAverage();
    }

    public String getRatingText(){
        return String.valueOf(mMovie.getRating().getAverage());
    }

    public String getYear() {
        return mMovie.getYear();
    }

    public String getMovieType() {
        StringBuilder builder = new StringBuilder();
        for (String s : mMovie.getGenres()) {
            builder.append(s + " ");
        }
        return builder.toString();
    }

    public String getImageUrl() {
        return mMovie.getImages().getSmall();
    }
}
