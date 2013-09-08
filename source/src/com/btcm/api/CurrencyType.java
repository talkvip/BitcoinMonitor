package com.btcm.api;

/**
* @author yuhai
*/
public enum CurrencyType {
    USD, CNY;

    public static CurrencyType get(String type) {
        if (type.toUpperCase().equals("USD"))
            return USD;
        else if (type.toUpperCase().equals("CNY"))
            return CNY;
        else return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case USD:   return "USD";
            case CNY:   return "CNY";
            default:    return null;
        }
    }
}
