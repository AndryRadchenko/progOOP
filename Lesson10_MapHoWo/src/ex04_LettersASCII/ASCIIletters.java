package ex04_LettersASCII;

import java.util.HashMap;

public class ASCIIletters {
	public static HashMap<Character, String[]> letters() {
		String ooooo = "     ";
		String XXXXX = "XXXXX";

		String Xoooo = "X    ";
		String XXXoo = "XXX  ";
		String XXXXo = "XXXX ";
		String ooooX = "    X";
		String oXXXX = " XXXX";

		String XXoXX = "XX XX";
		String XoooX = "X   X";
		String XooXX = "X  XX";
		String XoXXX = "X XXX";
		String XXooX = "XX  X";
		String XooXo = "X  X ";
		String XoXoo = "X X  ";

		String XoXoX = "X X X";
		String oXXXo = " XXX ";
		String oXoXo = " X X ";
		String ooXoo = "  X  ";
		String ooXXo = "  XX ";
		String oXooo = " X   ";
		String oooXo = "   X ";
		String oXXoX = " XX X";

		HashMap<Character, String[]> hm = new HashMap<>();
		hm.put(' ', new String[] { ooooo, ooooo, ooooo, ooooo, ooooo, ooooo, ooooo });
		hm.put('A', new String[] { oXXXo, XoooX, XoooX, XXXXX, XoooX, XoooX, XoooX });
		hm.put('B', new String[] { XXXXo, XoooX, XoooX, XXXXo, XoooX, XoooX, XXXXo });
		hm.put('C', new String[] { oXXXo, XoooX, Xoooo, Xoooo, Xoooo, XoooX, oXXXo });
		hm.put('D', new String[] { XXXoo, XooXo, XoooX, XoooX, XoooX, XooXo, XXXoo });

		hm.put('E', new String[] { XXXXX, Xoooo, Xoooo, XXXXo, Xoooo, Xoooo, XXXXX });
		hm.put('F', new String[] { XXXXX, Xoooo, Xoooo, XXXXo, Xoooo, Xoooo, Xoooo });
		hm.put('G', new String[] { oXXXo, XoooX, Xoooo, XoXXX, XoooX, XoooX, oXXXo });
		hm.put('H', new String[] { XoooX, XoooX, XoooX, XXXXX, XoooX, XoooX, XoooX });
		hm.put('I', new String[] { oXXXo, ooXoo, ooXoo, ooXoo, ooXoo, ooXoo, oXXXo });
		hm.put('J', new String[] { oXXXX, ooooX, ooooX, ooooX, ooooX, XoooX, oXXXo });

		hm.put('K', new String[] { XoooX, XooXo, XoXoo, XXXoo, XooXo, XoooX, XoooX });
		hm.put('L', new String[] { Xoooo, Xoooo, Xoooo, Xoooo, Xoooo, Xoooo, XXXXX });
		hm.put('M', new String[] { XoooX, XoooX, XXoXX, XoXoX, XoooX, XoooX, XoooX });
		hm.put('N', new String[] { XoooX, XoooX, XXooX, XoXoX, XooXX, XoooX, XoooX });
		hm.put('O', new String[] { oXXXo, XoooX, XoooX, XoooX, XoooX, XoooX, oXXXo });

		hm.put('P', new String[] { XXXXo, XoooX, XoooX, XXXXo, Xoooo, Xoooo, Xoooo });
		hm.put('Q', new String[] { oXXXo, XoooX, XoooX, XoooX, XoXoX, XooXo, oXXoX });
		hm.put('R', new String[] { XXXXo, XoooX, XoooX, XXXXo, XooXo, XoooX, XoooX });
		hm.put('S', new String[] { oXXXX, Xoooo, Xoooo, oXXXo, ooooX, ooooX, XXXXo });
		hm.put('T', new String[] { XXXXX, ooXoo, ooXoo, ooXoo, ooXoo, ooXoo, ooXoo });
		hm.put('U', new String[] { XoooX, XoooX, XoooX, XoooX, XoooX, XoooX, oXXXo });

		hm.put('V', new String[] { XoooX, XoooX, XoooX, XoooX, XoooX, oXoXo, ooXoo });
		hm.put('W', new String[] { XoooX, XoooX, XoooX, XoooX, XoXoX, XoXoX, oXoXo });
		hm.put('X', new String[] { XoooX, XoooX, oXoXo, ooXoo, oXoXo, XoooX, XoooX });
		hm.put('Y', new String[] { XoooX, XoooX, oXoXo, ooXoo, ooXoo, ooXoo, ooXoo });
		hm.put('Z', new String[] { XXXXX, ooooX, oooXo, ooXoo, oXooo, Xoooo, XXXXX });

		hm.put('.', new String[] { ooooo, ooooo, ooooo, ooooo, ooooo, ooXXo, ooXXo });
		hm.put(',', new String[] { ooooo, ooooo, ooooo, ooooo, ooooo, ooXXo, ooXoo });
		hm.put('?', new String[] { oXXXo, XoooX, XoooX, oooXo, ooXoo, ooooo, ooXoo });
		hm.put('!', new String[] { ooXoo, ooXoo, ooXoo, ooXoo, ooXoo, ooooo, ooXoo });

		return hm;
	}

}
