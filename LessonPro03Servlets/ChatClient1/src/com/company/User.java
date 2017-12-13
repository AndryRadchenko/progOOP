package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class User {
    private String login;
    private String cookies;
    private boolean isLoggedIn;
    private int nextReqId;
    private Map<String, Integer> nextReqMap = new HashMap<>();
    private String activeList;
    private Scanner scanner;

    public User() {
        super();
    }

    public User(String login, String cookies) {
        this.login = login;
        this.cookies = cookies;
        this.nextReqId = 0;
        this.isLoggedIn = false;
        this.activeList = "list";
        this.nextReqMap.put(this.activeList, 0);
        this.scanner = new Scanner(System.in);
    }

    public String getCookies() {
        return cookies;
    }

    public String getActiveList() {
        return activeList;
    }

    public void dropNonActiveListWithNextReqIdAndGetNewOnes(String list) {
        nextReqMap.put(activeList, nextReqId);

        this.activeList = list;

        if (nextReqMap.keySet().contains(list))
            nextReqId = nextReqMap.get(list);
        else
            nextReqId = 0;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public int getNextReqId() {
        return nextReqId;
    }

    public void setNextReqId(int nextReqId) {
        this.nextReqId = nextReqId;
    }


    public void login(){
        do {
            try{
                System.out.println("ENTER YOUR LOGIN: ");
                this.login = this.scanner.nextLine();
                System.out.println("ENTER YOUR PASSWORD: ");
                String password= scanner.nextLine();

                Message m = new Message(login, password);
                HttpURLConnection conn = postConnection("/login");

                OutputStream os = conn.getOutputStream();
                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(m);
                os.write(json.getBytes(StandardCharsets.UTF_8));

                if(conn.getResponseCode() == 200) {
                    this.cookies = conn.getHeaderFields().get("Set-Cookie").toString();
                    this.isLoggedIn = true;
                } else {
                    System.out.println("WARNING: WRONG LOGIN/PASSWORD");
                }
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } while (isLoggedIn== false);
    }

    public HttpURLConnection postConnection(String urlAddOn){
        String urls = Utils.getURL() + urlAddOn;
        try {
            URL obj = new URL(urls);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            if(this.cookies!=null)
                conn.setRequestProperty("Cookie", this.cookies);
            return conn;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String sendMessages (){

        String command = "";
        try{
            while (true) {
                if (this.getActiveList().equals("list"))
                    System.out.println("ENTER YOUR MESSAGE TO EVERYBODY: ");
                else
                    System.out.println("ENTER YOUR MESSAGE TO THE " + this.getActiveList() + " CHATROOM");
                String text = scanner.nextLine();
//			if a line starts with a backslash it gets treated as a command rather than a message
                if (text.isEmpty())
                    continue;

                if(text.substring(0, 1).equals("\\")){
                    command = text;
                    break;
                }

                Message m = new Message(this.login, "", text);
                int res = m.send(Utils.getURL() + "/add", this.cookies);
                if (res == 401){
                    System.out.println("WARNING: NOT AUTHORIZED");
                    command = "\\logout";
                    break;
                } else if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    continue;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return command;
    }

    public void logout() {
        try{
            postConnection("/logout");
            HttpURLConnection conn = postConnection("/logout");
            InputStream is = conn.getInputStream();
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
    }

    public void commandExecutor(String command){
        command = command.substring(1, command.length());

        if(command.equals("?")) {
            System.out.println("\\pm (send a private message)");
            System.out.println("\\getActiveUsers");
            System.out.println("\\checkStatus");
            System.out.println("\\createChatRoom");
            System.out.println("\\joinChatRoom");
        }
        else if(command.equals("pm"))
            sendPrivateMessage();
        else if(command.equals("getActiveUsers"))
            getActiveUsers();
        else if(command.equals("checkStatus"))
            checkStatus();
        else if(command.equals("createChatRoom"))
            createChatRoom();
        else if(command.equals("joinChatRoom"))
            joinChatRoom();
        else
            System.out.println("COMMAND NOT FOUND");
    }

    public void sendPrivateMessage (){
        String text = "";
        String to = "";

        try{
            while(text == "") {
                System.out.println("ENTER YOUR PRIVATE MESSAGE: ");
                text = scanner.nextLine();
            }

            while(to=="") {
                System.out.println("Private message to: ");
                to = scanner.nextLine();
            }

            Message m = new Message(login, to, text);
            int res = m.send(Utils.getURL() + "/add", cookies);

            if (res != 200) { // 200 OK
                System.out.println("HTTP error occured: " + res);
                return;
            }
            System.out.println("PRIVATE MESSAGE HAS BEEN SENT");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public  void getActiveUsers(){
        try {
            HttpURLConnection conn = postConnection("/getusers");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result = br.lines().collect(Collectors.joining());
            System.out.println(result);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void checkStatus () {
        try {
            System.out.println("USER STATUS TO BE CHECKED:");
            String loginReq = scanner.nextLine();
            URL obj = new URL(Utils.getURL() + "/checkStatus?user=" + loginReq);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestProperty("Cookie", cookies);
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = br.lines().collect(Collectors.joining());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createChatRoom () {
        String chatRoomName = "";
        String usersAllowed = "";

        while(chatRoomName.isEmpty() || chatRoomName.equals("list")) {
            System.out.println("ENTER THE CHAT ROOM NAME: ");
            chatRoomName = scanner.nextLine();
            if(chatRoomName.equals("list"))
                System.out.println("WARNING: THIS NAME IS RESERVED");
        }

        while(usersAllowed.isEmpty()) {
            System.out.println("ENTER USERS ALLOWED (SEPARATED WITH COMMAS): ");
            usersAllowed = scanner.nextLine();
        }

        List<String> usersList = new ArrayList<>();
        String[] users = usersAllowed.trim().split(",");
        usersList.addAll(Arrays.asList(users));
        if(!usersList.contains(login))
            usersList.add(login);
        usersList.add(chatRoomName);

        HttpURLConnection conn = postConnection("/createchatroom");
        try (ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream())) {
            oos.writeObject(usersList);
            if(conn.getResponseCode() == 403)
                System.out.println("WARNING: CHOOSE ANOTHER CHAT ROOM NAME");
            else
                System.out.println("CHAT ROOM CREATED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void joinChatRoom () {
        System.out.println("ENTER THE CHAT ROOM NAME: ");
        String newChatRoomName = scanner.nextLine();
        if(newChatRoomName.equals("list"))
            System.out.println("THIS NAME IS RESERVED");
        if(newChatRoomName.equals(""))
            newChatRoomName = "list";

        try {
            URL obj = new URL(Utils.getURL() + "/joinchatroom?name=" + newChatRoomName);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestProperty("Cookie", cookies);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            if(conn.getResponseCode() == 200) {
                dropNonActiveListWithNextReqIdAndGetNewOnes(newChatRoomName);
            } else if (conn.getResponseCode() == 403) {
                System.out.println("WARNING: YOU ARE NOT ALLOWED");
            } else if (conn.getResponseCode() == 404){
                System.out.println("WARNING: NO CHAT ROOM");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
