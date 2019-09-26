package com.example.intentserviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;


public class MyIntentService extends IntentService {
    String imageUrl = "http://freesms8.co.in/downloads/wallpapers/00988_paradiselost_1920x1200.jpg";
    ImageView imgView;

    public MyIntentService() {
        super("image download");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            // 1. Create URL object
            URL url = new URL(imageUrl);
            // 2. Open the Http Connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 3. Checking the Response Code - if 200 - Success else failure
            if (urlConnection.getResponseCode() != 200) {
                throw new Exception("Failed to get response");
            }
            // 4. Get Input Stream from Connection object
            InputStream inputStream = urlConnection.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,150,150,false);
            // Enviroment
            File sdCard = Environment.getExternalStorageDirectory();

            // FOS
            File directory = new File(sdCard.getAbsolutePath() + "/Colors_Images");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File file = new File(directory, "TestImage.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

            // path
            String imagePath = Environment.getExternalStorageDirectory() + "/Colors_Images" + "/TestImage.jpg";


            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("download");
            //broadcastIntent.putExtra("image",scaledBitmap);
            broadcastIntent.putExtra("imagepath", imagePath);

            sendBroadcast(broadcastIntent);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
