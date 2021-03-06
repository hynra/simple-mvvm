package com.github.hynra.simplemvvm.model.data;

import com.github.hynra.simplemvvm.App;
import com.github.hynra.simplemvvm.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hynra on 15/05/2018.
 */

public class ApiService {

    private static Retrofit retrofit;

    public static Retrofit getRetroftInstance() {
        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(App.getContext().getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }).build();
        return okHttpClient;
    }

}
