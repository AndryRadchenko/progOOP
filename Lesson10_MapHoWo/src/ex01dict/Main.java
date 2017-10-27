package ex01dict;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

//Перевод дословный, знаки препинания отсутствуют
//Словарный запас достаточен для "перевода" предложения
//"Who is on duty today?" (классика)

public class Main {

	public static void main(String[] args) {
		// предварительная запись текста в файл
		textToFile("Who is on duty today?", "English.in");

		// чтение текста из файла
		List<String> words = readFile("English.in");

		HashMap<String, String> dictio = new HashMap<>();
		dictio.put("duty", "чергування");
		dictio.put("who", "хто");
		dictio.put("today", "сьогодні");
		dictio.put("on", "на");
		dictio.put("is", "є");

		// ручное наполнение
		wordAdd(dictio);

		// сохранение словаря в файл
		new DictioToFile(dictio);

		// перевод
		translate(words, dictio, "Ukrainian.out");

	}

	// предварительная запись текста в файл
	public static void textToFile(String text, String out) {
		try (BufferedWriter f = new BufferedWriter(new FileWriter(out))) {
			f.write(text);
			f.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// чтение текста из файла
	public static List<String> readFile(String in) {
		List<String> words = new ArrayList<>();
		String str = "";
		try (BufferedReader h = new BufferedReader(new FileReader(in))) {
			for (; (str = h.readLine()) != null;) {
				String[] splitted = str.split("[\\W]");
				for (String word : splitted)
					words.add(word.toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}

	// перевод текста
	public static void translate(List<String> words, HashMap<String, String> dictio, String out) {
		try (BufferedWriter f = new BufferedWriter(new FileWriter(out))) {
			for (String word : words) {
				String res = dictio.get(word);
				f.write((res != null ? res : word) + " ");
			}
			f.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Ручное наполнение словаря
	public static void wordAdd(HashMap<String, String> dictio) {
		try {
			String key = String.valueOf(JOptionPane.showInputDialog("Слово англійською мовою"));
			if (key.equals("null") || key.equals(""))
				throw new IllegalArgumentException();
			String value = String.valueOf(JOptionPane.showInputDialog("Слово українською мовою"));
			if (value.equals("null") || value.equals(""))
				throw new IllegalArgumentException();
			dictio.put(key, value);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Щось уведене неправильно");
		}
	}

	
}
