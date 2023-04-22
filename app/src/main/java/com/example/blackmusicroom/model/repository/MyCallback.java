package com.example.blackmusicroom.model.repository;

import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public interface MyCallback {
    void onLoad(ArrayList<Song> songs);
}
