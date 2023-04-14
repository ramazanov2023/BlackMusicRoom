package com.example.blackmusicroom.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class PageFragmentAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> pages;
    public PageFragmentAdapter(@NonNull FragmentManager fragmentManager,
                               @NonNull Lifecycle lifecycle,
                               ArrayList<Fragment> pages) {
        super(fragmentManager, lifecycle);
        this.pages = pages;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return pages.get(position);
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }
}
