package com.btcm.api;

import android.util.Log;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yuhai
 */
public class BitcoinCharts {

    public static class MarketData {
        public double   high;
        public int      latest_trade;
        public double   bid;
        public double   volume;
        public String   currency;
        public double   currency_volume;
        public double   ask;
        public double   close;
        public double   avg;
        public String   symbol;
        public double   low;

        @Override
        public String toString() {
            return new StringBuilder().append(symbol).append(": ").append(bid).toString();
        }
    }

    private static MarketData[] data;
    private static CallBack cb = new CallBack() { @Override public void run() {} };

    public static MarketData getOne(MarketType market, CurrencyType curr) {
        for (MarketData d : data) {
            if(d.symbol.toLowerCase().equals(market.toString().toLowerCase()+curr.toString().toLowerCase()))
                return d;
        }
        return null;
    }

    public static void init(CallBack finishCb) {
        refresh(finishCb);
    }

    public static void refresh(CallBack finishCb) {
        cb = finishCb;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://api.bitcoincharts.com/v1/markets.json");
                    URLConnection con = url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuffer sb = new StringBuffer();
                    int c;
                    while((c = in.read()) != -1) {
                        sb.append((char)c);
                    }
                    String json = sb.toString();

                    data = new GsonBuilder().serializeNulls().create().fromJson(json, MarketData[].class);
                    if (cb != null)
                        cb.run();
                } catch (IOException e) {
                    Log.e("api", "refresh data of bitcoincharts fail");
                }
            }
        }).start();
    }

    public static void main(String[] args) {}
}
