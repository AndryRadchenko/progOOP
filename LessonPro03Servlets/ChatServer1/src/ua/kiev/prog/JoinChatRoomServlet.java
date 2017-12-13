package ua.kiev.prog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JoinChatRoomServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!MyLoginServlet.isAuthorized(req)){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String roomName = req.getParameter("name");
        HttpSession session = req.getSession(false);
        String login = (String)session.getAttribute("login");

        if(roomName.equals("list")){
            sendFarewellMessage(session);
            session.setAttribute("chatRoom", roomName);
        } else if( ChatRooms.containsChatRoom(roomName)) {
            if (ChatRooms.getRoom(roomName).isOnTheChatRoomList(login)){
                sendFarewellMessage(session);
                session.setAttribute("chatRoom", roomName);
            }
            else {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } else{
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void sendFarewellMessage(HttpSession session){
        String login = (String)session.getAttribute("login");
        String oldChatRoom = (String) session.getAttribute("chatRoom");
        String chatRoomForMessage = oldChatRoom.equals("list")? "":oldChatRoom;
        Message msg = new Message("SERVER", "", "USER " + login + " LEAVES CHAT " + chatRoomForMessage);
        msg.setChatRoom(oldChatRoom);
        MessageList.getInstance().add(msg);
    }
}
