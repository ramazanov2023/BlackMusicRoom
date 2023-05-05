package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.view.adapter.PageSongsAdapter;
import com.example.blackmusicroom.viewmodel.PageSongsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class OptionsSongFragment extends BottomSheetDialogFragment {
    LinearLayout addToPlaylist;
    ArrayList<Song> song;
    Options options;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options_song,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addToPlaylist = view.findViewById(R.id.options_add_to_pl);

        addToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("searchList"," 3 - song.size - " + song.size());
                options.setAction(Options.OPEN_PLAYLIST_LIST,null,0,song);
            }
        });
//        PageSongsViewModel viewModel = ViewModelProviders.of(this).get(PageSongsViewModel.class);

    }
    public void setSong(Options options, ArrayList<Song> song){
        this.song = song;
        Log.e("searchList"," 2 - this.song.size - " + this.song.size());
        this.options = options;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MusicRoomDialog);
    }
}
