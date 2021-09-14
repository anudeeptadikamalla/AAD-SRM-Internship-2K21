package com.example.mybroadcastservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

public class MyReceiver extends BroadcastReceiver {
    ImageView imageView;
    public MyReceiver(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        /*throw new UnsupportedOperationException("Not yet implemented");*/
        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:
                imageView.setImageResource(R.drawable.power_on);
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                imageView.setImageResource(R.drawable.power_off);
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                imageView.setImageResource(R.drawable.on);
                break;
            case Intent.ACTION_HEADSET_PLUG:
                imageView.setImageResource(R.drawable.plug);
                break;
        }
    }
}