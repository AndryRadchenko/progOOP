package ua.kiev.prog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!MyLoginServlet.isAuthorized(req)){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String user = req.getParameter("user");
        String res;

        if (MyLoginServlet.isUserWithinLoginsSet(user)){
            if (ActiveUsersList.isUserWithinActiveUsersSet(user)) {
                res = "USER " + user + " IS REGISTERED AND ONLINE";
            } else {
                res = "USER " + user + " IS REGISTERED AND OFFLINE";
            }
        } else {
            res = "USER " + user + " IS NOT REGISTERED";
        }

        try (PrintWriter pw = new PrintWriter(resp.getOutputStream())) {
            pw.println(res);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
