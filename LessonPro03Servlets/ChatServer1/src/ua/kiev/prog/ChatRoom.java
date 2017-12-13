package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String roomName;
    private String creator;
    private List<String> userList = new ArrayList<>();

    public ChatRoom(String roomName, String creator, List<String> userList) {
        this.roomName = roomName;
        this.creator = creator;
        this.userList = userList;
    }

    public boolean isOnTheChatRoomList (String login){
        return userList.contains(login);
    }

}
