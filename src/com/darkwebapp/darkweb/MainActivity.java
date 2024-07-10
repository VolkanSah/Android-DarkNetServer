package com.darkwebapp.darkweb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * src/com/darkwebapp/darkweb/MainActivity.java
 * The main entry point for the DarkWeb application.
 * Handles the UI and starts the Tor service and web server.
 */
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
