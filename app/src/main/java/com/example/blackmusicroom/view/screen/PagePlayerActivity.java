package com.example.blackmusicroom.view.screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.R;

public class PagePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);

        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);
    }
}