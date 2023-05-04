package com.example.blackmusicroom.model.repository.playlists;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.MyCallback;

import java.util.ArrayList;

public interface PlaylistControl {

    LiveData<ArrayList<Playlist>> getPlaylists(Context context);

    LiveData<ArrayList<Song>> getSongs(Context context);

    LiveData<ArrayList<Song>> getPlaylistSongs(Context context);

    boolean createPlaylist(Context context, String playlistName);

    void deletePlaylist(Context context, String playlistName,int playlistId);

    void addSongToPlaylist(Context context, String playlistName, ArrayList<Song> songs);

    void deleteSongFromPlaylist(Context context, String playlistName, ArrayList<Song> songs);

    void loadPlaylistSongs(Context context, String playlistName);

}
