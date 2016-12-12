package com.company.instagramusersfeed.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by gobinda on 12/12/16.
 */

public class Utils {
    public static boolean isOnline(Context context) {
        ConnectivityManager c = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = c.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private Utils() {
    }

    ;
}
