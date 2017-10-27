package ex03Letters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		// предварительная запись текста в файл
		try (BufferedWriter f = new BufferedWriter(new FileWriter("e.txt"))) {
			f.write("domestic cats is much more variable and ranges from");
//			f.write("gfedcba");
			f.newLine();
			f.write("widely dispersed individuals to feral cat colonies");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// чтение из файла
		String str = "";
		List<String> texts = new ArrayList<>();
		try (BufferedReader h = new BufferedReader(new FileReader("e.txt"))) {
			for (; (str = h.readLine()) != null;) {
				texts.add(str);
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<LetterFreq> a = textToLetterFreqs(texts);
		
		Collections.sort(a, new LetterFreqComparator());

		for (LetterFreq letter : a)
			System.out.println(letter.getLetter() + " " + letter.getFrequency());

	}

	public static List<LetterFreq> textToLetterFreqs(List<String> texts) {
		List<LetterFreq> a = new ArrayList<>();
		for (String text : texts) {
			text = text.replaceAll("\\s", "");
			text = text.toLowerCase();
			char[] letters = text.toCharArray();

			for (char letter : letters) {
				LetterFreq foo = new LetterFreq(letter);
				if (a.contains(foo)) {
					foo = a.get(a.indexOf(foo));
					foo.setFrequency(foo.getFrequency() + 1);
				} else {
					a.add(new LetterFreq(letter));
				}
			}
		}
		return a;
	}

}
