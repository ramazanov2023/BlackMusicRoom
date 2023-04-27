package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.view.adapter.PlaylistControlAdapter;
import com.example.blackmusicroom.viewmodel.PagePlaylistsViewModel;

import java.util.ArrayList;

public class PagePlaylistsFragment extends Fragment {
    RecyclerView recyclerView;
    EditText playlistName;
    ImageView createPlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playlists_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.page_playlists_recycler_view);
        playlistName = view.findViewById(R.id.enter_player_name);
        createPlaylist = view.findViewById(R.id.button_new_player);


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(new PlaylistControlAdapter(getActivity(),new ArrayList<>()));


//        Navigator navigator =(Navigator) getActivity();

        PagePlaylistsViewModel viewModel = ViewModelProviders.of(this).get(PagePlaylistsViewModel.class);
        viewModel.getPlaylists().observe(getViewLifecycleOwner(), new Observer<ArrayList<Playlist>>() {
            @Override
            public void onChanged(ArrayList<Playlist> playlists) {
                recyclerView.swapAdapter(new PlaylistControlAdapter(getActivity(),playlists),true);
            }
        });

        createPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.createPlaylist(playlistName.getText().toString().trim());
                playlistName.setText("");
            }
        });

//        viewModel.createPlaylist("BlackNightRoom");
    }
}
