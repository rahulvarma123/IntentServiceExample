package com.example.intentserviceexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imgView);
        progressDialog = new ProgressDialog(this);
        checkRuntimePermissions();
    }

    private void checkRuntimePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, 101);
        } else {
            // Notify the user at the time of App launching
            Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("download");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void download(View view) {
        progressDialog.show();
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           /* String name = intent.getStringExtra("text");
            Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();*/
            /*Bitmap bitmap = intent.getParcelableExtra("image");
            img.setImageBitmap(bitmap);*/
            progressDialog.dismiss();
            String imgPath = intent.getStringExtra("imagepath");
            img.setImageDrawable(Drawable.createFromPath(imgPath));


        }

    };
}
