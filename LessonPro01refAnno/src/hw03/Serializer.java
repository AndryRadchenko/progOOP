package hw03;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class Serializer <T>{

	private T t;
	private String path;
	
	public Serializer(T t, String path) {
		super();
		this.t = t;
		this.path = path;
		serialize();
	}

	public Serializer() {
		super();
	}

	public void serialize(){
		Class <?> cl = t.getClass();
		StringBuilder sb = new StringBuilder();
		try {
			Field[] fields = cl.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Save.class)) {
					if(Modifier.isPrivate(field.getModifiers()))
						field.setAccessible(true);
					sb.append(field.getName()).append(":").append(field.get(t)).append(";");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (FileWriter fw = new FileWriter(path)) {
			fw.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
