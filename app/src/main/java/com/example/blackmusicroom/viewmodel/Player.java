package com.example.blackmusicroom.viewmodel;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public interface Player {

    void initPlayer(Context context);

    void play(int songId, ArrayList<Song> playlist);

    void playCurrentSong();

    LiveData<Song> getSong();

    void pause();

    void stop();

    void nextSong();

    void prevSong();

    MediaPlayer getPlayer();

//    static Player getInstance(){
//        return PlayerImpl.getInstance();
//    }

}
