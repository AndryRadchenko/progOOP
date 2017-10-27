package ex03_elements;

public class Rectangle {
	int width;
	int height;
	String name; 
	
	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.name = width + " x " +  height;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (height != other.height)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	
	

}
