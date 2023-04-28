package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Playlist;
import com.example.blackmusicroom.viewmodel.PagePlaylistsViewModel;

import java.util.ArrayList;

public class PagePlaylistsAdapter extends RecyclerView.Adapter<PagePlaylistsAdapter.Holder> {
    ArrayList<Playlist> playlists;
    Context context;
    PlaylistOptions playlistOptions;

    public interface PlaylistOptions{
        void setOption(String playlistName,int playlistId);
    }


    public PagePlaylistsAdapter(Context context, ArrayList<Playlist> playlists,PlaylistOptions playlistOptions) {
        this.playlists = playlists;
        this.context = context;
        this.playlistOptions = playlistOptions;
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

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,countSongs,id;
        ImageView options;
        public Holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.page_playlist_id);
            name = itemView.findViewById(R.id.page_playlist_name);
            countSongs = itemView.findViewById(R.id.page_playlist_count_songs);
            options = itemView.findViewById(R.id.page_playlist_options);
            options.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Playlist playlist = playlists.get(getAdapterPosition());
            playlistOptions.setOption(playlist.plName,playlist.plId);
        }
    }
}
