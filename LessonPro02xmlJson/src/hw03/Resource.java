package hw03;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "resource")
public class Resource {

	
	private ArrayList<Field> resource = new ArrayList<>();
	private String classname;

	public Resource(ArrayList<Field> resource) {
		super();
		this.resource = resource;
	}

	public Resource() {
		super();
	}

	@XmlElement(name="field")
	public ArrayList<Field> getTrain() {
		return resource;
	}

	public void setTrain(ArrayList<Field> resource) {
		this.resource = resource;
	}

	public String getClassname() {
		return classname;
	}

	@XmlAttribute(name = "classname")
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	
   

	

}
