package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Playlist;

import java.util.ArrayList;

public class PagePlaylistsAdapter extends RecyclerView.Adapter<PagePlaylistsAdapter.Holder> {
    ArrayList<Playlist> playlists;
    Context context;
    Options userActions;



    public PagePlaylistsAdapter(Context context, ArrayList<Playlist> playlists, Options userActions) {
        this.playlists = playlists;
        this.context = context;
        this.userActions = userActions;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_playlists_page,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Playlist playlist = playlists.get(position);
        holder.id.setText(String.valueOf(playlist.plId));
        holder.name.setText(playlist.plName);
        holder.countSongs.setText(String.valueOf(playlist.plCountSongs));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playlist cPlaylist = playlists.get(holder.getAdapterPosition());
                ActionData.getInstance().addPlaylistData(cPlaylist.plName, cPlaylist.plId);
                Navigator navigator = NavigatorImpl.getInstance();
                navigator.openPage(Navigator.PLAYLIST_SONGS_PAGE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView name,countSongs,id;
        ImageView options;
        public Holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.page_playlist_id);
            name = itemView.findViewById(R.id.page_playlist_name);
            countSongs = itemView.findViewById(R.id.page_playlist_count_songs);
            options = itemView.findViewById(R.id.page_playlist_options);

        }
    }
}
