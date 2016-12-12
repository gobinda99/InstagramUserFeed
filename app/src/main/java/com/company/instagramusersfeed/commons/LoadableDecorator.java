
package com.company.instagramusersfeed.commons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.SparseBooleanArray;

import com.google.api.client.util.Preconditions;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import timber.log.Timber;

public class LoadableDecorator<T> implements LoaderCallbacks<AsyncResourceLoader.Result<T>> {

    private final LoaderCallbacks<AsyncResourceLoader.Result<T>> mCallbacks;
    private final int mLoaderId;
    private final Fragment mListFragment;
    private final SparseBooleanArray mActive = new SparseBooleanArray();

    public LoadableDecorator(LoaderCallbacks<AsyncResourceLoader.Result<T>> callbacks, int loaderId,
                             Fragment listFragment) {
        super();
        this.mCallbacks = Preconditions.checkNotNull(callbacks);
        this.mLoaderId = loaderId;
        this.mListFragment = Preconditions.checkNotNull(listFragment);
    }

    @Override
    public Loader<AsyncResourceLoader.Result<T>> onCreateLoader(int id, Bundle args) {
        mActive.put(id, true);
        if (mLoaderId == id) {
            //todo setting of progress loader set on recycle adapter data
        }
        updateWindowIndeterminateProgress();
        return mCallbacks.onCreateLoader(id, args);
    }

    @Override
    public void onLoadFinished(Loader<AsyncResourceLoader.Result<T>> loader, AsyncResourceLoader.Result<T> result) {
        mCallbacks.onLoadFinished(loader, result);
        mActive.delete(loader.getId());
        if (mLoaderId == loader.getId()) {
            //todo setting of progress loader based on fragment visible

            if (!result.success) {
                Crouton.makeText(mListFragment.getActivity(),
                        result.errorMessage, Style.ALERT).show();
                Timber.e(result.exception, "error %s", result.errorMessage);
            }
        }
        updateWindowIndeterminateProgress();
    }

    @Override
    public void onLoaderReset(Loader<AsyncResourceLoader.Result<T>> loader) {
        mCallbacks.onLoaderReset(loader);
        mActive.delete(loader.getId());
        if (mLoaderId == loader.getId()) {
            //todo setting of progress loader based on fragment visible
        }
        updateWindowIndeterminateProgress();
    }

    private void updateWindowIndeterminateProgress() {
        //todo  set progress loader visable
    }


}
