package ua.kiev.prog;

import java.util.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class ActiveUsersListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        if(event.getName().equals("chatRoom")) {
            HttpSession session = event.getSession();
            String login = (String)session.getAttribute("login");
            String chatRoom = (String)session.getAttribute("chatRoom");

            ActiveUsersList.addActiveUser(login);

            String chatRoomForMessage = chatRoom.equals("list")? "":chatRoom;
            Message msg = new Message("SERVER", "", "USER " + login + " JOINES CHAT " + chatRoomForMessage);
            msg.setChatRoom(chatRoom);
            MessageList.getInstance().add(msg);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals("login")) {
            String login = (String)event.getValue();
            Message msg = new Message("SERVER", "", "USER " + login + " LEFT CHAT");
            msg.setChatRoom("list");
            MessageList.getInstance().add(msg);
            ActiveUsersList.removeActiveUser((String)event.getValue());
        }
    }

}