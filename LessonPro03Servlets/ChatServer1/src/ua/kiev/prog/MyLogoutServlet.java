package ua.kiev.prog;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.*;

public class MyLogoutServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            HttpSession session = req.getSession(false);
            session.removeAttribute("login");
            session.invalidate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

