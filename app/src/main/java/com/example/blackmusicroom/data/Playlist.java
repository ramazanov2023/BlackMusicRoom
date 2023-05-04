package com.example.blackmusicroom.data;

public class Playlist {

    public int plId;
    public String plName;
    public long plCountSongs;

    public Playlist(int plId, String plName, long plCountSongs) {
        this.plId = plId;
        this.plName = plName;
        this.plCountSongs = plCountSongs;
    }
}
