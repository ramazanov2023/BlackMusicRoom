package com.example.blackmusicroom.viewmodel;

import android.media.MediaPlayer;
import android.util.Log;

import com.example.blackmusicroom.data.Song;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerImpl implements Player{

    private MediaPlayer player;
    private ArrayList<Song> playlist;
    private static PlayerImpl instance;
    private int songId = 0;

    public static PlayerImpl getInstance(){
        if(instance==null){
            instance = new PlayerImpl();
        }
        return instance;
    }

    @Override
    public void initPlayer() {
        player = new MediaPlayer();
//        player.setDataSource();
    }

    @Override
    public void play(int newSongId, ArrayList<Song> newPlaylist) {
        releaseMemories();
        player = new MediaPlayer();

        if(newPlaylist!=null) {
            playlist = newPlaylist;
        }
            songId = newSongId;


        Song song = playlist.get(songId);
        try {
            player.setDataSource(song.songPath);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextSong();
            }
        });
    }

    @Override
    public void playCurrentSong() {
        player.start();
    }

    @Override
    public void pause() {
        player.pause();
    }

    @Override
    public void stop() {

    }

    @Override
    public void nextSong() {
        if(songId==playlist.size()-1){
            songId = -1;
        }
        play(++songId,null);
    }

    @Override
    public void prevSong() {
        if(songId==0){
            songId = playlist.size();
        }
        play(--songId,null);
    }

    @Override
    public MediaPlayer getPlayer() {
        if(player==null){
            player = new MediaPlayer();
        }
        return player;
    }

    private void releaseMemories(){
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }
    }
}
