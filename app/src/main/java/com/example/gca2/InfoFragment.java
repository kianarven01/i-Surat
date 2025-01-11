package com.example.gca2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {
    public InfoFragment() {
        super(R.layout.fragment_info);  // The layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageButton fromInfo = view.findViewById(R.id.from_info);



        fromInfo.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }
}