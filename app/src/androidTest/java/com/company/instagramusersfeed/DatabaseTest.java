package com.company.instagramusersfeed;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.company.instagramusersfeed.db.DaoUtils;
import com.company.instagramusersfeed.db.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by gobinda on 12/12/16.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {


    @Test
    public void databaseName() throws Exception {
        assertEquals("instagram_user_feed.db", BuildConfig.DATA_BASE_NAME);
    }

    @Test
    public void databaseInitialized() throws Exception {
        ConnectionSource connectionSource = DaoUtils.getConnectionSource();
        assertNotNull(connectionSource);
    }

    @Test
    public void saveOrUpdateField() throws Exception {
        User user = new User("1", "test", "test", null);
        Object object = DaoUtils.createOrUpdate(user);
        Dao.CreateOrUpdateStatus status = (Dao.CreateOrUpdateStatus) object;
        assertTrue(status.isCreated() || status.isUpdated());
    }

}
