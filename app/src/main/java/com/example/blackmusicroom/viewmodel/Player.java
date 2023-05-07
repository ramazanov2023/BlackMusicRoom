package com.example.blackmusicroom.viewmodel;

import android.media.MediaPlayer;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public interface Player {

    void initPlayer();

    void play(int songId, ArrayList<Song> playlist);

    void playCurrentSong();

    void pause();

    void stop();

    void nextSong();

    void prevSong();

    MediaPlayer getPlayer();

//    static Player getInstance(){
//        return PlayerImpl.getInstance();
//    }

}
