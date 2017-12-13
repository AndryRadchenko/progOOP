package ua.kiev.prog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateChatRoomServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!MyLoginServlet.isAuthorized(req)){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        HttpSession session = req.getSession(false);
        String login = (String)session.getAttribute("login");
        List <String> userList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(req.getInputStream())){
//                try {
                    userList = (ArrayList)ois.readObject();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
            } catch (ClassNotFoundException | EOFException e){
        }

        String roomName = userList.get(userList.size() - 1);
        userList.remove(userList.size() - 1);

        if (ChatRooms.containsChatRoom(roomName)){
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        ChatRoom room = new ChatRoom(roomName, login, userList);
        ChatRooms.putRoom(roomName, room);

        for (String user : userList){
            Message msg = new Message(login, user, "YOU'VE BEEN INVITED TO " + roomName + " CHATROOM");
            msg.setChatRoom("list");
            MessageList.getInstance().add(msg);
        }

    }
}
