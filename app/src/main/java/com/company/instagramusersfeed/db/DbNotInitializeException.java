package com.company.instagramusersfeed.db;

/**
 * Created by gobinda on 10/12/16.
 */

public class DbNotInitializeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DbNotInitializeException(String aMessage) {
        super(aMessage);
    }
}
