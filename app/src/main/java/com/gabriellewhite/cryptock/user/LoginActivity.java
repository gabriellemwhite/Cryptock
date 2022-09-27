package com.gabriellewhite.cryptock.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gabriellewhite.cryptock.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fb, google, github;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPagerLogin);
        fb = findViewById(R.id.fab_facebook);
        google = findViewById(R.id.fab_google);
        github = findViewById(R.id.fab_github);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter
                (getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(300);
        fb.setTranslationY(300);
        google.setTranslationY(300);
        github.setTranslationY(300);


        tabLayout.setAlpha(v);
        fb.setAlpha(v);
        google.setAlpha(v);
        github.setAlpha(v);


        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        github.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
    }
}