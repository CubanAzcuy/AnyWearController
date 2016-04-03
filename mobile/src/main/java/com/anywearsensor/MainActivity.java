package com.anywearsensor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.shared.WearConnectionHandler;
import com.shared.WearMessageHandler;

public class MainActivity extends AppCompatActivity implements MessageApi.MessageListener {

    private WearConnectionHandler _wearConnectionHandler;
    private WearMessageHandler _wearMessageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _wearConnectionHandler = new WearConnectionHandler(this, this);
        _wearMessageHandler = new WearMessageHandler(_wearConnectionHandler);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String asdfasf = "";
                _wearMessageHandler.sendMessage(WearMessageHandler.WEAR_MESSAGE_PATH, "Nothings to see");
            }
        });
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

    }
}
