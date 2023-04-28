package com.example.blackmusicroom.model.repository.playlists;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Playlist;

import java.util.ArrayList;

public interface PlaylistControl {
    LiveData<ArrayList<Playlist>> getPlaylists(Context context);

    boolean createPlaylist(Context context, String playlistName);
    void deletePlaylist(Context context, String playlistName,int playlistId);
}
