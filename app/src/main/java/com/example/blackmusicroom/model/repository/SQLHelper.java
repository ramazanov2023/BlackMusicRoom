package com.example.blackmusicroom.model.repository;

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
        String CREATE_PLAYLIST_CONTROL = "CREATE TABLE " + PlaylistContract.PLAYLIST_CONTROL_TABLE_NAME + " ("
                + PlaylistContract.PLAYLIST_CONTROL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PlaylistContract.PLAYLIST_CONTROL_NAME + " TEXT NOT NULL, "
                + PlaylistContract.PLAYLIST_CONTROL_COUNT_SONGS + " TEXT NOT NULL);";
        db.execSQL(CREATE_PLAYLIST_CONTROL);

        PlaylistContract.createTable(db,PlaylistContract.FAVORITES_TABLE_NAME);

        ContentValues values = new ContentValues();
        values.put(PlaylistContract.PLAYLIST_CONTROL_NAME,PlaylistContract.FAVORITES_TABLE_NAME);
        values.put(PlaylistContract.PLAYLIST_CONTROL_COUNT_SONGS,0);
        db.insert(PlaylistContract.PLAYLIST_CONTROL_TABLE_NAME,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
