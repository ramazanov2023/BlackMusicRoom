package com.example.blackmusicroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public interface UserActions {
    int DELETE_PLAYLIST = 0;

    void setAction(int action, String playlistName,int playlistId);

}
