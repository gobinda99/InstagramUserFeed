package com.company.instagramusersfeed.db;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.company.instagramusersfeed.BuildConfig;
import com.company.instagramusersfeed.db.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

import timber.log.Timber;

/**
 * Created by gobinda on 10/12/16.
 */

public class OrmSqlLiteHelper extends OrmLiteSqliteOpenHelper {

    private final static String DB_NAME = BuildConfig.DATA_BASE_NAME;
    private final static int DB_VERSION = BuildConfig.DB_VERSION;


    public OrmSqlLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @TargetApi(16)
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            Timber.e(e, "create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //TODO here we have to implement for data migration if required any
    }
}
