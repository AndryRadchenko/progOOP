package hw01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "train")
@XmlType (propOrder={"from","to", "date", "departure"})
public class Train {
	
	private String id;
    private String from;
    private String to;
    private Date date;
    private Integer departure;

    public Train(String id, String from, String to, String date, String departure) {
    	this.id = id;
        this.from = from;
        this.to = to;
        this.date = dateParse(date);
        this.departure = Integer.parseInt(departure.replace(":", ""));
    }
    
	public Train() {
		super();
	}

	public String getId() {
		return id;
	}

	@XmlAttribute(name = "id")
	public void setId(String id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}
	
	@XmlElement(name = "from")
	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	@XmlElement(name = "to")
	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(TrainsDateFormatter.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDeparture() {
		return this.departure;
	}

	@XmlElement(name = "departure")
	@XmlJavaTypeAdapter(DepartureTimeFormatter.class)
	public void setDeparture(Integer departure) {
		this.departure = departure;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", from=" + from + ", to=" + to + ", date=" + date + ", departure=" + departure
				+ "]";
	}
	
	public static Date dateParse(String date){
		Date parsedDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
}
