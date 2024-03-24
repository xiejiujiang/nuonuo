package com.example.nuonuo.entity.mida.dddetail;

import java.io.IOException;

public enum OrderStatus {
    DELIVERING, FINISHED, ORDER_CANCELLED, ORDER_RECEIVED, ORDER_VERIFIED, PICKING, RECEIVED, WAIT_PICKING;

    public String toValue() {
        switch (this) {
            case DELIVERING: return "DELIVERING";
            case FINISHED: return "FINISHED";
            case ORDER_CANCELLED: return "ORDER_CANCELLED";
            case ORDER_RECEIVED: return "ORDER_RECEIVED";
            case ORDER_VERIFIED: return "ORDER_VERIFIED";
            case PICKING: return "PICKING";
            case RECEIVED: return "RECEIVED";
            case WAIT_PICKING: return "WAIT_PICKING";
        }
        return null;
    }

    public static OrderStatus forValue(String value) throws IOException {
        if (value.equals("DELIVERING")) return DELIVERING;
        if (value.equals("FINISHED")) return FINISHED;
        if (value.equals("ORDER_CANCELLED")) return ORDER_CANCELLED;
        if (value.equals("ORDER_RECEIVED")) return ORDER_RECEIVED;
        if (value.equals("ORDER_VERIFIED")) return ORDER_VERIFIED;
        if (value.equals("PICKING")) return PICKING;
        if (value.equals("RECEIVED")) return RECEIVED;
        if (value.equals("WAIT_PICKING")) return WAIT_PICKING;
        throw new IOException("Cannot deserialize OrderStatus");
    }
}
