package com.example.blackmusicroom.viewmodel;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.AllSongsDB;
import com.example.blackmusicroom.model.repository.AllSongsDBImpl;
import com.example.blackmusicroom.model.repository.MyCallback;

import java.util.ArrayList;

public class PageSongsViewModel extends AndroidViewModel {
//    private ArrayList<Song> songs;
    private MutableLiveData<ArrayList<Song>> liveData;
    private static PageSongsViewModel instance;

    public PageSongsViewModel(@NonNull Application application) {
        super(application);
    }

//    public static PageSongsViewModel getInstance(){
//        if(instance==null){
//            instance = new PageSongsViewModel();
//        }
//        return instance;
//    }


    public LiveData<ArrayList<Song>> getSongs() {
        Log.e("challenge","4 - getSongs");
        if(liveData==null){
            liveData = new MutableLiveData<>();
            Log.e("challenge","5 - if(liveData==null)");
            loadSongs();
        }
        return liveData;
    }

    private void loadSongs(){
        Log.e("challenge","6 - loadSongs");
        AllSongsDB allSongsDB = AllSongsDBImpl.getInstance();
        allSongsDB.loadSongs(getApplication(), new MyCallback() {
            @Override
            public void onLoad(ArrayList<Song> songs) {
                Log.e("challenge","10 - onLoad " + songs.size());
                liveData.postValue(songs);
            }
        });
    }

//
//    public LiveData<ArrayList<Song>> getSongs() {
//        return liveData;
//    }
}
