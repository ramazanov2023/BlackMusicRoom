package com.example.blackmusicroom;

import androidx.viewpager2.widget.ViewPager2;

public interface Navigator {

    void setSongsPage();

    void setPlaylistsPage();

    void setPlaylistSongsPage();

    void setPlayerPage();

    void setSettings();

    void setViewPager(ViewPager2 navigator);

    static Navigator getNavigator(){
        Navigator navigator = NavigatorImpl.getInstance();
        return navigator;
    }
}
