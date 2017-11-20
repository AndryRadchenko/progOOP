package hw03;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "list")
public class Lizt {

	private ArrayList<Resources> resources = new ArrayList<>();
	private Meta meta;
	private String version;

	
	public void setList(ArrayList<Resources> resources) {
		this.resources = resources;
	}

	@XmlElement(name = "resources")
	public ArrayList<Resources> getResources() {
		return this.resources;
	}

	public String getVersion() {
		return version;
	}

	@XmlAttribute(name = "version")
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Meta getMeta() {
		return meta;
	}

	@XmlElement(name = "meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
