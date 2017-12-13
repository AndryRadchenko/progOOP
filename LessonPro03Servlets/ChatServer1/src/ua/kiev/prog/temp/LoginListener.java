package ua.kiev.prog;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginListener implements HttpSessionBindingListener {

//    private static final Map<String, HttpSession> logins = new HashMap<String, HttpSession>();
    private static final List<Object> logins = new ArrayList<>();
//
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
//        logins.add(this);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
//        logins.remove(this);
    }

}
