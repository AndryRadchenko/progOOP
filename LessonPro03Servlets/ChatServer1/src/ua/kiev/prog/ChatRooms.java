package ua.kiev.prog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRooms {

    private static Map<String, ChatRoom> rooms = new HashMap<>();

    public static ChatRoom getRoom(String roomName) {
        return rooms.get(roomName);
    }

    public static void putRoom(String roomName, ChatRoom room) {
        rooms.put(roomName, room);
    }

    public static boolean containsChatRoom (String roomName){
        return rooms.keySet().contains(roomName);
    }
}
