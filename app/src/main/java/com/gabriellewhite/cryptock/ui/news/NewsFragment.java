package com.gabriellewhite.cryptock.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gabriellewhite.cryptock.R;
import com.google.android.material.tabs.TabLayout;

public class NewsFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View x = inflater.inflate(R.layout.fragment_news, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return x;
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new GeneralNewsFragment();
                case 1:
                    return new ForexNewsFragment();
                case 2:
                    return new CryptoNewsFragment();
                case 3:
                    return new MergerNewsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "General";
                case 1:
                    return "Forex";
                case 2:
                    return "Crypto";
                case 3:
                    return "Merger";
            }
            return null;
        }
    }
}