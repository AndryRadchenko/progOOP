package hw01;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "trains")
public class Trains {

	
	private ArrayList<Train> trains = new ArrayList<>();
	
	public void setTrains(ArrayList<Train> trains) {
		this.trains = trains;
	}

	public void add (Train train){
		trains.add(train);
	}

	@XmlElement(name = "train")
	public ArrayList<Train> getTrains() {
		return this.trains;
	}

	@Override
	public String toString() {
		return "Trains [trains=" + trains + "]";
	}
}
