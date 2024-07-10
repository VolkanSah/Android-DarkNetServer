// MainActivity.java
package com.example.hiddenservice;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupTorConfig();
        startTor();
        startWebServer();
    }
    
    private void setupTorConfig() {
        // Verzeichnisse erstellen
        File hiddenServiceDir = new File(getFilesDir(), "hidden_service");
        if (!hiddenServiceDir.exists()) {
            hiddenServiceDir.mkdirs();
        }

        // torrc Datei erstellen
        File torrcFile = new File(getFilesDir(), "torrc");
        try (FileWriter writer = new FileWriter(torrcFile)) {
            writer.write("HiddenServiceDir " + hiddenServiceDir.getAbsolutePath() + "\n");
            writer.write("HiddenServicePort 80 127.0.0.1:8080\n");
            writer.write("Log notice stdout\n");
            writer.write("ControlPort 9051\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startTor() {
        // Tor starten
        try {
            File torBinary = new File(getFilesDir(), "tor");
            ProcessBuilder pb = new ProcessBuilder(torBinary.getAbsolutePath(), "-f", 
                new File(getFilesDir(), "torrc").getAbsolutePath());
            pb.redirectErrorStream(true);
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void startWebServer() {
        // Webserver starten
        try {
            SimpleWebServer server = new SimpleWebServer(8080, new File("/path/to/webroot"));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
