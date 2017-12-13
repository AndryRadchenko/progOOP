package ua.kiev.prog;

import java.util.HashSet;
import java.util.Set;

public class ActiveUsersList {

    private static final Set<String> activeUsers = new HashSet<>();

    public static Set<String> getActiveUsers() {
        return activeUsers;
    }

    public static void addActiveUser(String login) {
        activeUsers.add(login);
    }

    public static void removeActiveUser(String login) {
        activeUsers.remove(login);
    }

    public static boolean isUserWithinActiveUsersSet(String user) {
        return activeUsers.contains(user);
    }


}
