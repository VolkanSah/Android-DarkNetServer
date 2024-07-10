package com.example.hiddenserviceapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TorService {

    private File appDir;

    public TorService(File filesDir) {
        this.appDir = filesDir;
    }

    public void start() {
        try {
            // Create torrc file
            File torrcFile = new File(appDir, "torrc");
            try (FileWriter writer = new FileWriter(torrcFile)) {
                writer.write("HiddenServiceDir " + new File(appDir, "hidden_service").getAbsolutePath() + "\n");
                writer.write("HiddenServicePort 80 127.0.0.1:8080\n");
                writer.write("Log notice stdout\n");
                writer.write("ControlPort 9051\n");
            }

            // Make tor executable
            File torBinary = new File(appDir, "tor");
            if (!torBinary.exists()) {
                throw new IOException("Tor binary not found");
            }
            torBinary.setExecutable(true);

            // Start Tor
            ProcessBuilder pb = new ProcessBuilder(torBinary.getAbsolutePath(), "-f", torrcFile.getAbsolutePath());
            pb.redirectErrorStream(true);
            pb.directory(appDir);
            Process torProcess = pb.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
