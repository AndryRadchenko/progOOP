package ex03Letters;

public class LetterFreq {
	private char letter;
	private int frequency = 1;
	
	public LetterFreq(char letter) {
		super();
		this.letter = letter;
	}

	public LetterFreq() {
		super();
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public boolean equals(Object obj) {
		if (((LetterFreq)obj).letter == this.letter)
				return true;
		return false;
		}
}
