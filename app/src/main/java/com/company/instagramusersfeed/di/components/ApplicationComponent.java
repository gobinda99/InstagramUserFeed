package com.company.instagramusersfeed.di.components;

import com.company.instagramusersfeed.di.modules.AppModule;
import com.company.instagramusersfeed.ui.AbstractBaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gobinda on 10/12/16.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(AbstractBaseFragment fragment);
}
