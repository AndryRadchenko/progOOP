package hw03;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;

public class Deserializer<T> {

	public Deserializer() {
		super();
	}

	public static <T> T deserialize(T t, String path) {
		Class<?> cl = t.getClass();
		StringBuilder sb = new StringBuilder();

		try (FileReader fr = new FileReader(new File(path))) {
			int c = 0;
			while ((c = fr.read()) != -1)
				sb.append((char) c);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] methodsSet = sb.toString().split(";");
		for (String methodSet : methodsSet) {
			String[] keyValue = methodSet.split(":");

			try {
				Field field = cl.getDeclaredField(keyValue[0]);
				if (field.isAnnotationPresent(Save.class)) {
					if (Modifier.isPrivate(field.getModifiers()))
						field.setAccessible(true);
					if (field.getType() == int.class)
						field.set(t, Integer.parseInt(keyValue[1]));
					else if (field.getType() == double.class)
						field.set(t, Double.parseDouble(keyValue[1]));
					else if (field.getType() == String.class)
						field.set(t, keyValue[1]);
					else if (field.getType() == boolean.class)
						field.set(t, Boolean.parseBoolean(keyValue[1]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return t;
	}
}
