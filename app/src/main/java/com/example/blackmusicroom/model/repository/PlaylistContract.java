package com.example.blackmusicroom.model.repository;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class PlaylistContract implements BaseColumns {


    public static final String PLAYLIST_CONTROL_TABLE_NAME = "listPlaylists";
    public static final String FAVORITES_TABLE_NAME = "Favorites";


    public static final String PLAYLIST_CONTROL_ID = BaseColumns._ID;
    public static final String PLAYLIST_CONTROL_NAME = "plName";
    public static final String PLAYLIST_CONTROL_COUNT_SONGS = "plCountSongs";

    public static final String ID = "_id";
    public static final String DATA = "sPath";
    public static final String TITLE = "sTitle";
    public static final String ARTIST = "sArtist";
    public static final String ALBUM = "sAlbum";
    public static final String DATE_ADDED = "sDateAdded";
    public static final String DURATION = "sDuration";
    public static final String SIZE = "sSize";

    public static void createTable(SQLiteDatabase database, String tableName) {
        String CREATE_PLAYLIST_USER = "CREATE TABLE " + tableName + " ("
                + ID + " INTEGER NOT NULL, "
                + DATA + " TEXT NOT NULL, "
                + TITLE + " TEXT NOT NULL, "
                + ARTIST + " TEXT NOT NULL, "
                + ALBUM + " TEXT NOT NULL, "
                + DATE_ADDED + " TEXT NOT NULL, "
                + DURATION + " TEXT NOT NULL, "
                + SIZE + " TEXT NOT NULL);";
        database.execSQL(CREATE_PLAYLIST_USER);

    }

    public static void deleteTable(SQLiteDatabase database, String tableName) {
        String DELETE_PLAYLIST_USER = "DROP TABLE IF EXISTS " + tableName;
        database.execSQL(DELETE_PLAYLIST_USER);
    }
}
