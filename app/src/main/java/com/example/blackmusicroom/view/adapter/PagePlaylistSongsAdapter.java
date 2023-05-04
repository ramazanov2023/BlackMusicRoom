package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public class PagePlaylistSongsAdapter extends RecyclerView.Adapter<PagePlaylistSongsAdapter.Holder> {
    Context context;
    ArrayList<Song> songs;

    public PagePlaylistSongsAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    public void setSongs(ArrayList<Song> songs){
        this.songs = songs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_playlist_songs_page,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Song song = songs.get(position);
        holder.title.setText(song.songTitle);
        holder.artist.setText(song.songArtist);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView title, artist;
        public Holder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.page_playlist_songs_poster);
            title = itemView.findViewById(R.id.page_playlist_songs_title);
            artist = itemView.findViewById(R.id.page_playlist_songs_artist);
        }
    }
}
