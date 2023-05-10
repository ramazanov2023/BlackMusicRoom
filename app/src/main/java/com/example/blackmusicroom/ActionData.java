package com.example.blackmusicroom;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;

import java.util.ArrayList;

public class ActionData {
    private ArrayList<Song> song;
    private String playlistName;
    int playlistId;
    public int playlistNum;
    int songId;
    public int mediaSongId = -1;
    private static ActionData instance;

    public static ActionData getInstance(){
        if(instance==null){
            instance = new ActionData();
        }
        return instance;
    }

    public void addSong(int songId, ArrayList<Song> song){
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
        this.songId = songId;
    }

    public void addSong(Song singleSong){
        song = new ArrayList<>();
        song.add(singleSong);
    }

    public void addPlaylistData(String playlistName, int playlistId){
        this.playlistName = playlistName;
        this.playlistId = playlistId;
    }

    public String getPlaylistName(){
        return playlistName;
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

    public void addPlaylistNum(int i) {
        playlistNum = i;
    }

    public void addMediaSongId(int i) {
        mediaSongId = i;
    }
}
