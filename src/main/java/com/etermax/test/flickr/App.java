package com.etermax.test.flickr;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mendez Fernandez on 28/12/2016.
 */

public class App extends Application {

    private static App sInstance;


    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        sInstance = this;
    }

    public static boolean checkInternetConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) sInstance.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = connectivityManager.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        return i.isAvailable();
    }

}
