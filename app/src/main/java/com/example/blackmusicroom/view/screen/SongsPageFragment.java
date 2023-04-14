package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.R;

public class SongsPageFragment extends Fragment {
    Button btnSongs,btnPlaylists,btnPlaylistSongs,btnPlayer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songs_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnSongs = view.findViewById(R.id.page_songs);
        btnPlaylists = view.findViewById(R.id.page_playlists);
        btnPlaylistSongs = view.findViewById(R.id.page_playlist_songs);
        btnPlayer = view.findViewById(R.id.page_player);

        btnPlaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.getNavigator().setPlaylistsPage();
            }
        });
        btnPlaylistSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.getNavigator().setPlaylistSongsPage();
            }
        });
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigator.getNavigator().setPlayerPage();
            }
        });
    }
}
