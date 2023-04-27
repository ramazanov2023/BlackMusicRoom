package com.example.blackmusicroom.model.repository.playlists;

import android.provider.BaseColumns;
import android.provider.MediaStore;

public class PlaylistFavoritesImpl {




    public final static class ContractPlaylistFavorites implements BaseColumns {

        public static final String F_TABLE_NAME = "Favorites";
        public static final String F_ID = BaseColumns._ID;
        public static final String F_DATA = "fPath";
        public static final String F_TITLE = "fTitle";
        public static final String F_ARTIST = "fArtist";
        public static final String F_ALBUM = "fAlbum";
        public static final String F_DATE_ADDED = "fDateAdded";
        public static final String F_DURATION = "fDuration";
        public static final String F_SIZE = "fSize";

        public static final String CREATE_PLAYLIST_FAVORITES = "CREATE TABLE " + F_TABLE_NAME + " ("
                + F_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + F_DATA + " INTEGER NOT NULL, "
                + F_TITLE + " TEXT NOT NULL, "
                + F_ARTIST + " TEXT NOT NULL, "
                + F_ALBUM + " TEXT NOT NULL, "
                + F_DATE_ADDED + " TEXT NOT NULL, "
                + F_DURATION + " TEXT NOT NULL, "
                + F_SIZE + " TEXT NOT NULL);";
    }
}
