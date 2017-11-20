package hw03;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meta")
public class Meta {

	private String type;

	public Meta(String type) {
		super();
		this.type = type;
	}

	public Meta() {
		super();
	}

	public String getType() {
		return type;
	}

	@XmlElement(name="type")
	public void setType(String type) {
		this.type = type;
	}
	
	
}
