package com.example.blackmusicroom.model.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public interface AllSongsDB {
    void loadSongs(Context context);
    LiveData<ArrayList<Song>> getSongs();
}
