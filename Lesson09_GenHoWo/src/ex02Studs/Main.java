package ex02Studs;

import ex02Studs.Human.Gender;

public class Main {

	public static void main(String[] args) {
		Student A = new Student("John", "Smith", 20, Gender.male);
//		System.out.println(A.getInfo());
		Student B = new Student("Walter", "White", 17, Gender.male);
		Student C = new Student("Joe", "Average", 19, Gender.male);
		Student D = new Student("Joe", "D******", 25, Gender.male);
		Student E = new Student("Joe", "H******", 18, Gender.male);
		Student F = new Student("Joe", "K******", 33, Gender.male);
		Student G = new Student("Joe", "G******", 17, Gender.male);
		Student H = new Student("Joe", "E******", 19, Gender.male);
		Student I = new Student("Joe", "I******", 66, Gender.male);
		Student J = new Student("Joe", "J******", 18, Gender.male);
		Student K = new Student("Joe", "F******", 44, Gender.male);
		
		Group Group = new Group();
		
		Group.addStudent(A);
		Group.addStudent(B);
		Group.addStudent(C);
		Group.addStudent(D);
		Group.addStudent(E);
		Group.addStudent(F);
		Group.addStudent(G);
		Group.addStudent(H);
		Group.addStudent(I);
		Group.addStudent(J);
		System.out.println(Group);

//		ИНтерактивное добавление
//		Group.addStudent();
//		System.out.println(Group);
		
//		А эти лишние
		Group.addStudent(K);
	
		Group.groupSort();
		Group.printGroupElements();

		Group.delStudent(4);
		System.out.println(Group);

		
		System.out.println(Group);
		
		Group.toFile(".\\group.txt");
//		
		Group groupFromFile = new Group();
		groupFromFile.fromFile(".\\group.txt");
		System.out.println(groupFromFile);
	}
}