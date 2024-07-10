package com.example.hiddenserviceapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Tor service
        TorService torService = new TorService(getFilesDir());
        torService.start();

        // Start Web server
        WebServer webServer = new WebServer();
        webServer.start();
    }
}
