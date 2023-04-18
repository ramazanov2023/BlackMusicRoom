package com.example.blackmusicroom.view.screen;

import android.content.Intent;
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

public class PlaylistSongsPageFragment extends Fragment {
    Button btnSongs,btnPlaylists,btnPlaylistSongs,btnPlayer,btnSettings;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_songs_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnSongs = view.findViewById(R.id.page_songs);
        btnPlaylists = view.findViewById(R.id.page_playlists);
        btnPlaylistSongs = view.findViewById(R.id.page_playlist_songs);
        btnPlayer = view.findViewById(R.id.page_player);
        btnSettings = view.findViewById(R.id.page_settings);

        Navigator navigator =(Navigator) getActivity();

        btnSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.setPage(Navigator.SONGS_PAGE);
            }
        });
        btnPlaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.setPage(Navigator.PLAYLISTS_PAGE);
            }
        });
        btnPlaylistSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.setPage(Navigator.PLAYLIST_SONGS_PAGE);
            }
        });
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.setPage(Navigator.PLAYER_PAGE);
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.setPage(Navigator.SETTINGS_PAGE);
            }
        });
    }
}
