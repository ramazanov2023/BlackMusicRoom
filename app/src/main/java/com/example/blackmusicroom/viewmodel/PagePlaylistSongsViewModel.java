package com.example.blackmusicroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class PagePlaylistSongsViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Song>> liveData;

    public PagePlaylistSongsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Song>> getPlaylistSongs() {
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        return playlistControl.getPlaylistSongs(getApplication());
    }
}
