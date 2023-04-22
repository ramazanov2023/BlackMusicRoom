package com.example.blackmusicroom.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.AllSongsDB;

import java.util.ArrayList;

public class PageSongsViewModel implements AllSongsDB {
    private ArrayList<Song> songs;
    private MutableLiveData<ArrayList<Song>> liveData;
    private static PageSongsViewModel instance;

    public static PageSongsViewModel getInstance(){
        if(instance==null){
            instance = new PageSongsViewModel();
        }
        return instance;
    }

    @Override
    public void loadSongs(Context context) {
        if(liveData==null){
            liveData = new MutableLiveData<>();
            songs = new ArrayList<>();
        }


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
        Log.e("challenge","2 - updatePlayer");
//        PlayerObserver.updatePlayer();

        liveData.setValue(songs);
    }

    @Override
    public LiveData<ArrayList<Song>> getSongs() {
        return liveData;
    }
}
