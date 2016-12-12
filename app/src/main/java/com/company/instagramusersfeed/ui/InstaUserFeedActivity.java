
package com.company.instagramusersfeed.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;


import com.company.instagramusersfeed.R;
import com.company.instagramusersfeed.commons.Constants;
import com.company.instagramusersfeed.utils.Utils;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import java.util.logging.Logger;

public class InstaUserFeedActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        if (!Utils.isOnline(this)) {
            Crouton.makeText(this, getString(R.string.please_connect_internet)
                    , Style.ALERT).show();
        }

        FragmentManager fm = getSupportFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            FeedFragment list = new FeedFragment();
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }
    }

    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

}
