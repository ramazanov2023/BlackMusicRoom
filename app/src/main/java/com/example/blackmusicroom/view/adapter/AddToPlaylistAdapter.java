package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public class AddToPlaylistAdapter extends RecyclerView.Adapter<AddToPlaylistAdapter.Holder> {
    Context context;
    ArrayList<Playlist> playlists;
    ArrayList<Song> song;
    Options options;

    public AddToPlaylistAdapter(Context context, ArrayList<Playlist> playlists, ArrayList<Song> song, Options options) {
        this.context = context;
        this.playlists = playlists;
        this.song = song;
        this.options = options;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_playlists_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Playlist playlist = playlists.get(position);
        holder.playlistName.setText(playlist.plName);
        holder.countSongs.setText(playlist.plCountSongs);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playlist pl = playlists.get(holder.getAdapterPosition());
                options.setAction(Options.NO_ACTION,pl.plName,pl.plId,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView playlistName, countSongs;
        public Holder(@NonNull View itemView) {
            super(itemView);
            playlistName = itemView.findViewById(R.id.playlists_list_pl_name);
            countSongs = itemView.findViewById(R.id.playlists_list_count_songs);
        }
    }
}
