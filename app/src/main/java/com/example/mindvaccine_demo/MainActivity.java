package com.example.mindvaccine_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, ServiceRoutine.class);
        stopService(serviceIntent);

        // 툴 바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // 툴 바 홈버튼 설정
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.consult);

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

        // 툴 바 타이틀 설정
        switch (id) {
            case R.id.navigation_1:
                getSupportActionBar().setTitle("나의 상태");
                break;
            case R.id.navigation_2:
                getSupportActionBar().setTitle("소통의 광장");
                break;
            case R.id.navigation_3:
                getSupportActionBar().setTitle("대화하기");
                break;
            case R.id.navigation_4:
                getSupportActionBar().setTitle("추천 상담사");
                break;
        }

        fragmentTransaction.setPrimaryNavigationFragment(nextFragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

    // 툴바 설정
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // 툴바 옵션 설정 -> 버튼 클릭 이벤트 넣으면댐
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                // 누르면 할거
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        Intent serviceIntent = new Intent(this, ServiceRoutine.class);
        ContextCompat.startForegroundService(this, serviceIntent);
        super.onDestroy();
    }
}