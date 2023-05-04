package com.example.blackmusicroom.model.repository.playlists;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.MyCallback;
import com.example.blackmusicroom.model.repository.PlaylistContract;
import com.example.blackmusicroom.model.repository.SQLHelper;

import java.util.ArrayList;

public class PlaylistControlImpl implements PlaylistControl {

    MutableLiveData<ArrayList<Playlist>> liveDataPlaylists;
    MutableLiveData<ArrayList<Song>> liveDataSongs;
    MutableLiveData<ArrayList<Song>> liveDataPlaylistSongs;
    static PlaylistControlImpl instance;

    public static PlaylistControlImpl getInstance(){
        if(instance==null){
            instance = new PlaylistControlImpl();
        }
        return instance;
    }

    @Override
    public LiveData<ArrayList<Playlist>> getPlaylists(Context context) {
        if(liveDataPlaylists == null){
            liveDataPlaylists = new MutableLiveData<>();
            loadPlaylists(context);
        }
        return liveDataPlaylists;
    }

    @Override
    public LiveData<ArrayList<Song>> getSongs(Context context) {
        if(liveDataSongs == null){
            liveDataSongs = new MutableLiveData<>();
            loadSongs(context);
        }
        return liveDataSongs;
    }

    @Override
    public LiveData<ArrayList<Song>> getPlaylistSongs(Context context) {
        if(liveDataPlaylistSongs == null){
            liveDataPlaylistSongs = new MutableLiveData<>();
//            loadPlaylistSongs(context);
        }
        return liveDataPlaylistSongs;
    }

    @Override
    public boolean createPlaylist(Context context, String playlistName) {
        SQLHelper helper = new SQLHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        boolean tableAlreadyExists = validate(database,playlistName);

        if(tableAlreadyExists){
            return true;
        }else{
            PlaylistContract.createTable(database,playlistName);

            ContentValues values = new ContentValues();
            values.put(PlaylistContract.PLAYLIST_CONTROL_NAME,playlistName);
//            long count = DatabaseUtils.queryNumEntries(database,playlistName);
            values.put(PlaylistContract.PLAYLIST_CONTROL_COUNT_SONGS,0);

            database.insert(PlaylistContract.PLAYLIST_CONTROL_TABLE_NAME, null,values);

            loadPlaylists(context);
            return false;
        }

    }

    @Override
    public void deletePlaylist(Context context, String playlistName, int playlistId) {
        SQLHelper helper = new SQLHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        PlaylistContract.deleteTable(database,playlistName);

        String selection ="_id=?";
        String[] selectionArgs = new String[]{String.valueOf(playlistId)};
        helper.getWritableDatabase().delete(PlaylistContract.PLAYLIST_CONTROL_TABLE_NAME,selection,selectionArgs);

        loadPlaylists(context);
    }

    @Override
    public void addSongToPlaylist(Context context, String playlistName, ArrayList<Song> songs) {
        SQLHelper helper = new SQLHelper(context);

        SQLiteDatabase database = helper.getReadableDatabase();

        for(int i = 0; i< songs.size(); i++) {
            Song s = songs.get(i);
            ContentValues values = new ContentValues();
            values.put(PlaylistContract.ID, s.songId);
            values.put(PlaylistContract.DATA, s.songPath);
            values.put(PlaylistContract.TITLE, s.songTitle);
            values.put(PlaylistContract.ARTIST, s.songArtist);
            values.put(PlaylistContract.ALBUM, s.songAlbum);
            values.put(PlaylistContract.DATE_ADDED, s.songDateAdded);
            values.put(PlaylistContract.DURATION, s.songDuration);
            values.put(PlaylistContract.SIZE, s.songSize);
            Log.e("traking"," 7 - playlistName - " + playlistName);
            database.insert(playlistName, null, values);
        }
    }

