package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;

import java.util.ArrayList;

public class PagePlaylistSongsAdapter extends RecyclerView.Adapter<PagePlaylistSongsAdapter.Holder> {
    Context context;
    ArrayList<Song> songs;
    Options options;
    ArrayList<Song> listSongs;

    public PagePlaylistSongsAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
        listSongs = new ArrayList<>();
    }

    public void setSongs(ArrayList<Song> songs){
        this.songs = songs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_songs_page,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Song song = songs.get(position);
        holder.title.setText(song.songTitle);
        holder.artist.setText(song.songArtist);
        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("reaction", " 1 ");
                listSongs.add(songs.get(holder.getAdapterPosition()));
                ActionData.getInstance().addSong(holder.getAdapterPosition(), listSongs);
                options = (Options) context;
                options.setAction(Options.OPEN_PLAYLIST_SONG_OPTIONS);
                listSongs = new ArrayList<>();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView poster,options;
        TextView title, artist;
        public Holder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.page_songs_poster);
            options = itemView.findViewById(R.id.page_songs_options);
            title = itemView.findViewById(R.id.page_songs_title);
            artist = itemView.findViewById(R.id.page_songs_artist);
        }
    }
}
