package com.example.android.weekendproject3;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService implements Serializable {
    private static final String TAG ="" ;
    List<String> list = new ArrayList<>();
    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: intent ");
        Intent sending = new Intent("sending");
        list.add("Hello");
        list.add("How are you");

        sending.putExtra("sent", (Serializable) list);
        sendOrderedBroadcast(sending,null);
       // sendBroadcast(sending);
    }


}
