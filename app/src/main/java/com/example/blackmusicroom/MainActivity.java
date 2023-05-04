package com.example.blackmusicroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.view.screen.BottomPlayerFragment;
import com.example.blackmusicroom.view.screen.OptionsPlaylistFragment;
import com.example.blackmusicroom.view.screen.OptionsSongFragment;
import com.example.blackmusicroom.view.screen.PageFilesFragment;
import com.example.blackmusicroom.view.screen.PagePlaylistsFragment;
import com.example.blackmusicroom.view.screen.AddToPlaylistFragment;
import com.example.blackmusicroom.view.screen.PageSongsFragment;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Options{
    ViewPager2 viewPager2;
    FragmentStateAdapter adapter;
    OptionsSongFragment optionsSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.main_activity_view_pager_2);
        adapter = new PagerFragmentAdapter(this);
        viewPager2.setAdapter(adapter);

        displayBottomPlayer();

        Player player = PlayerImpl.getInstance();
        player.initPlayer();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);
        navigator.setViewPager(viewPager2);
    }

    private void displayBottomPlayer() {
        BottomPlayerFragment bottomPlayer = new BottomPlayerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_bottom_player,bottomPlayer)
                .commit();
    }

    @Override
    public void setAction(int action, String playlistName, int playlistId, ArrayList<Song> song) {
//        ArrayList<Song> listSongs;
        switch (action){
            case OPEN_SONG_OPTIONS:
                optionsSong = new OptionsSongFragment();
                optionsSong.setSong(this,song);
                optionsSong.show(getSupportFragmentManager(),"TAG");
                break;
            case OPEN_PLAYLIST_OPTIONS:
                OptionsPlaylistFragment optionsPlaylist = new OptionsPlaylistFragment();
                optionsPlaylist.show(getSupportFragmentManager(),"TAG");
                break;
            case OPEN_PLAYLIST_LIST:
                AddToPlaylistFragment playlistsList = new AddToPlaylistFragment();
                playlistsList.setSong(song);
                if(optionsSong!=null) {
                    optionsSong.dismiss();
                    optionsSong = null;
                }
                playlistsList.show(getSupportFragmentManager(),"TAG");
                break;
            case ADD_SONG_TO_PLAYLIST:

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
                    return new PageFilesFragment();
            }

            return null;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

}