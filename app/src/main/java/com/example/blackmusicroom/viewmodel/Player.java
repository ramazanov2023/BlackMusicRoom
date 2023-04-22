package com.example.blackmusicroom.viewmodel;

public interface Player {

    void initPlayer();

    void play();

    void pause();

    void stop();

    void nextSong();

    void prevSong();

//    static Player getInstance(){
//        return PlayerImpl.getInstance();
//    }

}
