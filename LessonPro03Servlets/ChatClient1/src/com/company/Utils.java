package com.company;

public class Utils {
    private static final String URL = "http://127.0.0.1";
//    private static final String URL = "http://localhost.fiddler";
    private static final int PORT = 8080;

    public static String getURL() {
        return URL + ":" + PORT;
    }
}
