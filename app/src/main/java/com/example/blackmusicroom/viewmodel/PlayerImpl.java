package com.example.blackmusicroom.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.data.Song;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerImpl implements Player{

    private MediaPlayer player;
    private ArrayList<Song> playlist;
    private String playlistName;
    private int playlistNum;
    private static PlayerImpl instance;
    private int songId = 0;
    private MutableLiveData liveData;

    public static PlayerImpl getInstance(){
        if(instance==null){
            instance = new PlayerImpl();

        }
        return instance;
    }

    @Override
    public void initPlayer(Context context) {
        player = new MediaPlayer();
        if(liveData==null){
            liveData = new MutableLiveData();
        }
//        songId = context.getSharedPreferences("musPlayer", Context.MODE_PRIVATE).getInt("songId",0);
//        liveData.setValue();

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
        liveData.setValue(song);
        ActionData.getInstance().addSong(song);
    }

    @Override
    public void playCurrentSong() {
        player.start();
    }

    @Override
    public LiveData<Song> getSong() {
        if(liveData==null){
            liveData = new MutableLiveData();
        }
        return liveData;
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

    public void setPlaylist(ArrayList<Song> playlist, int songId){
        this.playlist = playlist;
        this.songId = songId;
        liveData.setValue(playlist.get(songId));

        player = new MediaPlayer();
        try {
            player.setDataSource(playlist.get(songId).songPath);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getSongId(){
        return songId;
    }
}
