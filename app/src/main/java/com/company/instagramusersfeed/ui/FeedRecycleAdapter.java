package com.company.instagramusersfeed.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.instagramusersfeed.R;
import com.company.instagramusersfeed.utils.CircleTransform;
import com.company.instagramusersfeed.instagram.api.model.FeedItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gobinda on 11/12/16.
 */

public class FeedRecycleAdapter extends RecyclerView.Adapter<FeedRecycleAdapter.ViewHolder> {

    private List<FeedItem> mFeedItems;
    private Context mContext;


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.icon1)
        ImageView imageView;
        @BindView(android.R.id.icon2)
        ImageView avatarView;
        @BindView(android.R.id.text1)
        TextView usernameView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public FeedRecycleAdapter(Context context, List<FeedItem> feedItems) {
        mContext = context;
        mFeedItems = feedItems;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.simple_list_item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FeedItem item = mFeedItems.get(position);
        String imageUrl = item.getImages().getThumbnail().getUrl();
        Picasso.with(mContext).load(imageUrl).into(holder.imageView);
        String avatarUrl = item.getUser().getProfilePicture();
        Picasso.with(mContext).load(avatarUrl).transform(new CircleTransform(item.getUser().getId())).into(holder.avatarView);
        holder.usernameView.setText(item.getUser().getFullName());

    }

    @Override
    public int getItemCount() {
        return mFeedItems == null ? 0 : mFeedItems.size();
    }
}
