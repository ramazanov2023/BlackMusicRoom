package com.example.blackmusicroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public class UserActionsViewModel extends AndroidViewModel {
    String playlistName;
    ArrayList<Song> songs;

    public UserActionsViewModel(@NonNull Application application) {
        super(application);
    }

    public void setPlaylistName(String playlistName){
        this.playlistName = playlistName;
    }

    public void addSongToPlaylist(String playlistName, ArrayList<Song> songs){
        this.songs = new ArrayList<>();
        for(int i =0; i<songs.size(); i++){
            Song currentSong = songs.get(i);
            this.songs.add(new Song(
                    currentSong.songId,
                    currentSong.songPath,
                    currentSong.songTitle,
                    currentSong.songArtist,
                    currentSong.songAlbum,
                    currentSong.songDateAdded,
                    currentSong.songDuration,
                    currentSong.songSize
            ));
        }
    }

    public String getPlaylistName(){
        return playlistName;
    }

    public ArrayList<Song> getSongs(){
        return songs;
    }
}
