
package com.company.instagramusersfeed.commons;

import android.support.v4.app.LoaderManager.LoaderCallbacks;

import com.company.instagramusersfeed.commons.AsyncResourceLoader;


public interface Loadable<T> extends LoaderCallbacks<AsyncResourceLoader.Result<T>> {

    boolean hasMore();

    boolean hasError();

    void init();

    void destroy();

    boolean isReadyToLoadMore();

    void loadMore();
}
