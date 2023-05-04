package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.view.adapter.AddToPlaylistAdapter;
import com.example.blackmusicroom.viewmodel.AddToPlaylistViewModel;

import java.util.ArrayList;

public class AddToPlaylistFragment extends DialogFragment implements Options{
    RecyclerView recyclerView;
    ArrayList<Song> song;
    Options options;
    AddToPlaylistViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_to_playlist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.playlists_list_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new AddToPlaylistAdapter(getActivity(),new ArrayList<>(),song,AddToPlaylistFragment.this));

        viewModel = ViewModelProviders.of(this).get(AddToPlaylistViewModel.class);
        viewModel.getPlaylists().observe(getViewLifecycleOwner(), new Observer<ArrayList<Playlist>>() {
            @Override
            public void onChanged(ArrayList<Playlist> playlists) {
                recyclerView.swapAdapter(new AddToPlaylistAdapter(getActivity(),playlists,song, AddToPlaylistFragment.this),true);
            }
        });

    }

    public void setSong(ArrayList<Song> song) {
        this.song = song;
    }

    @Override
    public void setAction(int action, String playlistName, int playlistId, ArrayList<Song> song) {
        viewModel.addToPlaylist(playlistName,this.song);
    }
}
