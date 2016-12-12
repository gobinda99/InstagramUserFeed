package com.company.instagramusersfeed.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.instagramusersfeed.R;
import com.company.instagramusersfeed.commons.AsyncResourceLoader;
import com.company.instagramusersfeed.commons.Loadable;
import com.company.instagramusersfeed.commons.LoadableDecorator;
import com.company.instagramusersfeed.instagram.api.model.Feed;
import com.company.instagramusersfeed.instagram.api.model.FeedItem;
import com.company.instagramusersfeed.commons.RecycleViewScrollListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by gobinda on 11/12/16.
 */

public class FeedFragment extends AbstractBaseFragment implements LoaderManager.LoaderCallbacks<AsyncResourceLoader.Result<Feed>> {

    @BindView(R.id.feed_recycle_view)
    RecyclerView mRecyclerView;
    Loadable<Feed> mLoadable;
    FeedRecycleAdapter mFeedAdapter;
    List<FeedItem> mFeedItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflateLayout(R.layout.fragment_feed, container, false);
        setUpAllRecycleView();
        return v;
    }

    private void setUpAllRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        HorizontalDividerItemDecoration decoration = new HorizontalDividerItemDecoration.Builder(getContext()).color(Color.TRANSPARENT)
                .sizeResId(R.dimen.divider)
                .build();
        mRecyclerView.addItemDecoration(decoration);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFeedItems = new ArrayList<>();
        mFeedAdapter = new FeedRecycleAdapter(getContext(), mFeedItems);
        mRecyclerView.setAdapter(mFeedAdapter);
        mLoadable = new FeedLoadable(getLoaderManager(), 0,
                new LoadableDecorator<Feed>(this, 0, this));
        mRecyclerView.addOnScrollListener(new RecycleViewScrollListener(mLoadable));
        mLoadable.init();
    }

    @Override
    public Loader<AsyncResourceLoader.Result<Feed>> onCreateLoader(int id, Bundle args) {
        return new InstaFeedLoader(getActivity(), FeedLoadable.getMaxId(args));
    }

    @Override
    public void onLoadFinished(Loader<AsyncResourceLoader.Result<Feed>> loader, AsyncResourceLoader.Result<Feed> data) {
        final boolean clear = !((InstaFeedLoader) loader).isLoadMoreRequest();
        if (data.data != null) {
            if (clear) {
                mFeedItems.clear();
            }
            mFeedItems.addAll(data.data.getData());
            mFeedAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onLoaderReset(Loader<AsyncResourceLoader.Result<Feed>> loader) {
        if (mFeedItems != null) {
            mFeedItems.clear();
            mFeedAdapter.notifyDataSetChanged();
        }
    }
}
