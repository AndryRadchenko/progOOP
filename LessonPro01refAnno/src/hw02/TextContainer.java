package hw02;

import java.io.FileWriter;
import java.io.IOException;

public class TextContainer {
	
	@SaveTo(path = "c:\\file.txt")
	private String text = "Hello world !";
	
	@SaveMethod
	public void save(String file){
		try(FileWriter fw = new FileWriter(file)){
			fw.write(text);	 
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
