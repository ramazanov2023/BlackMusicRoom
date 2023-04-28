package com.example.blackmusicroom.model.repository.playlists;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blackmusicroom.data.Playlist;

import java.util.ArrayList;

public class PlaylistControlImpl implements PlaylistControl {

    MutableLiveData<ArrayList<Playlist>> liveData;
    static PlaylistControlImpl instance;

    public static PlaylistControlImpl getInstance(){
        if(instance==null){
            instance = new PlaylistControlImpl();
        }
        return instance;
    }

    @Override
    public LiveData<ArrayList<Playlist>> getPlaylists(Context context) {
        if(liveData == null){
            liveData = new MutableLiveData<>();
            loadPlaylists(context);
        }
        return liveData;
    }


    private boolean validate(SQLiteDatabase database, String playlistName) {
        String table = "SELECT COUNT(*) FROM sqlite_master WHERE type=? AND name=?";
        Cursor cursor = database.rawQuery(table,new String[]{"table",playlistName});
        cursor.moveToFirst();
        return cursor.getInt(0) > 0;
    }

    @Override
    public boolean createPlaylist(Context context, String playlistName) {
        SQLHelper helper = new SQLHelper(context);

        SQLiteDatabase database = helper.getWritableDatabase();

        boolean tableAlreadyExists = validate(database,playlistName);

        if(tableAlreadyExists){
            return true;
        }else{
            PlaylistUser playlistUser = new PlaylistUserImpl();
            playlistUser.createPlaylistUser(database,playlistName);

            ContentValues values = new ContentValues();
            values.put(ContractPlaylistControl.PLAYLIST_NAME,playlistName);
            values.put(ContractPlaylistControl.PLAYLIST_COUNT_SONGS,0);

            database.insert(ContractPlaylistControl.PLAYLIST_TABLE_NAME, null,values);

            loadPlaylists(context);
            return false;
        }

//        PlaylistUser playlistUser = new PlaylistUserImpl();
//        playlistUser.createPlaylistUser(database,playlistName);
//
//        ContentValues values = new ContentValues();
//        values.put(ContractPlaylistControl.PLAYLIST_NAME,playlistName);
//        values.put(ContractPlaylistControl.PLAYLIST_COUNT_SONGS,0);
//
//        database.insert(ContractPlaylistControl.PLAYLIST_TABLE_NAME, null,values);
//
//        loadPlaylists(context);
    }

    @Override
    public void deletePlaylist(Context context, String playlistName, int playlistId) {
        SQLHelper helper = new SQLHelper(context);

        PlaylistUser playlistUser = new PlaylistUserImpl();
        playlistUser.deletePlaylistUser(helper.getWritableDatabase(),playlistName);

        String selection ="_id=?";
        String[] selectionArgs = new String[]{String.valueOf(playlistId)};
        helper.getWritableDatabase().delete(ContractPlaylistControl.PLAYLIST_TABLE_NAME,selection,selectionArgs);

        loadPlaylists(context);
    }

    private void loadPlaylists(Context context){
        ArrayList<Playlist> playlists = new ArrayList<>();
        SQLHelper helper = new SQLHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] projection = {
                        ContractPlaylistControl.PLAYLIST_ID,
                        ContractPlaylistControl.PLAYLIST_NAME,
                        ContractPlaylistControl.PLAYLIST_COUNT_SONGS
                };
                Cursor cursor = db.query(
                        ContractPlaylistControl.PLAYLIST_TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                while(cursor.moveToNext()){
                    playlists.add(new Playlist(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)
                    ));
                }
                liveData.postValue(playlists);
                db.close();
            }
        }).start();


    }

    public final static class ContractPlaylistControl implements BaseColumns {

        public static final String PLAYLIST_TABLE_NAME = "listPlaylists";
        public static final String PLAYLIST_ID = BaseColumns._ID;
        public static final String PLAYLIST_NAME = "plName";
        public static final String PLAYLIST_COUNT_SONGS = "plCountSongs";

        public static final String CREATE_PLAYLIST_CONTROL = "CREATE TABLE " + PLAYLIST_TABLE_NAME + " ("
                + PLAYLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAYLIST_NAME + " TEXT NOT NULL, "
                + PLAYLIST_COUNT_SONGS + " INTEGER NOT NULL);";
    }
}
