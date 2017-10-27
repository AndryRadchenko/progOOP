package ex02Studs;

import java.io.Serializable;
import java.util.Comparator;

public class Student extends Human implements Comparable, Serializable {

	public Student(String firstName, String lastName, int age, Gender gender) {
		super(firstName, lastName, age, gender);
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Returns lastName + firstName
	public String getFullName() {
		return super.getLastName() + " " + super.getFirstName();
	}

	@Override
	public String getInfo() {
		return "STUDENT " + toString();
	}

	@Override
	public String toString() {
		return "Student " + super.toString();
	}

//	Реализуйте возможность сортировки списка студентов по фамилии
	@Override
	public int compareTo(Object arg0) {
		if (arg0 == (null))
			return -1;

		Student anotherStudent = (Student) arg0;
		return this.getLastName().compareToIgnoreCase(anotherStudent.getLastName());
	}

//	Реализуйте возможность сортировки по параметру(Фамилия, успеваемость и т. д.).
	public enum StudentComparator implements Comparator<Student> {
		FIRST_NAME {
			public int compare(Student studOne, Student studTwo) {
				return studOne.getFirstName().compareToIgnoreCase(studTwo.getFirstName());
			}
		},

		LAST_NAME {
			public int compare(Student studOne, Student studTwo) {
				return studOne.getLastName().compareToIgnoreCase(studTwo.getLastName());
			}
		},

		AGE {
			public int compare(Student studOne, Student studTwo) {
				return Integer.valueOf(studOne.getAge()).compareTo(studTwo.getAge());
			}
		},
	};

	public static Comparator<Student> getComparator(StudentComparator option) {
		return new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return option.compare(o1, o2);
			}
		};
	}
		
	
//	Реализуйте интерфейс Военком которые вернет из группы
//	массив студентов юношей которым больше 18 лет.
//	@Override
//	public ArrayList<Student> getEighteen(){
//		ArrayList<Student> students = new Student[1];
//		if (this.getAge() > 18)
//			students[0] = this;
//		return students;
//		}

}
