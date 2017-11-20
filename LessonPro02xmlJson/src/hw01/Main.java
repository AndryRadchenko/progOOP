package hw01;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {

	public static void main(String[] args) {

		File file = new File("src\\hw01\\trains.xml");
		
//		Вывести на экран инфо о поездах, которые отправляются
//		в заданный день в заданном промежутке времени
		filterTrains(file, "19.12.2013", "15:00", "19:00");
		System.out.println("\n");
		
//		написать код для добавления поездов в существующий xml
		Train intercity = new Train("742", "Киев", "Львов", "01.12.2017", "17:58");
		File fileNew = new File("src\\hw01\\trainsNew.xml");
		addTrain(file, fileNew, intercity);
	}
	
	
	public static void filterTrains(File file, String sdate, String depFrom, String depTo){
		Trains trains = new Trains();
		Date date = Train.dateParse(sdate);
		int departureFrom = Integer.parseInt(depFrom.replace(":", ""));
		int departureTo = Integer.parseInt(depTo.replace(":", ""));
		
		try{
			JAXBContext jax = JAXBContext.newInstance(Trains.class);
			Unmarshaller unmarshaller = jax.createUnmarshaller();
			trains = (Trains) unmarshaller.unmarshal(file);
		} catch (JAXBException e){
			e.printStackTrace();
		}
		
		ArrayList<Train> sTrains = trains.getTrains().stream()
				.filter(sTrain -> sTrain.getDate().equals(date))
				.filter(sTrain -> sTrain.getDeparture().compareTo(departureFrom)>=0)
				.filter(sTrain -> sTrain.getDeparture().compareTo(departureTo)<=0)
				.collect(Collectors.toCollection(ArrayList::new));
		sTrains.forEach(System.out::println);
	}
	
	public static void addTrain(File file, File fileNew, Train train){
		try{
			Trains trains = new Trains();
			JAXBContext jax = JAXBContext.newInstance(Trains.class);
			Unmarshaller unmarshaller = jax.createUnmarshaller();
			trains = (Trains) unmarshaller.unmarshal(file);
			trains.add(train);
			
			Marshaller marshaller = jax.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(trains, fileNew);
			marshaller.marshal(trains, System.out);
		} catch (JAXBException e){
			e.printStackTrace();
		}
	}

}
