package com.example.blackmusicroom;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager2.widget.ViewPager2;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.view.screen.PagePlayerActivity;
import com.example.blackmusicroom.view.screen.PagePlaylistSongsActivity;
import com.example.blackmusicroom.view.screen.SettingsActivity;

import java.util.ArrayList;

public class NavigatorImpl implements Navigator, LifecycleObserver {

    ViewPager2 viewPager2;
//    Context context = null;
    AppCompatActivity activity = null;
    private static NavigatorImpl instance;



    public static NavigatorImpl getInstance(){
        if(instance==null){
            instance = new NavigatorImpl();
        }
        return instance;
    }


    @Override
    public void initNavigator(AppCompatActivity activity) {
        this.activity = activity;
        this.activity.getLifecycle();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void deleteViewPager2(){
        viewPager2 = null;
    }



    @Override
    public void setViewPager(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
    }

    @Override
    public void openPage(int numPage,String playlistName) {

        if(viewPager2==null){
            return;
        }
        switch (numPage) {
            case Navigator.SONGS_PAGE:
                viewPager2.setCurrentItem(Navigator.SONGS_PAGE);
                break;
            case Navigator.PLAYLISTS_PAGE:
                viewPager2.setCurrentItem(Navigator.PLAYLISTS_PAGE);
                break;
            case Navigator.FILES_PAGE:
                viewPager2.setCurrentItem(Navigator.FILES_PAGE);
                break;
            case Navigator.PLAYLIST_SONGS_PAGE:
                Intent intent = new Intent(activity, PagePlaylistSongsActivity.class);
                intent.putExtra("playlistName",playlistName);
                activity.startActivity(intent);
                break;
            case Navigator.PLAYER_PAGE:
                Intent intent2 = new Intent(activity, PagePlayerActivity.class);
                activity.startActivity(intent2);
                break;
            case Navigator.SETTINGS_PAGE:
                Intent intent3 = new Intent(activity, SettingsActivity.class);
                activity.startActivity(intent3);
                break;
        }
    }


}
