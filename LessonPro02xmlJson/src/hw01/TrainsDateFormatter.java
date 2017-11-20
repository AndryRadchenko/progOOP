package hw01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TrainsDateFormatter extends XmlAdapter<String, Date> {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	@Override
	public Date unmarshal(String date) throws Exception {
		Date parsedDate = new Date();
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

	@Override
	public String marshal(Date date) throws Exception {
		return sdf.format(date);
	}
}
