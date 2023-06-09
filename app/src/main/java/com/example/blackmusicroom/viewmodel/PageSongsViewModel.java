package com.example.blackmusicroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class PageSongsViewModel extends AndroidViewModel {

    public PageSongsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Song>> getSongs() {
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        return playlistControl.getSongs(getApplication());
    }


}
