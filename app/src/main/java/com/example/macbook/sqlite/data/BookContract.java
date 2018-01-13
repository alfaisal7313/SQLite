package com.example.macbook.sqlite.data;

import android.provider.BaseColumns;

/**
 * Created by MacBook on 1/8/18.
 */

public class BookContract {

    public BookContract() {
    }

    public static class BookEntry implements BaseColumns{
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "books";
        public static final String COLOUMN_TITLE = "title";
        public static final String COLOUMN_AUTHOR = "author";
        public static final String COLOUMN_CATEGORIES = "categories";
        public static final String COLOUMN_RELEASE = "release";
    }
}
