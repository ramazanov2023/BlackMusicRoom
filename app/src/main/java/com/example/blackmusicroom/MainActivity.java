package com.example.blackmusicroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

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
    AddToPlaylistFragment addToPlaylist;

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
    public void setAction(int action) {
//        ArrayList<Song> listSongs;
        switch (action){
            case OPEN_SONG_OPTIONS:
//                ActionData.getInstance().addSong(song);
                optionsSong = new OptionsSongFragment();
//                optionsSong.setSong(this,song);
                optionsSong.show(getSupportFragmentManager(),"TAG");
                break;
            case OPEN_PLAYLIST_OPTIONS:
                OptionsPlaylistFragment optionsPlaylist = new OptionsPlaylistFragment();
                optionsPlaylist.show(getSupportFragmentManager(),"TAG");
                break;
            case OPEN_ADD_TO_PLAYLIST:
                addToPlaylist = new AddToPlaylistFragment();
//                playlistsList.setSong(song);
                if(optionsSong!=null) {
                    optionsSong.dismiss();
                    optionsSong = null;
                }
                addToPlaylist.show(getSupportFragmentManager(),"TAG");
                break;
            case CLOSE_ADD_TO_PLAYLIST:
                if(addToPlaylist!=null) {
                    addToPlaylist.dismiss();
                    addToPlaylist = null;
                }
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