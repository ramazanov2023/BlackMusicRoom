package com.example.blackmusicroom;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public interface Navigator {

    int SONGS_PAGE = 0;
    int PLAYLISTS_PAGE = 1;
    int FILES_PAGE = 2;
    int PLAYLIST_SONGS_PAGE = 3;
    int PLAYER_PAGE = 4;
    int SETTINGS_PAGE = 5;

    void initNavigator(AppCompatActivity activity);

    void openPage(int numPage);

    void setViewPager(ViewPager2 viewPager2);

}
