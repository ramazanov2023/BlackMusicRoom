package com.example.blackmusicroom;

import android.content.Context;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;

import java.util.ArrayList;

public class ActionData {
    private ArrayList<Song> song;
    private String playlistName;
    int playlistId;
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
        this.playlistName = playlistName;
    }

    public void addPlaylistData(String playlistName, int playlistId){
        this.playlistName = playlistName;
        this.playlistId = playlistId;
    }

    public void addToPlaylist(Context context, String playlistName){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        playlistControl.addSongToPlaylist(context,playlistName,song);
        song = null;
    }

    public void deleteFromPlaylist(Context context){
        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        playlistControl.deleteSongFromPlaylist(context,playlistName,song);
        song = null;
//        playlistName = null;
    }
}
