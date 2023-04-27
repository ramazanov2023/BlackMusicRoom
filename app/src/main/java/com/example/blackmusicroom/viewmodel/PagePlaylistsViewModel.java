package com.example.blackmusicroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class PagePlaylistsViewModel extends AndroidViewModel {

    public PagePlaylistsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Playlist>> getPlaylists(){
        PlaylistControl playlists = PlaylistControlImpl.getInstance();
        return playlists.getPlaylists(getApplication());
    }

    public void createPlaylist(String playlistName){
        PlaylistControl playlists = PlaylistControlImpl.getInstance();
        playlists.createPlaylist(getApplication(),playlistName);
    }
}
