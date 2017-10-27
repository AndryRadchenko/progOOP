package ex03Letters;

import java.util.Comparator;

public class LetterFreqComparator implements Comparator<LetterFreq> {

	@Override
	public int compare(LetterFreq letFr0, LetterFreq letFr1) {
		if (letFr0.getFrequency() > letFr1.getFrequency()) {
			return -1;
		} else if (letFr0.getFrequency() < letFr1.getFrequency()) {
			return 1;
		} else {
			if (letFr0.getLetter() < letFr1.getLetter()) {
				return -1;
			} else if (letFr0.getLetter() > letFr1.getLetter()) {
				return 1;
			}
		}
		return 0;
	}
	
	// public int compare(LetterFreq letFr0, LetterFreq letFr1) {
	// if (letFr0.getFrequency() > letFr1.getFrequency()) {
	// return -1;
	// } else if (letFr0.getFrequency() < letFr1.getFrequency()) {
	// return 1;
	// } else
	// return 0;
	// }
}
