package com.example.blackmusicroom.model.repository.playlists;

import android.database.sqlite.SQLiteDatabase;

public class PlaylistUserImpl implements PlaylistUser{
    private final String U_ID = "_id";
    private final String U_DATA = "fPath";
    private final String U_TITLE = "fTitle";
    private final String U_ARTIST = "fArtist";
    private final String U_ALBUM = "fAlbum";
    private final String U_DATE_ADDED = "fDateAdded";
    private final String U_DURATION = "fDuration";
    private final String U_SIZE = "fSize";


    @Override
    public void createPlaylistUser(SQLiteDatabase database, String tableName) {
        String CREATE_PLAYLIST_USER = "CREATE TABLE " + tableName + " ("
                + U_ID + " INTEGER PRIMARY KEY, "
                + U_DATA + " INTEGER NOT NULL, "
                + U_TITLE + " TEXT NOT NULL, "
                + U_ARTIST + " TEXT NOT NULL, "
                + U_ALBUM + " TEXT NOT NULL, "
                + U_DATE_ADDED + " TEXT NOT NULL, "
                + U_DURATION + " TEXT NOT NULL, "
                + U_SIZE + " TEXT NOT NULL);";
        database.execSQL(CREATE_PLAYLIST_USER);
    }

    @Override
    public void deletePlaylistUser(SQLiteDatabase database, String tableName) {
        String DELETE_PLAYLIST_USER = "DROP TABLE IF EXISTS " + tableName;
        database.execSQL(DELETE_PLAYLIST_USER);
    }
}
