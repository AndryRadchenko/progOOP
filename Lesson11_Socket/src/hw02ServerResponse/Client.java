package hw02ServerResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	private Socket socket;
	private String answer;
	private Thread th;

	public Client(Socket socket, String answer) {
		super();
		this.socket = socket;
		this.answer = answer;
		th = new Thread(this);
		th.start();
	}

	public void run() {
		try (InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os)) {
			byte[] rec1 = new byte[is.available()];
			is.read(rec1);
			String response = "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type:text/html\r\n"
					+ "Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";
			pw.print(response + answer);
			pw.flush();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}