package com.example.blackmusicroom.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class AddToPlaylistViewModel extends AndroidViewModel {

    public AddToPlaylistViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Playlist>> getPlaylists(){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        return playlistControl.getPlaylists(getApplication());
    }

    public void addToPlaylist(String playlistName,ArrayList<Song> song){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        playlistControl.addSongToPlaylist(getApplication(),playlistName,song);
    }
}
