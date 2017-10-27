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

//������� ���������, ����� ���������� �����������
//��������� ����� ���������� ��� "��������" �����������
//"Who is on duty today?" (��������)

public class Main {

	public static void main(String[] args) {
		// ��������������� ������ ������ � ����
		textToFile("Who is on duty today?", "English.in");

		// ������ ������ �� �����
		List<String> words = readFile("English.in");

		HashMap<String, String> dictio = new HashMap<>();
		dictio.put("duty", "����������");
		dictio.put("who", "���");
		dictio.put("today", "�������");
		dictio.put("on", "��");
		dictio.put("is", "�");

		// ������ ����������
		wordAdd(dictio);

		// ���������� ������� � ����
		new DictioToFile(dictio);

		// �������
		translate(words, dictio, "Ukrainian.out");

	}

	// ��������������� ������ ������ � ����
	public static void textToFile(String text, String out) {
		try (BufferedWriter f = new BufferedWriter(new FileWriter(out))) {
			f.write(text);
			f.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ������ ������ �� �����
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

	// ������� ������
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

	// ������ ���������� �������
	public static void wordAdd(HashMap<String, String> dictio) {
		try {
			String key = String.valueOf(JOptionPane.showInputDialog("����� ���������� �����"));
			if (key.equals("null") || key.equals(""))
				throw new IllegalArgumentException();
			String value = String.valueOf(JOptionPane.showInputDialog("����� ���������� �����"));
			if (value.equals("null") || value.equals(""))
				throw new IllegalArgumentException();
			dictio.put(key, value);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "���� ������� �����������");
		}
	}

	
}
