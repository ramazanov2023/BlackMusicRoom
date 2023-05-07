package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;

public class PagePlayerActivity extends AppCompatActivity {
    ImageView btnPlay,btnPause,btnNext,btnPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_page);

        Navigator navigator = NavigatorImpl.getInstance();
        navigator.initNavigator(this);

        btnPlay = findViewById(R.id.play_btn);
        btnNext = findViewById(R.id.next_btn);
        btnPreview = findViewById(R.id.preview_btn);

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
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = PlayerImpl.getInstance();
                player.prevSong();
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
    }
}