package com.example.blackmusicroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.MyCallback;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;
import com.example.blackmusicroom.view.screen.BottomPlayerFragment;
import com.example.blackmusicroom.view.screen.OptionsPlaylistFragment;
import com.example.blackmusicroom.view.screen.OptionsSongFragment;
import com.example.blackmusicroom.view.screen.PageFilesFragment;
import com.example.blackmusicroom.view.screen.PagePlaylistsFragment;
import com.example.blackmusicroom.view.screen.AddToPlaylistFragment;
import com.example.blackmusicroom.view.screen.PageSongsFragment;
import com.example.blackmusicroom.viewmodel.BottomPlayerViewModel;
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

        Player player = PlayerImpl.getInstance();
        player.initPlayer(this);

        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        playlistControl.getSongs(this);
        playlistControl.loadSongs(this);
        playlistControl.getPlaylists(this);



        viewPager2 = findViewById(R.id.main_activity_view_pager_2);
        adapter = new PagerFragmentAdapter(this);
        viewPager2.setAdapter(adapter);

        displayBottomPlayer();


        if(savedInstanceState==null) {
            Log.e("trackData", " 4 - savedInstanceState==null");
            SharedPreferences preferences = getSharedPreferences("musPlayer", Context.MODE_PRIVATE);
            int songId = preferences.getInt("songId", 0);
            int playlistNum = preferences.getInt("playlistNum", 0);
            String playlistName = preferences.getString("playlistName", "Favorites");
            playlistControl.loadPlaylistToPlayer(playlistNum,getApplication(),playlistName).observe(this, new Observer<ArrayList<Song>>() {
                @Override
                public void onChanged(ArrayList<Song> songs) {
                    PlayerImpl.getInstance().setPlaylist(songs,songId);
                }
            });
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);
        navigator.setViewPager(viewPager2);
    }

    private void displayBottomPlayer() {
//        BottomPlayerFragment bottomPlayer = BottomPlayerFragment.getInstance();
        BottomPlayerFragment bottomPlayer = new BottomPlayerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_bottom_player,bottomPlayer)
                .commit();
        Log.e("trackData"," 2 - displayBottomPlayer()");
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

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor preferences = getSharedPreferences("musPlayer",MODE_PRIVATE).edit();
        preferences.putInt("songId",PlayerImpl.getInstance().getSongId());
        preferences.putInt("playlistNum",ActionData.getInstance().playlistNum);
        preferences.apply();
    }


}