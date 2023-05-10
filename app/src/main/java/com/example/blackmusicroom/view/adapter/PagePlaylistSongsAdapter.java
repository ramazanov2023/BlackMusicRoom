package com.example.blackmusicroom.view.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.ActionData;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.viewmodel.Player;
import com.example.blackmusicroom.viewmodel.PlayerImpl;

import java.util.ArrayList;

public class PagePlaylistSongsAdapter extends RecyclerView.Adapter<PagePlaylistSongsAdapter.Holder> {
    Context context;
    ArrayList<Song> songs;
    Options options;
    ArrayList<Song> listSongs;
    boolean select = false;
    int[] countSelect;
    int size = 0;
    int count = 0;
    int pos;
    int songPlay = -1;

    public PagePlaylistSongsAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
        listSongs = new ArrayList<>();
        countSelect = new int[songs.size()];
        songPlay = ActionData.getInstance().mediaSongId;
    }

//    public void setSongs(ArrayList<Song> songs){
//        this.songs = songs;
//        notifyDataSetChanged();
//    }

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

        holder.addSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("select"," 3 " + size);
                pos = holder.getAdapterPosition();
                if(select){
                    Log.e("select"," 4 " + size);
                    for(int i = 0; i<count; i++){
                        if(countSelect[i]==pos){
                            Log.e("select"," 5 " + size);
                            listSongs.remove(songs.get(pos));
                            countSelect[i] = -1;
                            size--;
                            ActionData.getInstance().addSong(pos, listSongs);
                            // TODO: отключаем выделение песни
                            holder.bgSample.setVisibility(View.GONE);
                            if(size==0){
                                select = false;
                            }
                            Log.e("select"," 6 " + size);
                            return;
                        }
                    }
                    listSongs.add(songs.get(pos));
                    countSelect[size++] = pos;
                    Log.e("select"," 7 " + size);
                    ActionData.getInstance().addSong(pos, listSongs);
                    // TODO: включаем выделение песни
                    holder.bgSample.setVisibility(View.VISIBLE);
                    count = size;

                }else{
                    Player player = PlayerImpl.getInstance();
                    player.play(pos,songs);
                    songPlay = songs.get(pos).songId;
                    notifyDataSetChanged();
                    holder.title.setTextColor(context.getColor(R.color.color_shape));
                    ActionData.getInstance().addPlaylistNum(1);
                    ActionData.getInstance().addMediaSongId(songPlay);
                    Log.e("playing"," 1 " + pos);
                }
            }
        });

        if(songPlay==song.songId){
            holder.title.setTextColor(context.getColor(R.color.color_shape));
        }else{
            holder.title.setTextColor(context.getColor(R.color.text_color));
        }

        for(int i = 0; i<count; i++) {
            if (countSelect[i] == position) {
                holder.bgSample.setVisibility(View.VISIBLE);
                return;
            }
            if(i==count-1){
                holder.bgSample.setVisibility(View.GONE);
            }
        }


        holder.addSong.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(select==false){
                    Log.e("select"," 1 " + size);
                    select = true;
                    pos = holder.getAdapterPosition();
                    listSongs.add(songs.get(pos));
                    countSelect[size++] = pos;
                    ActionData.getInstance().addSong(pos, listSongs);
                    // TODO: включаем выделение песни
                    holder.bgSample.setVisibility(View.VISIBLE);
                    count = size;
                    Log.e("select"," 2 " + size);
                }
                return true;
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
        FrameLayout addSong,bgSample;
        public Holder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.page_songs_poster);
            options = itemView.findViewById(R.id.page_songs_options);
            title = itemView.findViewById(R.id.page_songs_title);
            artist = itemView.findViewById(R.id.page_songs_artist);
            addSong = itemView.findViewById(R.id.page_songs_add_song);
            bgSample = itemView.findViewById(R.id.page_songs_bg);
        }
    }
}
