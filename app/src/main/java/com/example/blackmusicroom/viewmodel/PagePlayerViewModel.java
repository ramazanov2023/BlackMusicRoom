package com.example.blackmusicroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blackmusicroom.data.Song;

public class PagePlayerViewModel extends AndroidViewModel {

    public PagePlayerViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Song> getSong() {
        return PlayerImpl.getInstance().getSong();
    }
}
