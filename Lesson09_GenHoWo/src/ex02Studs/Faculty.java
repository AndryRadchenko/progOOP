package ex02Studs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Faculty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private Group[] groups;

	public Faculty(String name, Group[] groups) {
		super();
		this.name = name;
		this.groups = groups;
	}

	public Faculty() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group[] getGroups() {
		return groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	public void saveGroups() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.name))) {
			oos.writeObject(this.groups);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadGroups(String name) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
			this.groups = (Group[]) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Faculty [name=" + name + ", groups=" + Arrays.toString(groups) + "]";
	}
}
