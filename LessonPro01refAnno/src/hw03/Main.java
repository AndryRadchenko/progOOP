package hw03;

public class Main {

	public static void main(String[] args) {

		new Serializer<Test>(new Test(1, 1), "c:\\serialized.txt");

		Test test = Deserializer.deserialize(new Test(10, 10.0), "c:\\serialized.txt");
		System.out.println(test);

	}

}
