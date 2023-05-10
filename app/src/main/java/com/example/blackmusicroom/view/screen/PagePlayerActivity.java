package com.example.blackmusicroom.view.screen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.viewmodel.BottomPlayerViewModel;
import com.example.blackmusicroom.viewmodel.PagePlayerViewModel;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;

public class PagePlayerActivity extends AppCompatActivity implements Options {
    ImageView btnPlay,btnNext,btnPreview,btnOptions;
    TextView songTitle, songArtist;
    OptionsPlaylistSongFragment optionsPlaylistSong;
    private AddToPlaylistFragment addToPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);

        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);

        btnPlay = findViewById(R.id.play_btn);
        btnNext = findViewById(R.id.next_btn);
        btnPreview = findViewById(R.id.preview_btn);
        songTitle = findViewById(R.id.page_player_song_title);
        songArtist = findViewById(R.id.page_player_artist);
        btnOptions = findViewById(R.id.page_player_options);

//        displaySongData();

        Player player = PlayerImpl.getInstance();
        if(player.getPlayer().isPlaying()){
            btnPlay.setBackgroundResource(R.drawable.ic_pause_circle);
        }else{
            btnPlay.setBackgroundResource(R.drawable.ic_play_circle);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = PlayerImpl.getInstance();
                player.nextSong();
                btnPlay.setBackgroundResource(R.drawable.ic_pause_circle);
//                displaySongData();
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = PlayerImpl.getInstance();
                player.prevSong();
                btnPlay.setBackgroundResource(R.drawable.ic_pause_circle);
//                displaySongData();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = PlayerImpl.getInstance();
                if(player.getPlayer().isPlaying()){
                    player.pause();
                    btnPlay.setBackgroundResource(R.drawable.ic_play_circle);
                }else{
                    player.playCurrentSong();
                    btnPlay.setBackgroundResource(R.drawable.ic_pause_circle);

                };
            }
        });

        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAction(OPEN_PLAYLIST_SONG_OPTIONS);
            }
        });

        PagePlayerViewModel viewModel = ViewModelProviders.of(this).get(PagePlayerViewModel.class);
        viewModel.getSong().observe(this, new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                songTitle.setText(song.songTitle);
                songArtist.setText(song.songArtist);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor preferences = getSharedPreferences("musPlayer",MODE_PRIVATE).edit();
        preferences.putInt("songId",PlayerImpl.getInstance().getSongId());
        preferences.putInt("playlistNum", ActionData.getInstance().playlistNum);
        preferences.apply();
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