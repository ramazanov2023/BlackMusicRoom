package com.example.blackmusicroom;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.blackmusicroom.model.repository.AllSongsDB;
import com.example.blackmusicroom.model.repository.AllSongsDBImpl;
import com.example.blackmusicroom.view.screen.PagePlayerFragment;
import com.example.blackmusicroom.view.screen.PagePlaylistSongsFragment;
import com.example.blackmusicroom.view.screen.PagePlaylistsFragment;
import com.example.blackmusicroom.view.screen.SettingsActivity;
import com.example.blackmusicroom.view.screen.PageSongsFragment;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;


public class MainActivity extends FragmentActivity implements Navigator {
    ViewPager2 viewPager2;
    FragmentStateAdapter adapter;
    AllSongsDB songs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        songs = AllSongsDBImpl.getInstance();
//        songs.loadSongs(getApplicationContext());

//        Player player = PlayerImpl.getInstance();
//        player.initPlayer();
//        Player.getInstance().initPlayer();

        viewPager2 = findViewById(R.id.view_pager_2);
        adapter = new PagerFragmentAdapter(this);
        viewPager2.setAdapter(adapter);

//        AllSongsDB songs = AllSongsDBImpl.getInstance();
//        songs.loadSongs(getApplicationContext());

        Player player = PlayerImpl.getInstance();
        player.initPlayer();

        if (!checkPermission()) {
            requestPermission();
        }

    }

    @Override
    public void setPage(int numPage) {
        switch (numPage) {
            case SONGS_PAGE:
                viewPager2.setCurrentItem(SONGS_PAGE);
                break;
            case PLAYLISTS_PAGE:
                viewPager2.setCurrentItem(PLAYLISTS_PAGE);
                break;
            case PLAYLIST_SONGS_PAGE:
                viewPager2.setCurrentItem(PLAYLIST_SONGS_PAGE);
                break;
            case PLAYER_PAGE:
                viewPager2.setCurrentItem(PLAYER_PAGE);
                break;
            case SETTINGS_PAGE:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class PagerFragmentAdapter extends FragmentStateAdapter {
        public PagerFragmentAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new PageSongsFragment();
                case 1:
                    return new PagePlaylistsFragment();
                case 2:
                    return new PagePlaylistSongsFragment();
                case 3:
                    return new PagePlayerFragment();
            }

            return null;
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }

    //Strong reference(жесткая ссылка)
//    StrongR strong = new StrongR();

    //Weak reference(слабая ссылка)если object strong удлёт то эта ссылка будет удалена GC
//    WeakReference weak = new WeakReference(strong);
//    strong = null;

    //Soft reference(мягкая ссылка) GC удалит ссылку когда заметит нехватку выделенной памяти
//    SoftReference soft = new SoftReference(strong);
//    strong = null;

    //Phantom reference(фантомная ссылка)
//    PhantomReference phantom = new PhantomReference(strong);
//    strong = null;

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "READ PERMISSION ID REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    AllSongsDB songs = AllSongsDBImpl.getInstance();
//                    songs.loadSongs(getApplicationContext());
                    viewPager2.setAdapter(new PagerFragmentAdapter(this));
                }
        }
    }
}