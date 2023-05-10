package com.example.blackmusicroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class BottomPlayerViewModel extends AndroidViewModel {

    public BottomPlayerViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Song>> getSongs(){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        return playlistControl.getSongs(getApplication());
    }

    public LiveData<ArrayList<Song>> getPlaylistSongs(String playlistName){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        LiveData<ArrayList<Song>> liveData = playlistControl.getPlaylistSongs(getApplication());
        playlistControl.loadPlaylistSongs(getApplication(),playlistName);
        return liveData;
    }

    public LiveData<Song> getSong() {
        Player player = PlayerImpl.getInstance();
        return player.getSong();
    }
}
