package com.example.android.weekendproject3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {
    private static final String TAG = "";
    RecyclerView listView;
    MyNewReciver myNewReciver = new MyNewReciver();
    ListAdapter listAdapter;

    RecyclerView.LayoutManager layoutManager;
    DefaultItemAnimator itemAnimator;
    private List<String> random = new ArrayList<String>();
    IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.rvList);


        layoutManager = new LinearLayoutManager(this);

        itemAnimator = new DefaultItemAnimator();

        listView.setLayoutManager(layoutManager);
        listView.setItemAnimator(itemAnimator);



    }

    public void sms(View view) {
    }

    public void list(View view) {

        Intent intent = new Intent(this,MyIntentService.class);

        startService(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        intentFilter.addAction("sending");
        intentFilter.addAction("first");
        intentFilter.addAction("third");
        intentFilter.addAction("fith");
        registerReceiver(myNewReciver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myNewReciver);
    }

    public void sendMessagingSystem(View view) {
        Intent toSmsIntent = new Intent(this,SMS.class);
        startActivity(toSmsIntent);
    }


    public class MyNewReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: Meesage Recived");
            random = (List<String>) intent.getSerializableExtra("sent");
            listAdapter = new ListAdapter(random);
            listView.setAdapter(listAdapter);

            String temp;
            temp = intent.getStringExtra("first");
            Toast.makeText(context.getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
            Intent secondMesage = new Intent("second");
            secondMesage.putExtra("second", "Good Sms. How are you");
            sendBroadcast(secondMesage);

            Intent fourthMesage = new Intent("fourth");
            String temp3 = intent.getStringExtra("third");
            Toast.makeText(context.getApplicationContext(), temp3, Toast.LENGTH_SHORT).show();

            fourthMesage.putExtra("fourth", "Ok then I'll talk to you later then");
            sendBroadcast(fourthMesage);
        }

    }
}
