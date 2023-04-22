package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.AllSongsDB;
import com.example.blackmusicroom.model.repository.AllSongsDBImpl;
import com.example.blackmusicroom.view.adapter.PageSongsAdapter;

import java.util.ArrayList;

public class PageSongsFragment extends Fragment{
    RecyclerView recyclerView;
    PageSongsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songs_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.page_songs_recycler_view);
        Log.e("challenge","1 - registerObserver");

        adapter = new PageSongsAdapter(getActivity(), new ArrayList<>());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        AllSongsDB songs = AllSongsDBImpl.getInstance();
        songs.getSongs().observe(getViewLifecycleOwner(), new Observer<ArrayList<Song>>() {
            @Override
            public void onChanged(ArrayList<Song> songs) {
                Log.e("observe","1 - onChanged");
                recyclerView.swapAdapter(new PageSongsAdapter(getActivity(),songs),true);
//                adapter.setSongs(songs);
            }
        });
    }
}
