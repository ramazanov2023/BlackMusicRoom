package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.blackmusicroom.R;

public class PageFilesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_files_page,container,false);
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
