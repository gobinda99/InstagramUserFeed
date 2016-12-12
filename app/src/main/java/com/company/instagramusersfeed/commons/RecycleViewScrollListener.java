
package com.company.instagramusersfeed.commons;

import android.support.v7.widget.RecyclerView;

import com.company.instagramusersfeed.utils.RecyclerViewPositionHelper;
import com.google.api.client.util.Preconditions;

public class RecycleViewScrollListener extends RecyclerView.OnScrollListener {

    private final Loadable<?> mLoadable;

    public RecycleViewScrollListener(Loadable<?> loadable) {
        super();
        this.mLoadable = Preconditions.checkNotNull(loadable);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerViewPositionHelper mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mRecyclerViewHelper.getItemCount();
        int firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();
        if (firstVisibleItem + visibleItemCount >= totalItemCount) {
            if (mLoadable.isReadyToLoadMore()) {
                mLoadable.loadMore();
            }
        }
    }


}
