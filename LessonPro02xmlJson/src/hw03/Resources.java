package hw03;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "resources")
public class Resources {

	private ArrayList<Resource> resources = new ArrayList<>();
	private String count;
	private String start;
	
	
	public String getStart() {
		return start;
	}

	public String getCount() {
		return count;
	}

	@XmlAttribute(name = "start")
	public void setStart(String start) {
		this.start = start;
	}

	@XmlAttribute(name = "count")
	public void setCount(String count) {
		this.count = count;
	}

	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	public void add (Resource resource){
		resources.add(resource);
	}

	@XmlElement(name="resource")
	public ArrayList<Resource> getResources() {
		return this.resources;
	}

	@Override
	public String toString() {
		return "Resources [resources=" + resources + "]";
	}


}
