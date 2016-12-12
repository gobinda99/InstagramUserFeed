package com.company.instagramusersfeed.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by gobinda on 10/12/16.
 */

public class DaoUtils {

    private static OrmSqlLiteHelper sDbHelper = null;

    public static void init(Context context) {
        if (sDbHelper == null) {
            sDbHelper = OpenHelperManager.getHelper(context, OrmSqlLiteHelper.class);
        }
    }

    public static void shutdown() {
        if (sDbHelper != null) {
            OpenHelperManager.releaseHelper();
            sDbHelper = null;
        }
    }

    public static ConnectionSource getConnectionSource() {
        return sDbHelper.getConnectionSource();
    }

    @SuppressWarnings("unchecked")
    public static <T> Dao<T, ?> getDao(T ob) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) sDbHelper.getDao(ob.getClass());
        return dao;
    }

    private static void checkIfInitiated() {
        if (sDbHelper == null) {
            throw new DbNotInitializeException(" Database is not initialized! Call DaoUtils.init(context) to initialize it.");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T createOrUpdate(T ob) throws SQLException {
        checkIfInitiated();

        Dao<Object, ?> dao = (Dao<Object, ?>) sDbHelper.getDao(ob.getClass());
        return (T) dao.createOrUpdate(ob);
    }

}
