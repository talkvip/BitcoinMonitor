package com.btcm;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import com.btcm.api.BitcoinCharts;
import com.btcm.api.CallBack;
import com.btcm.fragment.About;
import com.btcm.fragment.Kline;
import com.btcm.fragment.Monitor;
import com.btcm.fragment.Setting;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class Main extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simple_tabs);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);

        FragmentPagerAdapter adapter = new BtcmAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

    }

    public class BtcmAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> pages;
        private ArrayList<String>   titles;

        public BtcmAdapter(FragmentManager fm, Context ctx) {
            super(fm);
            pages = new ArrayList<>();
            pages.add(new Monitor());
            pages.add(new Kline());
            pages.add(new Setting());
            pages.add(new About());

            titles = new ArrayList<>();
            titles.add(ctx.getString(R.string.menu_monitor));
            titles.add(ctx.getString(R.string.menu_kline));
            titles.add(ctx.getString(R.string.menu_setting));
            titles.add(ctx.getString(R.string.menu_about));
        }

        @Override
        public Fragment getItem(int pos) {
            return pages.get(pos);
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return titles.get(pos);
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
