package com.company.instagramusersfeed;

import android.app.Application;

import com.company.instagramusersfeed.db.DaoUtils;
import com.company.instagramusersfeed.di.components.ApplicationComponent;
import com.company.instagramusersfeed.di.components.DaggerApplicationComponent;
import com.company.instagramusersfeed.di.modules.AppModule;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by gobinda on 10/12/16.
 */

public class App extends Application {


    public static final String TAG = "InstagramUserFeed";

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Fabric Setup
        Fabric.with(this, new Crashlytics());


        //init logger for debug builds
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag(TAG);
        }

        //Database Initialize
        DaoUtils.init(this);

        // init dagger Application component
        mComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    /**
     * Helper method to get ApplicationComponent
     *
     * @return
     */
    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
