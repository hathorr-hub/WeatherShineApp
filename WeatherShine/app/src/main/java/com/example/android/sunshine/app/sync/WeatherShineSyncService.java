package com.example.android.sunshine.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WeatherShineSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WeatherShineSyncAdapter sWeatherShineSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("WeatherShineSyncService", "onCreate - WeatherShineSyncService");
        synchronized (sSyncAdapterLock) {
            if (sWeatherShineSyncAdapter == null) {
                sWeatherShineSyncAdapter = new WeatherShineSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sWeatherShineSyncAdapter.getSyncAdapterBinder();
    }
}