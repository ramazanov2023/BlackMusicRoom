package com.example.blackmusicroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public interface Navigator {

    int SONGS_PAGE = 0;
    int PLAYLISTS_PAGE = 1;
    int PLAYLIST_SONGS_PAGE = 2;
    int PLAYER_PAGE = 3;
    int SETTINGS_PAGE = 4;


    void setPage(int numPage);

//    void setPlaylistsPage();
//
//    void setPlaylistSongsPage();
//
//    void setPlayerPage();
//
//    void setSettingsPage();
//
//    void setViewPager(ViewPager2 navigator);
//
////    static Navigator getNavigator(){
////        Navigator navigator = NavigatorImpl.getInstance();
////        return navigator;
////    }
}
