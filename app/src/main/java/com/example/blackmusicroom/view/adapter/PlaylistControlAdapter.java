package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Playlist;

import java.util.ArrayList;

public class PlaylistControlAdapter extends RecyclerView.Adapter<PlaylistControlAdapter.Holder> {
    ArrayList<Playlist> playlists;
    Context context;
    public PlaylistControlAdapter(Context context, ArrayList<Playlist> playlists) {
        this.playlists = playlists;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_view_playlists,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Playlist playlist = playlists.get(position);
        holder.id.setText(String.valueOf(playlist.plId));
        holder.name.setText(playlist.plName);
        holder.countSongs.setText(playlist.plCountSongs);
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView name,countSongs,id;
        public Holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.page_playlist_id);
            name = itemView.findViewById(R.id.page_playlist_name);
            countSongs = itemView.findViewById(R.id.page_playlist_count_songs);
        }
    }
}
