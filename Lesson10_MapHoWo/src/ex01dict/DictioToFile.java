package ex01dict;
//сохранение словаря в файл

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class DictioToFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String> dictio;

	public DictioToFile(HashMap<String, String> dictio) {
		super();
		this.dictio = dictio;
		saveDictio(this.dictio);
	}
	
	private void saveDictio(HashMap<String, String> dictio) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dictionary"))) {
			oos.writeObject(dictio);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
