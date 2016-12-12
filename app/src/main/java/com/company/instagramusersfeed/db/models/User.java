package com.company.instagramusersfeed.db.models;


import com.company.instagramusersfeed.db.DaoUtils;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;

import timber.log.Timber;

/**
 * Created by gobinda on 12/12/16.
 */

@DatabaseTable(tableName = "users")
public class User {
    @DatabaseField(columnName = "id", id = true)
    private String mId;

    @DatabaseField(columnName = "username")
    private String mUsername;

    @DatabaseField(columnName = "full_name")
    private String mFullName;

    @DatabaseField(columnName = "profile_picture")
    private String mProfilePicture;

    public User() {
    }

    public User(String id, String username, String fullName, String profilePicture) {
        this.mId = id;
        this.mUsername = username;
        this.mFullName = fullName;
        this.mProfilePicture = profilePicture;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        this.mFullName = fullName;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.mProfilePicture = profilePicture;
    }

    public void save() {
        try {
            DaoUtils.createOrUpdate(this);
        } catch (SQLException e) {
            Timber.e(e,"sql exp");
        }
    }

}
