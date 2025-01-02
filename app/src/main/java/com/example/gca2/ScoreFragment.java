package com.example.gca2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ScoreFragment extends Fragment {
    Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        nextButton = rootView.findViewById(R.id.nextButton);
        if (bundle != null) {
            String message = bundle.getString("score", "No message received");
            // Use the data in the Fragment (e.g., display it in a TextView)
            TextView textView = rootView.findViewById(R.id.score);
            textView.setText(message);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigateToFragment(new FrontPageFragment());
                getParentFragmentManager().popBackStack();
                getParentFragmentManager().popBackStack();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
    private void navigateToFragment(Fragment fragment) {
        if (getActivity() != null) {
            // Replace the current fragment and add the transaction to the back stack
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}