package com.github.hynra.simplemvvm.model.data;

import com.github.hynra.simplemvvm.model.entity.Movie;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hynra on 15/05/2018.
 */

public interface NetworkService {

    @GET("top250")
    Observable<Response<List<Movie>>> getMovies(@Query("start") int start, @Query("count") int count);
}
