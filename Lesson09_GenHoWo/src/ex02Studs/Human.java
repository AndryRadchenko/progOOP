package ex02Studs;

import java.io.Serializable;

public class Human implements Serializable {
		
	private String FirstName;
	private String LastName;
	private int age;
	protected enum Gender {
		male, female
		};
	private Gender gender;
	
	public Human(String firstName, String lastName, int age, Gender gender) {
		super();
		FirstName = firstName;
		LastName = lastName;
		this.age = age;
		this.gender = gender;
	}

	public Human() {
		super();
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getInfo() {
		return toString();
	}

	@Override
	public String toString() {
		return FirstName + " " + LastName + " aged " + age + " " + gender + "\n";
	}
}
