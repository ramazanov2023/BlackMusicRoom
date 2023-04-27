package com.example.blackmusicroom.model.repository.playlists;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "player.db";
    public static final int DATABASE_VERSION = 1;

    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlaylistControlImpl.ContractPlaylistControl.CREATE_PLAYLIST_CONTROL);
        db.execSQL(PlaylistFavoritesImpl.ContractPlaylistFavorites.CREATE_PLAYLIST_FAVORITES);

        ContentValues values = new ContentValues();
        values.put(PlaylistControlImpl.ContractPlaylistControl.PLAYLIST_NAME,PlaylistFavoritesImpl.ContractPlaylistFavorites.F_TABLE_NAME);
        values.put(PlaylistControlImpl.ContractPlaylistControl.PLAYLIST_COUNT_SONGS,0);
        db.insert(PlaylistControlImpl.ContractPlaylistControl.PLAYLIST_TABLE_NAME,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
