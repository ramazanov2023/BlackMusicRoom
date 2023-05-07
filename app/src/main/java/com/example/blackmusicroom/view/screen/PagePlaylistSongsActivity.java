package com.example.blackmusicroom.view.screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.UserActions;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControl;
import com.example.blackmusicroom.model.repository.playlists.PlaylistControlImpl;
import com.example.blackmusicroom.view.adapter.PagePlaylistSongsAdapter;
import com.example.blackmusicroom.view.adapter.PagePlaylistsAdapter;
import com.example.blackmusicroom.view.adapter.PageSongsAdapter;
import com.example.blackmusicroom.viewmodel.PagePlaylistSongsViewModel;

import java.util.ArrayList;

public class PagePlaylistSongsActivity extends AppCompatActivity implements Options {
    RecyclerView recyclerView;
    OptionsPlaylistSongFragment optionsPlaylistSong;
    AddToPlaylistFragment addToPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_songs_page);

//        String playlistName = getIntent().getStringExtra("playlistName");

        PlaylistControl playlistControl = PlaylistControlImpl.getInstance();
        playlistControl.loadPlaylistSongs(this, ActionData.getInstance().getPlaylistName());

        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);

        recyclerView = findViewById(R.id.page_playlist_songs_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PagePlaylistSongsAdapter(this, new ArrayList<>()));

        PagePlaylistSongsViewModel viewModel = ViewModelProviders.of(this).get(PagePlaylistSongsViewModel.class);
        viewModel.getPlaylistSongs().observe(this, new Observer<ArrayList<Song>>() {
            @Override
            public void onChanged(ArrayList<Song> songs) {
                recyclerView.swapAdapter(new PagePlaylistSongsAdapter(PagePlaylistSongsActivity.this,
                        songs), true);
            }
        });
    }


    @Override
    public void setAction(int action) {
        switch (action) {
            case OPEN_PLAYLIST_SONG_OPTIONS:
                optionsPlaylistSong = new OptionsPlaylistSongFragment();
                optionsPlaylistSong.show(getSupportFragmentManager(), "TAG");
                break;
            case OPEN_ADD_TO_PLAYLIST:
                addToPlaylist = new AddToPlaylistFragment();
                if(optionsPlaylistSong!=null) {
                    optionsPlaylistSong.dismiss();
                    optionsPlaylistSong = null;
                }
                addToPlaylist.show(getSupportFragmentManager(),"TAG");
                break;
            case CLOSE_ADD_TO_PLAYLIST:
                if(addToPlaylist!=null) {
                    addToPlaylist.dismiss();
                    addToPlaylist = null;
                }
                break;
            case CLOSE_OPTIONS:
                if(optionsPlaylistSong!=null) {
                    optionsPlaylistSong.dismiss();
                    optionsPlaylistSong = null;
                }
                break;
        }
    }
}