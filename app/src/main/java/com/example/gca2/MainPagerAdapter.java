package com.example.gca2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainPagerAdapter extends FragmentStateAdapter {

    // Constructor for the adapter
    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity); // Pass the activity context
    }

    // Create fragments based on position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: // First tab or page
                return new FrontPageFragment();
            case 1: // Second tab or page
                return new MaterialFragment();
            default: // Default case (optional)
                return new FrontPageFragment();
        }
    }

    // Return the total number of pages/fragments
    @Override
    public int getItemCount() {
        return 2; // Number of fragments to display
    }
}

