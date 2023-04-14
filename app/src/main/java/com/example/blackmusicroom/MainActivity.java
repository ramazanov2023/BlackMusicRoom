package com.example.blackmusicroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.blackmusicroom.view.adapter.PageFragmentAdapter;
import com.example.blackmusicroom.view.screen.PlayerPageFragment;
import com.example.blackmusicroom.view.screen.PlaylistSongsPageFragment;
import com.example.blackmusicroom.view.screen.PlaylistsPageFragment;
import com.example.blackmusicroom.view.screen.SongsPageFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ArrayList<Fragment> pages;
    PageFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager_2);
        Navigator.getNavigator().setViewPager(viewPager2);

        pages = new ArrayList<>();
        pages.add(0,new SongsPageFragment());
        pages.add(1,new PlaylistsPageFragment());
        pages.add(2,new PlaylistSongsPageFragment());
        pages.add(3,new PlayerPageFragment());

        adapter = new PageFragmentAdapter(getSupportFragmentManager(),getLifecycle(),pages);
        viewPager2.setAdapter(adapter);

    }
}