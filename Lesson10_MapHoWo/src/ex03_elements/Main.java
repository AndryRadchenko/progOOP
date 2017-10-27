package ex03_elements;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

//		������ ���������������
		Rectangle[] arrayOne = new Rectangle[100000];
		Random rn = new Random();
		for (int i = 0; i < arrayOne.length; i++) 
			arrayOne[i] = new Rectangle((rn.nextInt(3) + 3), (rn.nextInt(4) + 5));
		
//		������ �����
		String[] arrayTwo = new String[100000];
		Random rns = new Random();
		for (int i = 0; i < arrayOne.length; i++)
			arrayTwo[i] = "String " + rns.nextInt(10);
		
		aggregate(arrayOne);
		aggregate(arrayTwo);
	}

	// ����� ��� ��� ������: �������
	public static HashMap<Integer, Integer> hmapFill(Object[] array) {
		HashMap<Integer, Integer> hmap = new HashMap<>();
		for (Object obj : array) {
			Integer hshcode = obj.hashCode();
			Integer hmapElem = hmap.get(hshcode);
			if (hmapElem != null)
				hmap.put(hshcode, hmapElem + 1);
			else
				hmap.put(hshcode, 1);
		}
		return hmap;
	}

	// ����� ��� ��� ������: ������
	public static HashMap<Integer, Object> omapFill(Object[] array) {
		HashMap<Integer, Object> omap = new HashMap<>();
		for (Object obj : array) {
			Integer hshcode = obj.hashCode();
			Object omapElem = omap.get(hshcode);
			if (omapElem == null)
				omap.put(hshcode, obj);
		}
		return omap;
	}

//	����� "������ -> ����������"
	public static void comparisonPrint(HashMap<Integer, Integer> hmap, HashMap<Integer, Object> omap) {
		Set<Integer> set = hmap.keySet();
		for (Integer hshcode : set)
			System.out.println(omap.get(hshcode) + " -> " + hmap.get(hshcode));
	}
	
//	�������-���������
	public static void aggregate(Object[] array){
		HashMap<Integer, Integer> hmap = hmapFill(array);
		HashMap<Integer, Object> omap = omapFill(array);
		comparisonPrint(hmap, omap);
	}
}
