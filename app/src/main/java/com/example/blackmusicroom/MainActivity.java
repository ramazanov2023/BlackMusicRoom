package com.example.blackmusicroom;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.blackmusicroom.view.screen.PlayerPageFragment;
import com.example.blackmusicroom.view.screen.PlaylistSongsPageFragment;
import com.example.blackmusicroom.view.screen.PlaylistsPageFragment;
import com.example.blackmusicroom.view.screen.SettingsActivity;
import com.example.blackmusicroom.view.screen.SongsPageFragment;



public class MainActivity extends FragmentActivity implements Navigator {
    ViewPager2 viewPager2;
    FragmentStateAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager_2);

        adapter = new PagerFragmentAdapter(this);
        viewPager2.setAdapter(adapter);
    }

    @Override
    public void setPage(int numPage) {
        switch (numPage) {
            case SONGS_PAGE:
                viewPager2.setCurrentItem(SONGS_PAGE);
                break;
            case PLAYLISTS_PAGE:
                viewPager2.setCurrentItem(PLAYLISTS_PAGE);
                break;
            case PLAYLIST_SONGS_PAGE:
                viewPager2.setCurrentItem(PLAYLIST_SONGS_PAGE);
                break;
            case PLAYER_PAGE:
                viewPager2.setCurrentItem(PLAYER_PAGE);
                break;
            case SETTINGS_PAGE:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class PagerFragmentAdapter extends FragmentStateAdapter {
        public PagerFragmentAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
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

    //Strong reference(жесткая ссылка)
//    StrongR strong = new StrongR();

    //Weak reference(слабая ссылка)если object strong удлёт то эта ссылка будет удалена GC
//    WeakReference weak = new WeakReference(strong);
//    strong = null;

    //Soft reference(мягкая ссылка) GC удалит ссылку когда заметит нехватку выделенной памяти
//    SoftReference soft = new SoftReference(strong);
//    strong = null;

    //Phantom reference(фантомная ссылка)
//    PhantomReference phantom = new PhantomReference(strong);
//    strong = null;

    public class StrongR {

    }


}