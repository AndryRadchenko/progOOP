package ua.kiev.prog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    if (!MyLoginServlet.isAuthorized(req)){
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        HttpSession session = req.getSession(false);
        String chatRoom = (String)session.getAttribute("chatRoom");

	    byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Message.fromJSON(bufStr);
        msg.setChatRoom(chatRoom);
		if (msg != null)
		    msgList.add(msg);
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	public static byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
