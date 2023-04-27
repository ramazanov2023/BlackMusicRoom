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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.model.repository.AllSongsDB;
import com.example.blackmusicroom.model.repository.AllSongsDBImpl;
import com.example.blackmusicroom.view.adapter.PageSongsAdapter;
import com.example.blackmusicroom.viewmodel.PageSongsViewModel;

import java.util.ArrayList;

public class PageSongsFragment extends Fragment{
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songs_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.page_songs_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new PageSongsAdapter(getActivity(), new ArrayList<>()));

        PageSongsViewModel viewModel = ViewModelProviders.of(this).get(PageSongsViewModel.class);
        viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<ArrayList<Song>>() {
            @Override
            public void onChanged(ArrayList<Song> songs) {
                recyclerView.swapAdapter(new PageSongsAdapter(getActivity(),songs),true);
            }
        });
    }
}
