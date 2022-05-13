package com.example.collegealumni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.collegealumni.fragment.add.AddFragment;
import com.example.collegealumni.fragment.college.CollegeFragment;
import com.example.collegealumni.fragment.feed.FeedFragment;
import com.example.collegealumni.fragment.profile.ProfileFragment;
import com.example.collegealumni.fragment.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener( bottomNavigationItemChangeListener());

        // Keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    new ProfileFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.profile);
        }
    }

    private NavigationBarView.OnItemSelectedListener bottomNavigationItemChangeListener() {
        return item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {

                case R.id.home:
                    selectedFragment = new FeedFragment();
                    break;

                case R.id.search:
                    selectedFragment = new SearchFragment();
                    break;

                case R.id.add:
                    selectedFragment = new AddFragment();
                    break;

                case R.id.collge:
                    selectedFragment = new CollegeFragment();
                    break;

                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    selectedFragment).commit();
            return true;
        };
    }
}