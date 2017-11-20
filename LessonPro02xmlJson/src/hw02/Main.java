package hw02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {

		File file = new File("src\\hw02\\jsonIn.txt");
		StringBuilder sb = new StringBuilder();
		try (BufferedReader f = new BufferedReader(new FileReader(file))) {
			String str = "";
			for (; (str = f.readLine()) != null;) {
				sb.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result = sb.toString();
		
		Gson gson = new GsonBuilder().create();
		Json json = (Json) gson.fromJson(result, Json.class);
		
		System.out.println(gson.toJson(json));
	}

}
