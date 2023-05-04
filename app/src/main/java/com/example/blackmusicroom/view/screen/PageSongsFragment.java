package com.example.blackmusicroom.view.screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blackmusicroom.MainActivity;
import com.example.blackmusicroom.Options;
import com.example.blackmusicroom.R;
import com.example.blackmusicroom.data.Song;
import com.example.blackmusicroom.view.adapter.PageSongsAdapter;
import com.example.blackmusicroom.viewmodel.PageSongsViewModel;

import java.util.ArrayList;

public class PageSongsFragment extends Fragment {
    RecyclerView recyclerView;
    PageSongsViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songs_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.page_songs_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new PageSongsAdapter(getActivity(), new ArrayList<>()));

        if (!checkPermission()) {
            requestPermission();
        }else{
            viewModel = ViewModelProviders.of(this).get(PageSongsViewModel.class);
            viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<ArrayList<Song>>() {
                @Override
                public void onChanged(ArrayList<Song> songs) {
                    Log.e("traking"," 5 - onChanged");
                    recyclerView.swapAdapter(new PageSongsAdapter(getActivity(),songs),true);
                }
            });
        }


    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;
        } else {
            return false;
        }
    }

    public void requestPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "READ PERMISSION ID REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT).show();
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("traking"," 1 - requestCode");
                    viewModel = ViewModelProviders.of(this).get(PageSongsViewModel.class);
                    viewModel.getSongs().observe(getViewLifecycleOwner(), new Observer<ArrayList<Song>>() {
                        @Override
                        public void onChanged(ArrayList<Song> songs) {
                            Log.e("traking"," 5 - onChanged");
                            recyclerView.swapAdapter(new PageSongsAdapter(getActivity(),songs),true);
                        }
                    });
                }
        }
    }
}
