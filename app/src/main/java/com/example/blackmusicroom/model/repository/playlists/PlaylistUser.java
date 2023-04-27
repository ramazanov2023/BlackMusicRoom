package com.example.blackmusicroom.model.repository.playlists;

import android.database.sqlite.SQLiteDatabase;

public interface PlaylistUser {
    void createPlaylistUser(SQLiteDatabase database, String tableName);
}
