package com.btcm;

import android.support.v4.app.Fragment;

public final class BtcmFragmentFactory extends Fragment {

    public static Fragment get(int position)
    {
        switch(position)
        {
            case 0: return new MonitorFragment();
            case 1: return new KlineFragment();
            case 2: return new SettingFragment();
            case 3: return new AboutFragment();
            default : return null;
        }
    }
}
