package com.darkwebapp.darkweb;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;

/**
 * WebServer.java
 * Implements a simple web server using NanoHTTPD.
 */
public class WebServer extends NanoHTTPD {

    public WebServer() {
        super(8080);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello, World!</h1></body></html>";
        return newFixedLengthResponse(msg);
    }

    public void start() {
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            System.out.println("Web server started on port 8080");
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }
}
