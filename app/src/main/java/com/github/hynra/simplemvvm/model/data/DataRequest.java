package com.github.hynra.simplemvvm.model.data;

import com.github.hynra.simplemvvm.model.entity.Movie;
import com.github.hynra.simplemvvm.model.entity.Response;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hynra on 15/05/2018.
 */

public class DataRequest {

    public static void getMovies(Subscriber<Movie> subscriber, int start, int count) {

        ApiService.getRetroftInstance()
                .newBuilder()
                .client(ApiService.getClient())
                .build().create(NetworkService.class)
                .getMovies(start, count)
                .map(Response::getSubjects)
                .flatMap(Observable::from).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
