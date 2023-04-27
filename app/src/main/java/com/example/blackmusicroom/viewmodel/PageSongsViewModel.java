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
    private MutableLiveData<ArrayList<Song>> liveData;

    public PageSongsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Song>> getSongs() {
        if(liveData==null){
            liveData = new MutableLiveData<>();
            loadSongs();
        }
        return liveData;
    }

    private void loadSongs(){
        AllSongsDB allSongsDB = AllSongsDBImpl.getInstance();
        allSongsDB.loadSongs(getApplication(), new MyCallback() {
            @Override
            public void onLoad(ArrayList<Song> songs) {
                liveData.postValue(songs);
            }
        });
    }
}
