package com.github.hynra.simplemvvm.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.github.hynra.simplemvvm.model.data.DataRequest;
import com.github.hynra.simplemvvm.model.entity.Movie;
import com.github.hynra.simplemvvm.view.CompletedListener;
import com.github.hynra.simplemvvm.view.MovieAdapter;

import rx.Subscriber;

/**
 * Created by hynra on 15/05/2018.
 */

public class MainViewModel {

    public ObservableField<Integer> contentViewVisibility;
    public ObservableField<Integer> progressBarVisibility;
    public ObservableField<Integer> errorInfoVisibility;
    public ObservableField<String> exception;

    private Subscriber<Movie> mSubscriber;
    private MovieAdapter mMovieAdapter;
    private CompletedListener mCompletedListener;


    public MainViewModel(MovieAdapter movieAdapter, CompletedListener completedListener) {
        mMovieAdapter = movieAdapter;
        mCompletedListener = completedListener;
        initData();
        getMovies();
    }


    private void initData() {
        contentViewVisibility = new ObservableField<>();
        progressBarVisibility = new ObservableField<>();
        errorInfoVisibility = new ObservableField<>();
        exception = new ObservableField<>();
        contentViewVisibility.set(View.GONE);
        errorInfoVisibility.set(View.GONE);
        progressBarVisibility.set(View.VISIBLE);
    }

    private void getMovies() {
        mSubscriber = new Subscriber<Movie>() {

            @Override
            public void onCompleted() {
                hideAll();
                contentViewVisibility.set(View.VISIBLE);
                mCompletedListener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                hideAll();
                errorInfoVisibility.set(View.VISIBLE);
                Log.e("MainViewModel",e.getMessage());
                exception.set(e.getMessage());
            }

            @Override
            public void onNext(Movie movie) {
                mMovieAdapter.addItem(movie);
            }
        };
        DataRequest.getMovies(mSubscriber, 0, 20);
    }


    private void hideAll() {
        contentViewVisibility.set(View.GONE);
        errorInfoVisibility.set(View.GONE);
        progressBarVisibility.set(View.GONE);
    }

    public void refreshData() {
        getMovies();
    }

}
