package hw02ServerResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {

		String answer = "";
		answer += "<html><head><title>sysinfo</title><meta charset='utf-8'></head><body><br>";
		answer += ("os.name: " + System.getProperty("os.name") + " <br>");
		answer += ("os.version: " + System.getProperty("os.version") + " <br>");
		answer += ("os.architecture: " + System.getProperty("os.arch") + " <br>");
		answer += ("availableProcessors: " + Runtime.getRuntime().availableProcessors() + " <br>");
		answer += ("totalMemorytoJVM: " + Runtime.getRuntime().totalMemory() + " <br>");
		answer += ("freeMemorytoJVM: " + Runtime.getRuntime().freeMemory() + " <br>");
		answer += "</body></html>";

		try (ServerSocket serSoc = new ServerSocket(8080)) {
			while (true) {
				Socket socket = serSoc.accept();
				new Client(socket, answer);
			}
		} catch (IOException e) {
			System.out.println("Error to server Socket Open!!!");
		}
	}
}