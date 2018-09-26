package com.mobileinformationsystems.alienreporter.connector;

public interface HTTPConnectorResponse {
    void onProcessFinished(String json);
}