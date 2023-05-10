package com.example.blackmusicroom.view.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.viewmodel.BottomPlayerViewModel;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;

import java.util.ArrayList;

public class BottomPlayerFragment extends Fragment {
    ConstraintLayout openPlayerPage;
    TextView songTitle, songArtist;
    ImageView btnPlay;
    SharedPreferences preferences;
    private String playlistName;
    private int playlistNum;
    private int songId = 0;

    private static BottomPlayerFragment bottomPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_player,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        openPlayerPage = view.findViewById(R.id.bottom_player_open_player_page);
        songTitle = view.findViewById(R.id.bottom_player_song_title);
        songArtist = view.findViewById(R.id.bottom_player_artist);
        btnPlay = view.findViewById(R.id.bottom_player_play);

        Navigator navigator = NavigatorImpl.getInstance();



        openPlayerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.openPage(Navigator.PLAYER_PAGE);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = PlayerImpl.getInstance();
                if(player.getPlayer().isPlaying()){
                    player.pause();
                    btnPlay.setBackgroundResource(R.drawable.ic_play);
                }else{
                    player.playCurrentSong();
                    btnPlay.setBackgroundResource(R.drawable.ic_pause);

                };
            }
        });


        BottomPlayerViewModel viewModel = ViewModelProviders.of(this).get(BottomPlayerViewModel.class);

        viewModel.getSong().observe(getViewLifecycleOwner(), new Observer<Song>() {
            @Override
            public void onChanged(Song song) {
                songTitle.setText(song.songTitle);
                songArtist.setText(song.songArtist);

                displayViews();
            }
        });

    }

    public void displayViews(){
        Player player = PlayerImpl.getInstance();
        Log.e("setText"," 1 isPlaying() - " + player.getPlayer().isPlaying());
        if(player.getPlayer().isPlaying()){
            Log.e("setText"," 2 displayViews");
            btnPlay.setBackgroundResource(R.drawable.ic_pause);
        }else{
            Log.e("setText"," 3 displayViews");
            btnPlay.setBackgroundResource(R.drawable.ic_play);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        displayViews();
    }
}
