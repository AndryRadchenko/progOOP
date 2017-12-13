package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetUsers extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!MyLoginServlet.isAuthorized(req)){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String users = String.join(", ", ActiveUsersList.getActiveUsers());
        int n  = users.length();
        if (users.substring(n - 2, n).equals(", "))
            users = users.substring(0, n - 2);

        try (PrintWriter pw = new PrintWriter(resp.getOutputStream());){
            pw.write("ACTIVE USERS: " + users);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
