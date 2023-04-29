package com.example.blackmusicroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public interface Navigator {

    int SONGS_PAGE = 0;
    int PLAYLISTS_PAGE = 1;
    int FILES_PAGE = 2;
    int PLAYLIST_SONGS_PAGE = 3;
    int PLAYER_PAGE = 4;
    int SETTINGS_PAGE = 5;

    void setPage(int numPage);

}
