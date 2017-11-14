package hw01;

import java.lang.reflect.*;

public class Tester{

	public static void testIt (Class<?>... arr) {
		try {
			for (Class<?> ar : arr) {
				Method[] methods = ar.getDeclaredMethods();
				for (Method method : methods){
					if(method.isAnnotationPresent(Test.class)){
						Test test = method.getAnnotation(Test.class);
						int mods = method.getModifiers();
						if(Modifier.isStatic(mods))
							method.invoke(null, test.a(), test.b());
						else
							method.invoke(ar.newInstance(), test.a(), test.b());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
