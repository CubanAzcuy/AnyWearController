package com.shared;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by robertgross on 4/3/16.
 */
public class WearConnectionHandler implements GoogleApiClient.ConnectionCallbacks {
    private GoogleApiClient _apiClient;
    private MessageApi.MessageListener _messageListener;

    public WearConnectionHandler(Context context) {
        initGoogleApiClient(context);
    }

    public WearConnectionHandler(Context context, MessageApi.MessageListener messageListener) {
        _messageListener = messageListener;

        initGoogleApiClient(context);
    }

    private void initGoogleApiClient(Context context) {
        _apiClient = new GoogleApiClient.Builder(context)
                .addApi( Wearable.API )
                .addConnectionCallbacks( this )
                .build();

        if( _apiClient != null && !( _apiClient.isConnected() || _apiClient.isConnecting() ) )
            _apiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(_messageListener != null) {
            Wearable.MessageApi.addListener(_apiClient, _messageListener);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public GoogleApiClient getAPI() {
        return _apiClient;
    }
}
