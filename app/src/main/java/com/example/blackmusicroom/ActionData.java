package com.example.blackmusicroom;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class ActionData {
    private ArrayList<Song> song;
    private static ActionData instance;

    public static ActionData getInstance(){
        if(instance==null){
            instance = new ActionData();
        }
        return instance;
    }

    public void addSong(ArrayList<Song> song){
//        this.song = song;
        this.song = new ArrayList<>();
        for (Song s:song){
            this.song.add(new Song(
                    s.songId,
                    s.songPath,
                    s.songTitle,
                    s.songArtist,
                    s.songAlbum,
                    s.songDateAdded,
                    s.songDuration,
                    s.songSize
                    ));
        }
    }

    public void addToPlaylist(String playlistName){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        playlistControl.addSongToPlaylist(null,playlistName,song);
        song = null;
    }
}
