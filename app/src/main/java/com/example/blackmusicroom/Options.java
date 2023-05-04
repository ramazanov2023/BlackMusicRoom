package com.example.blackmusicroom;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public interface Options {
    int NO_ACTION = -1;
    int OPEN_SONG_OPTIONS = 0;
    int OPEN_PLAYLIST_OPTIONS = 1;
    int OPEN_PLAYLIST_LIST = 2;
    int ADD_SONG_TO_PLAYLIST = 3;

    void setAction(int action, String playlistName, int playlistId, ArrayList<Song> song);
}
