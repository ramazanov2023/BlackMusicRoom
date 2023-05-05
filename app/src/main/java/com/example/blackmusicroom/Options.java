package com.example.blackmusicroom;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public interface Options {
    int NO_ACTION = -1;
    int OPEN_SONG_OPTIONS = 0;
    int OPEN_PLAYLIST_OPTIONS = 1;
    int OPEN_ADD_TO_PLAYLIST = 2;
    int ADD_SONG_TO_PLAYLIST = 3;
    int OPEN_PLAYLIST_SONG_OPTIONS = 4;
    int CLOSE_ADD_TO_PLAYLIST = 5;
    int CLOSE_OPTIONS = 6;

    void setAction(int action);
//    void setAction(int action);
}
