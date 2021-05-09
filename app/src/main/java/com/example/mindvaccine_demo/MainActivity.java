package com.example.mindvaccine_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 바텀 네비게이션 설정
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                BottomNavigate(item.getItemId());
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_1);
    }
    
    // 바텀 네비게이션 Fragment 이동
    private void BottomNavigate(int id) {
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if(currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment nextFragment = fragmentManager.findFragmentByTag(tag);
        if (nextFragment == null) { // id로 검색
            if (id == R.id.navigation_1) {
                nextFragment = new DashboardFragment();
            } else if (id == R.id.navigation_2) {
                nextFragment = new ChattingFragment();
            } else if (id == R.id.navigation_3) {
                nextFragment = new ChatbotFragment();
            } else if (id == R.id.navigation_4) {
                nextFragment = new ConsultingFragment();
            } else {
                // Null Fragment Exception
            }
            fragmentTransaction.add(R.id.content_layout, nextFragment, tag);
        } else { // 바로 표시
            fragmentTransaction.show(nextFragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(nextFragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }
}