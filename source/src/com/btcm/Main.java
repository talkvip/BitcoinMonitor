package com.btcm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class Main extends FragmentActivity {
    private static ArrayList<String> MenuName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MenuName = new ArrayList<String>();
        MenuName.add(getString(R.string.menu_monitor));
        MenuName.add(getString(R.string.menu_kline));
        MenuName.add(getString(R.string.menu_setting));
        MenuName.add(getString(R.string.menu_about));

        setContentView(R.layout.simple_tabs);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        FragmentPagerAdapter adapter = new BtcmAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }

    class BtcmAdapter extends FragmentPagerAdapter {
        public BtcmAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BtcmFragmentFactory.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return MenuName.get(position % MenuName.size()).toUpperCase();
        }

        @Override
        public int getCount() {
            return MenuName.size();
        }
    }
}
