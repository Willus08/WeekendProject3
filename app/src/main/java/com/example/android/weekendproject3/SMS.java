package com.example.android.weekendproject3;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// 251 321 4262
public class SMS extends AppCompatActivity {

    TextInputEditText number;
    EditText mesg;
    TextView recived;
    TextView sent;
    private static final String TAG = "";
    MyNeweestReciver reciver = new MyNeweestReciver();
    IntentFilter filter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        number = (TextInputEditText) findViewById(R.id.phone);
        mesg = (EditText) findViewById(R.id.mesage);
        recived = (TextView) findViewById(R.id.tvTextRecived);
        sent = (TextView) findViewById(R.id.tvTextmesg);
    }

    public void SendMessage(View view) {
        Log.d(TAG, "SendMessage: MesageShould send");
        // Toast.makeText(this, "Why Is the SnackBar not showing", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            int My_REQUEST = 1;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, My_REQUEST);
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number.getText().toString(), null, mesg.getText().toString(), null, null);

        }
        Snackbar.make(this.getCurrentFocus(), "Sending Message", Snackbar.LENGTH_SHORT).show();
    }


    public void Doom(MenuItem item) {
        Snackbar.make(this.getCurrentFocus(), "Doom Aproaches", Snackbar.LENGTH_SHORT).show();
    }


    public void test(View view) {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
        Intent FirstMessage = new Intent("first");
        FirstMessage.putExtra("first", "Hi Main Activity how ar yout");
        sendBroadcast(FirstMessage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        filter.addAction("second");
        filter.addAction("fourth");
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(reciver, filter);
    }

    class MyNeweestReciver extends BroadcastReceiver {
        private static final String SMS_BUNDLE = "pdus";


        @Override
        public void onReceive(Context context, Intent intent) {
            Intent ThirdMesage = new Intent("third");
            Intent FithMessage = new Intent("fith");

            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null) {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String smsMessageStr = "";
                for (int i = 0; i < sms.length; ++i) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    String smsBody = smsMessage.getMessageBody().toString();
                    String address = smsMessage.getOriginatingAddress();

                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";
                }
                Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();

                String temp2 = intent.getStringExtra("second");
                Toast.makeText(context.getApplicationContext(), temp2, Toast.LENGTH_SHORT).show();

                ThirdMesage.putExtra("third", "Well thats good sorry I gott cut this short but I need to do something");
                sendBroadcast(ThirdMesage);

                String temp4 = intent.getStringExtra("fourth");
                Toast.makeText(context.getApplicationContext(), temp4, Toast.LENGTH_SHORT).show();


            }
        }
    }
}
