package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;
	private int id;


	public Message(String from, String text) {
		this.from = from;
		this.text = text;
	}

	public Message(String from, String to,  String text) {
		this.from = from;
		this.to = to;
		this.text = text;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
	
	public static Message fromJSON(String s) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(s, Message.class);
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date)
				.append(", From: ").append(from).append(", To: ").append(to)
				.append("] ").append(text)
                .toString();
	}

//	public int send(String url) throws IOException {
//		URL obj = new URL(url);
//		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//		conn.setRequestMethod("POST");
//		conn.setDoOutput(true);
//
//		OutputStream os = conn.getOutputStream();
//		try {
//			String json = toJSON();
//			os.write(json.getBytes(StandardCharsets.UTF_8));
//			return conn.getResponseCode();
//		} finally {
//			os.close();
//		}
//	}

//	method sends a message and adds cookies to the mesage
	public int send(String url, String cookies) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Cookie", cookies);
		conn.setDoOutput(true);
	
		OutputStream os = conn.getOutputStream();
		try {
			String json = toJSON();
			os.write(json.getBytes(StandardCharsets.UTF_8));
//			System.out.println("Before rsp code");
			return conn.getResponseCode();
		} finally {
			os.close();
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
