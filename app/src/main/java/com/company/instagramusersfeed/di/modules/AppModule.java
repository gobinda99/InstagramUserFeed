package com.company.instagramusersfeed.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gobinda on 10/12/16.
 */
@Module
public class AppModule {
    private final Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providePreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }
}
