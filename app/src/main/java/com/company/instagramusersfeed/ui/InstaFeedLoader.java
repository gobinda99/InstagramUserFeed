package com.company.instagramusersfeed.ui;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.company.instagramusersfeed.R;
import com.company.instagramusersfeed.commons.AsyncResourceLoader;
import com.company.instagramusersfeed.commons.Constants;
import com.company.instagramusersfeed.commons.OAuth;
import com.company.instagramusersfeed.db.models.User;
import com.company.instagramusersfeed.instagram.api.Instagram;
import com.company.instagramusersfeed.instagram.api.InstagramRequestInitializer;
import com.company.instagramusersfeed.instagram.api.InstagramScopes;
import com.company.instagramusersfeed.instagram.api.model.Feed;
import com.company.instagramusersfeed.instagram.api.model.FeedItem;
import com.company.instagramusersfeed.instagram.api.model.Meta;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpStatusCodes;

import java.util.Arrays;

import timber.log.Timber;

/**
 * Created by gobinda on 11/12/16.
 */

public class InstaFeedLoader extends AsyncResourceLoader<Feed> {
    private final String nextMaxId;
    private final OAuth oauth;

    public InstaFeedLoader(FragmentActivity activity, String nextMaxId) {
        super(activity);
        this.nextMaxId = nextMaxId;
        oauth = OAuth.newInstance(activity.getApplicationContext(),
                activity.getSupportFragmentManager(),
                new ClientParametersAuthentication(Constants.CLIENT_ID, null),
                Constants.AUTHORIZATION_IMPLICIT_SERVER_URL,
                Constants.TOKEN_SERVER_URL,
                Constants.REDIRECT_URL,
                Arrays.asList(InstagramScopes.BASIC, InstagramScopes.COMMENTS,
                        InstagramScopes.LIKES, InstagramScopes.RELATIONSHIPS));
    }

    public boolean isLoadMoreRequest() {
        return !TextUtils.isEmpty(nextMaxId);
    }

    @Override
    public Feed loadResourceInBackground() throws Exception {
        Credential credential = oauth.authorizeImplicitly(
                getContext().getString(R.string.token_instagram)).getResult();
        Timber.i("token: %s", credential.getAccessToken());

        Instagram instagram =
                new Instagram.Builder(OAuth.HTTP_TRANSPORT, OAuth.JSON_FACTORY, null)
                        .setApplicationName(getContext().getString(R.string.application_name))
                        .setInstagramRequestInitializer(
                                new InstagramRequestInitializer(credential))
                        .build();
        Instagram.Users.Self.FeedRequest request = instagram.users().self().feed();
        if (isLoadMoreRequest()) {
            request.setMaxId(nextMaxId);
        }
        Feed feed = request.execute();
        if(feed != null && feed.getData() != null && feed.getData().size()> 0) {
            FeedItem feedItem = feed.getData().get(0);
             com.company.instagramusersfeed.instagram.api.model.User aUser = feedItem.getUser();
            if(aUser != null) {
                User user = new User(aUser.getId(),aUser.getUsername(),aUser.getFullName(),aUser.getProfilePicture());
                user.save();
            }
        }
        return feed;
    }

    @Override
    public void updateErrorStateIfApplicable(AsyncResourceLoader.Result<Feed> result) {
        Feed data = result.data;
        Meta meta = data.getMeta();
        result.success = meta.getCode() == HttpStatusCodes.STATUS_CODE_OK;
        result.errorMessage = result.success ? null :
                (meta.getCode() + " " + meta.getErrorType() + ": " + meta.getErrorMessage());
    }

}
