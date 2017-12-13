package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.deploy.net.HttpResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;
import javax.servlet.http.Cookie;

public class Main {
	public static void main(String[] args) {

		while(true){

			User user = new User(null, null);
			user.login();

			Thread th = new Thread(new GetThread(user));
			th.setDaemon(true);
			th.start();


			while(user.isLoggedIn()) {
				String command = user.sendMessages();

				if (!command.equals("\\logout")) {
					user.commandExecutor(command);
				} else {
                    th.interrupt();
					user.logout();
					break;
				}
			}
		}
	}
}
