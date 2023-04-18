package com.example.blackmusicroom.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.blackmusicroom.view.screen.PlayerPageFragment;
import com.example.blackmusicroom.view.screen.PlaylistSongsPageFragment;
import com.example.blackmusicroom.view.screen.PlaylistsPageFragment;
import com.example.blackmusicroom.view.screen.SongsPageFragment;

import java.util.ArrayList;

public class PageFragmentAdapter extends FragmentStateAdapter {
    public PageFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SongsPageFragment();
            case 1:
                return new PlaylistsPageFragment();
            case 2:
                return new PlaylistSongsPageFragment();
            case 3:
                return new PlayerPageFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
