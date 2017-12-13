package ua.kiev.prog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.servlet.http.*;

public class MyLoginServlet extends HttpServlet {

//    static final String TEMPLATE = "<html>" +
//            "<head><title>Prog.kiev.ua</title></head>" +
//            "<body><h1>%s</h1></body></html>";

    static final Map<String, String> cred = new HashMap<>();

    static {
        // hardcode login credentials
        cred.put("user", "qwerty");
        cred.put("jafora", "qwerty");
        cred.put("admin", "qazwsx");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = AddServlet.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        Message msg = Message.fromJSON(bufStr);

        String login = msg.getFrom();
        String password = msg.getText();

        if (cred.containsKey(login) && password.equals(cred.get(login))) {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", login);
            session.setAttribute("chatRoom", "");
            session.setAttribute("chatRoom", "list");
            String sessionId = session.getId();

            try (OutputStream os = resp.getOutputStream()) {
                byte[] msgeBytes = sessionId.getBytes();
                os.write(msgeBytes);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public static boolean isUserWithinLoginsSet(String user) {
        Set<String> set = new HashSet<>(cred.keySet());
        return set.contains(user);
    }

    public static boolean isAuthorized (HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session == null)
            return false;
        return true;
    }
}
