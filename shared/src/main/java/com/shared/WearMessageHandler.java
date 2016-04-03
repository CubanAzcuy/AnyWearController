package com.shared;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by robertgross on 4/3/16.
 */
public class WearMessageHandler {

    WearConnectionHandler _wearConnectionHandler;

    public WearMessageHandler(WearConnectionHandler wearConnectionHandler){
        _wearConnectionHandler = wearConnectionHandler;
    }

    public void sendMessage(final String path, final String text) {
        if(_wearConnectionHandler != null && _wearConnectionHandler.getAPI() != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(_wearConnectionHandler.getAPI()).await();
                    for (Node node : nodes.getNodes()) {
                        MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                                _wearConnectionHandler.getAPI(), node.getId(), path, text.getBytes()).await();
                    }
                }
            }).start();
        }
    }

    public static String WEAR_MESSAGE_PATH = "/message";
}
