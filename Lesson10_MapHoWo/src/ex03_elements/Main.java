package ex03_elements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;


public class Main {

	public static void main(String[] args) {

		// массив прямоугольников
		Rectangle[] arrayOne = new Rectangle[100000];
		Random rn = new Random();
		for (int i = 0; i < arrayOne.length; i++)
			arrayOne[i] = new Rectangle((rn.nextInt(3) + 3), (rn.nextInt(4) + 5));

		// массив строк
		String[] arrayTwo = new String[100000];
		Random rns = new Random();
		for (int i = 0; i < arrayOne.length; i++)
			arrayTwo[i] = "String " + rns.nextInt(10);

		tmapFill(arrayOne);
		tmapFill(arrayTwo);
		
	}

	// карта для пар объект: частота
	public static void tmapFill(Object[] array) {
		TreeMap<Object, Integer> tmap = new TreeMap<>(new Comparator<Object>(){
			@Override
			 public int compare(Object letFr0, Object letFr1) {
			 if (letFr0.hashCode() > letFr1.hashCode()) {
			 return -1;
			 } else if (letFr0.hashCode() < letFr1.hashCode()) {
			 return 1;
			 } else
			 return 0;
			 }
		});

		for (Object obj : array) {
			Integer tmapFreq = tmap.get(obj);
			if (tmapFreq != null)
				tmap.put(obj, tmapFreq + 1);
			else
				tmap.put(obj, 1);
		}

		Set<Object> set = tmap.keySet();
		for (Object obj : set)
			System.out.println(obj + " -> " + tmap.get(obj));

	}
}
