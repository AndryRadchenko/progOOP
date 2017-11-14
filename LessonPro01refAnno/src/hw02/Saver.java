package hw02;

import java.lang.reflect.*;

public class Saver <T>{
	
	public Saver(T t) {
		super();
		saveIt(t);
	}

	public void saveIt(T t) {
		Class<?> cl = t.getClass();
		try {
			Field[] fields = cl.getDeclaredFields();
			String path = null;

			for (Field field : fields) {
				if (field.isAnnotationPresent(SaveTo.class)) {
					SaveTo saveTo = field.getAnnotation(SaveTo.class);
					path = saveTo.path();
					break;
				}
			}
			
			if (path != null) {
				Method[] methods = cl.getMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(SaveMethod.class)) {
						method.invoke(cl.newInstance(), path);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
