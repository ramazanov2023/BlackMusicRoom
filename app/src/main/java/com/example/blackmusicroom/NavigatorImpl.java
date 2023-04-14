package com.example.blackmusicroom;

import androidx.viewpager2.widget.ViewPager2;

public class NavigatorImpl implements Navigator{

    private static NavigatorImpl instance;
    private ViewPager2 navigator;

    @Override
    public void setSongsPage() {
        if(navigator!=null){
            navigator.setCurrentItem(0);
        }
    }

    @Override
    public void setPlaylistsPage() {
        if(navigator!=null){
            navigator.setCurrentItem(1);
        }
    }

    @Override
    public void setPlaylistSongsPage() {
        if(navigator!=null){
            navigator.setCurrentItem(2);
        }
    }

    @Override
    public void setPlayerPage() {
        if(navigator!=null){
            navigator.setCurrentItem(3);
        }
    }

    @Override
    public void setSettings() {

    }

    @Override
    public void setViewPager(ViewPager2 navigator) {
        this.navigator = navigator;
    }

    public static NavigatorImpl getInstance(){
        if(instance==null){
            instance = new NavigatorImpl();
        }
        return instance;
    }
}
