package com.btcm.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.btcm.R;
import com.btcm.api.BitcoinCharts;
import com.btcm.api.CallBack;
import com.btcm.api.CurrencyType;
import com.btcm.api.MarketType;


public class Monitor extends Fragment {
    private TextView txtMtgoxUSD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.monitor, null);
        assert view != null;
        txtMtgoxUSD = (TextView)view.findViewById(R.id.txtMtgoxUSD);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                double bid = (double) msg.obj;
                (txtMtgoxUSD).setText(String.valueOf(bid));
            }
        };

        BitcoinCharts.init(new CallBack() {
            @Override
            public void run() {
                BitcoinCharts.MarketData data;
                if ((data = BitcoinCharts.getOne(MarketType.MTGOX, CurrencyType.USD)) != null) {
                    Message msg = new Message();
                    msg.obj = data.bid;
                    handler.sendMessage(msg);
                }
            }
        });

        return view;
    }
}
