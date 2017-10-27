package ex02Studs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import ex02Studs.Human.Gender;
import ex02Studs.Student.StudentComparator;;

public class Group implements Voenkom, Serializable {
	private List<Student> students = new ArrayList<>();

	public Group() {
		super();
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void addStudent() {
		try {
			students.add(studentIn());
		} catch (IllegalArgumentException d) {
			JOptionPane.showMessageDialog(null, "===Invalid data entered");
		}
	}

	// Усовершенствуйте класс Group добавив возможность
	// интерактивного добавления объекта.
	private Student studentIn() throws IllegalArgumentException {
		String firstName = String.valueOf(JOptionPane.showInputDialog("Input First Name"));
		if (firstName.equals("null"))
			throw new IllegalArgumentException();
		String lastName = String.valueOf(JOptionPane.showInputDialog("Input Last Name"));
		if (lastName.equals("null"))
			throw new IllegalArgumentException();
		int age = Integer.valueOf(JOptionPane.showInputDialog("Input Age"));
		Gender gender = Gender
				.valueOf(String.valueOf(JOptionPane.showInputDialog("Input Gender: \"male\" or \"female\"")));
		if (gender.equals("null"))
			throw new IllegalArgumentException();

		Student student = new Student(firstName, lastName, age, gender);
		return student;
	}

	// Удаляет ID
	public void delStudent(int index) {
		try {
			students.remove(index);
			System.out.println("\nStudent ID " + index + " deleted\n");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("***WrongID\n");
		}
	}

	public void printGroupElements() {
		System.out.println("Element   LastName   FirsName   Age");
		for (int i = 0; i < students.size(); i++)
			System.out.print(i + "     " + students.get(i));
	}

	// Реализуйте возможность сортировки по параметру(Фамилия, успеваемость и
	// т.д..
	public StudentComparator sortParameterIn() throws IllegalArgumentException {
		String parameter = String
				.valueOf(JOptionPane.showInputDialog("Input sort parameter: FIRST_NAME or LAST_NAME or AGE"));
		if (parameter.equals("null"))
			throw new IllegalArgumentException();
		return StudentComparator.valueOf(parameter);
	}

	public void groupSort() {
		for (;;) {
			try {
				StudentComparator parameter = sortParameterIn();
				Collections.sort(students, parameter);
				System.out.println("\nSORTED BY " + String.valueOf(parameter));
				break;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "===Wrong parameter");
			}
		}
	}
	//

	// Усовершенствуйте класс описывающий группу студентов
	// добавив возможность сохранения группы в файл.
	public void toFile(String file) {
		try (BufferedWriter f = new BufferedWriter(new FileWriter(file))) {
			f.write("GROUP");
			f.newLine();
			f.write("ID\tLNAME\tFNAME\tAGE");
			f.newLine();
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i) != null) {
					f.write(i + "\t" + students.get(i).getLastName() + "\t" + students.get(i).getFirstName() + "\t"
							+ students.get(i).getAge());
					f.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Реализовать обратный процесс — т.е. считать данные о
	// группе из файла и на их основе создать объект типа группа.
	public void fromFile(String file) {
		String[] lines = new String[15];
		int i = 0;

		try (BufferedReader f = new BufferedReader(new FileReader(file))) {
			String str = "";
			for (; (str = f.readLine()) != null;) {
				lines[i++] = str;
			}
		} catch (IOException e) {
			System.out.println("ERROR");
		}

		for (int k = 2; k < 12; k++) {
			if (lines[k] == null)
				break;
			try {
				String[] lineSplit = lines[k].split("\t");
				Student student = new Student();
				student.setLastName(lineSplit[1]);
				student.setFirstName(lineSplit[2]);
				student.setAge(Integer.parseInt(lineSplit[3]));
				addStudent(student);
			} catch (ArrayIndexOutOfBoundsException e) {
			} catch (NumberFormatException e) {
			}
		}
	}

	@Override
	public String toString() {
		ArrayList<Student> studs = new ArrayList<>();
		for(Student student : students)
			studs.add(student);
		
		Collections.sort(studs);
		StringBuilder S = new StringBuilder();
		S.append("GROUP\nID\tLNAME\tFNAME\tAGE\n");
		
		int i = 0;
		for (Student stud : studs) {
			S.append(i + "\t" + stud.getLastName() + "\t" + stud.getFirstName() + "\t"
					+ stud.getAge() + "\n");
		}

		String string = S.toString();
		return string;
	}

	// Реализуйте интерфейс Военком которые вернет из группы
	// массив студентов юношей которым больше 18 лет.
	public ArrayList<Student> getStEighteen(Voenkom voenkom) {
		return voenkom.getEighteen();
	}

	@Override
	public ArrayList<Student> getEighteen() {
		ArrayList<Student> studVoenkom = new ArrayList<>();
		
		for (Student student : students)
			if (student.getAge() > 18)
				studVoenkom.add(student);
		return studVoenkom;
	}
}
