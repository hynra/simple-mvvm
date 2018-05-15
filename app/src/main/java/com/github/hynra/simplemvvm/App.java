package com.github.hynra.simplemvvm;

import android.app.Application;
import android.content.Context;

/**
 * Created by hynra on 15/05/2018.
 */

public class App extends Application {

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

}
