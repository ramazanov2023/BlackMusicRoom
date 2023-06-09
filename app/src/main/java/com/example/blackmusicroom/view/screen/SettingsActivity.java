package com.example.blackmusicroom.view.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);
    }
}