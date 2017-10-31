package hw03pagetoLinks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {

		String urlIn = "https://prog.kiev.ua/forum/index.php/";
		String linksOut = "links.txt";

		String str = pageParse(urlIn);
		Map<Integer, String> map = pageToMap(str);
		mapToFile(map, linksOut);

	}

	public static String pageParse(String urlIn) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL("https://prog.kiev.ua/forum/index.php/");
			URLConnection urlc = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			String text = "";
			for (; (text = br.readLine()) != null;) {
				sb.append(text).append(System.lineSeparator());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}

	public static Map<Integer, String> pageToMap(String str) {
		Map<Integer, String> map = new HashMap<>();
		int i = 0;
		while ((i = str.indexOf("href=\"http")) > 0) {
			i += 6;
			int k = str.substring(i).indexOf('"');
			String f = str.substring(i, i + k);
			if (!map.containsKey(f))
				map.put(f.hashCode(), f);
			i += k;
			str = str.substring(i);
		}
		return map;
	}

	public static void mapToFile(Map<Integer, String> map, String file) {
		Set<Integer> urls = map.keySet();
		try (BufferedWriter f = new BufferedWriter(new FileWriter(file))) {
			for (Integer u : urls) {
				f.write(map.get(u));
				f.write(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("open " + file);
	}
}