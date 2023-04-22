package com.example.blackmusicroom.viewmodel;

public class PlayerImpl implements Player{

    private static PlayerImpl instance;

    public static PlayerImpl getInstance(){
        if(instance==null){
            instance = new PlayerImpl();
        }
        return instance;
    }

    @Override
    public void initPlayer() {

    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void nextSong() {

    }

    @Override
    public void prevSong() {

    }
}
