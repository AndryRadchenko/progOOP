package ex04_LettersASCII;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		HashMap<Character, String[]> hm = ASCIIletters.letters();

		String s = " ";

		char[] lines = textToLines(s);
		linesPrint(lines, hm);

	}

	public static char[] textToLines(String s) {
		char[] chars = s.toUpperCase().toCharArray();
		return chars;
	}

	public static void linesPrint(char[] lines, HashMap<Character, String[]> hm) {
		StringBuilder[] sbArr = new StringBuilder[7];
		for (int i = 0; i < 7; i++)
			sbArr[i] = new StringBuilder();

		for (char letter : lines) {
			String[] l = hm.get(letter);
			for (int i = 0; i < 7; i++)
				sbArr[i].append(l[i] + "  ");
		}

		for (StringBuilder sb : sbArr)
			System.out.println(sb.toString());
	}
}
