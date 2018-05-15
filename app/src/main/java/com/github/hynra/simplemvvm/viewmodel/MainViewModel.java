package com.github.hynra.simplemvvm.viewmodel;

import android.databinding.ObservableField;

import com.github.hynra.simplemvvm.model.entity.Movie;

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

}
