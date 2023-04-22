package com.example.blackmusicroom.data;

public class Song {
    public int songId;
    public String songPath;
    public String songTitle;
    public String songArtist;
    public String songAlbum;
    public String songDateAdded;
    public String songDuration;
    public String songSize;

    public Song(int songId,
                String songPath,
                String songTitle,
                String songArtist,
                String songAlbum,
                String songDateAdded,
                String songDuration,
                String songSize) {
        this.songId = songId;
        this.songPath = songPath;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songDateAdded = songDateAdded;
        this.songDuration = songDuration;
        this.songSize = songSize;
    }
}
