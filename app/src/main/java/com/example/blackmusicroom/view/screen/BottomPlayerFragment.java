package com.example.blackmusicroom.view.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.blackmusicroom.Navigator;
import com.example.blackmusicroom.NavigatorImpl;
import com.example.blackmusicroom.R;

public class BottomPlayerFragment extends Fragment {
    ConstraintLayout openPlayerPage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_player,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        openPlayerPage = view.findViewById(R.id.bottom_player_open_player_page);


        Navigator navigator = NavigatorImpl.getInstance();

        openPlayerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.openPage(Navigator.PLAYER_PAGE);
            }
        });

    }
}
