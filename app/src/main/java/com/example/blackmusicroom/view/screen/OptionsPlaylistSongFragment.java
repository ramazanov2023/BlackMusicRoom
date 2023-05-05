package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class OptionsPlaylistSongFragment extends BottomSheetDialogFragment {
    LinearLayout addToPlaylist, deleteFromPlaylist;
    ArrayList<Song> song;
    Options options;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options_playlist_song,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addToPlaylist = view.findViewById(R.id.options_pl_add_to_pl);
        deleteFromPlaylist = view.findViewById(R.id.options_pl_delete_from_pl);

        addToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("searchList"," 3 - song.size - " + song.size());
                ((Options) getActivity()).setAction(Options.OPEN_ADD_TO_PLAYLIST);
            }
        });

        deleteFromPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("searchList"," 3 - song.size - " + song.size());
                ActionData.getInstance().deleteFromPlaylist(getActivity());
                ((Options) getActivity()).setAction(Options.CLOSE_OPTIONS);
            }
        });

    }
    public void setSong(Options options, ArrayList<Song> song){
        this.song = song;
//        Log.e("searchList"," 2 - this.song.size - " + this.song.size());
        this.options = options;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MusicRoomDialog);
    }
}
