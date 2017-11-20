package hw03;

import javax.xml.bind.annotation.*;

public class Field {

	private String id;
	private String field;

	public Field(String id, String field) {
		super();
		this.id = id;
		this.field = field;
	}

	public Field() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getField() {
		return field;
	}

	@XmlAttribute(name = "name")
	public void setId(String id) {
		this.id = id;
	}

	@XmlValue
	public void setField(String field) {
		this.field = field;
	}

}
