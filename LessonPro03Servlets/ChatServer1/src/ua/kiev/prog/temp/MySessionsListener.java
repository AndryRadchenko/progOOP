package ua.kiev.prog;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionsListener implements HttpSessionListener {

//    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public synchronized void sessionCreated(HttpSessionEvent event) {
//        HttpSession session = event.getSession();
//        sessions.put(session.getId(), session);
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent event) {
//        sessions.remove(event.getSession().getId());
    }

//    public static Map<String, HttpSession> getSessions() {
//        return sessions;
//    }

//    public static HttpSession find(String sessionId) {
//        return sessions.get(sessionId);
//    }

}
