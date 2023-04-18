package com.example.blackmusicroom;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.blackmusicroom.view.screen.SettingsActivity;

public class NavigatorImpl implements Navigator{

    private static NavigatorImpl instance;
    private ViewPager2 navigator;
    private AppCompatActivity activity;

    @Override
    public void setPage(int numPage) {
        if(navigator!=null){
            navigator.setCurrentItem(0);
        }
    }

}
