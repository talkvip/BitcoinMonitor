package com.btcm.api;

/**
 * @author yuhai
 */
public enum MarketType {
        MTGOX;

        public static MarketType get(String type) {
            if (type.toLowerCase().equals("mtgox"))
                return MTGOX;

            else return null;
        }

        @Override
        public String toString() {
            switch (this) {
                case MTGOX:     return "MTGOX";
                default:        return null;
            }
        }
    }
