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

public class PagePlaylistSongsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlist_songs_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



//        Navigator navigator =(Navigator) getActivity();
//
//        btnSongs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigator.setPage(Navigator.SONGS_PAGE);
//            }
//        });

    }
}