    @Override
    public void deleteSongFromPlaylist(Context context, String playlistName, ArrayList<Song> songs) {
        SQLHelper helper = new SQLHelper(context);

        SQLiteDatabase database = helper.getReadableDatabase();

        for(Song s:songs) {
            String selection = "_id=?";
            String[] selectionArgs = new String[]{String.valueOf(s.songId)};
            database.delete(playlistName, selection, selectionArgs);
        }
    }

    @Override
    public void loadPlaylists(Context context){
        ArrayList<Playlist> playlists = new ArrayList<>();
        SQLHelper helper = new SQLHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] projection = {
                        PlaylistContract.PLAYLIST_CONTROL_ID,
                        PlaylistContract.PLAYLIST_CONTROL_NAME,
//                        PlaylistContract.PLAYLIST_CONTROL_COUNT_SONGS
                };
                Cursor cursor = db.query(
                        PlaylistContract.PLAYLIST_CONTROL_TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );


                while(cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    long count = DatabaseUtils.queryNumEntries(db,name);
                    playlists.add(new Playlist(
                            id,
                            name,
                            count
                    ));
                }
                liveDataPlaylists.postValue(playlists);
                db.close();
            }
        }).start();


    }

    private void loadSongs(Context context){
        ArrayList<Song> songs = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("traking"," 2 - onChanged");
                String sortOrder = null;
                String[] projection = {
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.DATE_ADDED,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.SIZE
                };

                String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";

//        switch (idSort) {
//            case 0:
//                sortOrder = MediaStore.Audio.Media.TITLE;
//                break;
//            case 1:
//                sortOrder = MediaStore.Audio.Media.ARTIST;
//                break;
//            case 2:
//                sortOrder = MediaStore.Audio.Media.ALBUM;
//                break;
//            case 3:
//                sortOrder = MediaStore.Audio.Media.COMPOSER;
//                break;
//            case 4:
//                sortOrder = MediaStore.Audio.Media.YEAR;
//                break;
//            case 5:
//                sortOrder = MediaStore.Audio.Media.DATE_ADDED;
//                break;
//            case 6:
//                sortOrder = MediaStore.Audio.Media.DURATION;
//                break;
//            case 7:
//                sortOrder = MediaStore.Audio.Media.SIZE;
//                break;
//            default:
//                sortOrder = "title";
//        }

                Cursor cursor = context.getContentResolver().query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        projection,
                        selection,
                        null,
                        sortOrder);
                Log.e("traking"," 3 - query");

                while (cursor.moveToNext()) {
                    Song singleSong = new Song(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7));
                    songs.add(singleSong);
                }
                Log.e("traking"," 4 - songs.add");
               liveDataSongs.postValue(songs);
//                myCallback.onLoad(songs);
            }
        }).start();


    }

    @Override
    public void loadPlaylistSongs(Context context, String playlistName){
        SQLHelper helper = new SQLHelper(context);
        ArrayList<Song> playlistSongs = new ArrayList<>();

        SQLiteDatabase database = helper.getReadableDatabase();

        String sortOrder = null;
//        boolean thread = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] projection = {
                        PlaylistContract.ID,
                        PlaylistContract.DATA,
                        PlaylistContract.TITLE,
                        PlaylistContract.ARTIST,
                        PlaylistContract.ALBUM,
                        PlaylistContract.DATE_ADDED,
                        PlaylistContract.DURATION,
                        PlaylistContract.SIZE
                };
                Cursor cursor = database.query(
                        playlistName,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder
                );
                while(cursor.moveToNext()){
                    playlistSongs.add(new Song(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7)));

                }
                liveDataPlaylistSongs.postValue(playlistSongs);
//                myCallback.onLoad(playlistSongs);
            }
        }).start();
        Log.e("testThread", " 1  - thread.isAlive() ");


    }

    private boolean validate(SQLiteDatabase database, String playlistName) {
        if(TextUtils.isEmpty(playlistName.trim())){
            return false;
        }
        String table = "SELECT COUNT(*) FROM sqlite_master WHERE type=? AND name=?";
        Cursor cursor = database.rawQuery(table,new String[]{"table",playlistName});
        cursor.moveToFirst();
        return cursor.getInt(0) > 0;
    }


}
