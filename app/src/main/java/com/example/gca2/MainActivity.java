package com.example.gca2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Your layout with ViewPager2

        // Initialize ViewPager2
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        MainPagerAdapter adapter = new MainPagerAdapter(this);
        viewPager.setAdapter(adapter);

    }
}
