package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (!MyLoginServlet.isAuthorized(req)){
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

    	String fromStr = req.getParameter("from");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}

		HttpSession session = req.getSession(false);
		String login = (String)session.getAttribute("login");
		String chatRoom = (String)session.getAttribute("chatRoom");

		String json = msgList.toJSON(from, login, chatRoom);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);
		}
	}
}
