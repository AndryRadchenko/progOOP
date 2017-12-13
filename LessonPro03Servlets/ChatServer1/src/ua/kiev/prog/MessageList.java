package ua.kiev.prog;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.stream.Collectors;

public class MessageList {
	private static final MessageList msgList = new MessageList();
    private static final int LIMIT = 5;
    private static int nextId;

    private final Gson gson;
	private final List<Message> list = new LinkedList<>();

  	public MessageList() {
		gson = new GsonBuilder().create();
	}

    public static MessageList getInstance() {
        return msgList;
    }

    public synchronized void add(Message m) {
        if (list.size() + 1 == LIMIT) {
            list.remove(0);
        }
        m.setId(nextId);
        nextId++;
        list.add(m);
    }

	public synchronized String toJSON(int nextReqId, String login, String chatRoom) {
        if (list.size() == 0){
            return null;
        }else if (nextReqId == nextId ) {
            return null;
        } else {
            List<Message> filteredList = list.stream()
                    .filter(message -> message.getChatRoom().equals(chatRoom) && message.getTo().equals("") || message.getTo().equals(login))
                    .filter(message -> message.getId() >= nextReqId)
                    .collect(Collectors.toCollection(LinkedList::new));

            return gson.toJson(new JsonMessages(filteredList, 0));
        }
	}
}
