package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int nextreqId;
    User user;


    public GetThread(User user) {
        this.gson = new GsonBuilder().create();
        this.user = user;
    }

    @Override
    public void run() {
        try {
            while ( ! Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + user.getNextReqId());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Cookie", user.getCookies());
                InputStream is = conn.getInputStream();

                try {
                    byte[] buf = requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null && list.getList().size()>0) {
                        for (Message m : list.getList()) {
                                System.out.println("\t" + m);
                        }
                        int k = list.getList().size();
                        nextreqId = list.getList().get(list.getList().size() - 1).getId() + 1 ;
                        user.setNextReqId(nextreqId);
                    }
                } finally {
                    is.close();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
