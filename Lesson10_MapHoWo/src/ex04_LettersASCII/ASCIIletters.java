package ex04_LettersASCII;

import java.util.HashMap;

public class ASCIIletters {
	public static HashMap<Character, String[]> letters() {
//		surprise!!!
		String ooooo = "     ";	

		HashMap<Character, String[]> hm = new HashMap<>();
		hm.put(' ', new String[] { ooooo, ooooo, ooooo, ooooo, ooooo, ooooo, ooooo });
		
		return hm;
	}

}
