package hw01;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DepartureTimeFormatter extends XmlAdapter<String, Integer> {

	@Override
	public Integer unmarshal(String departure) throws Exception {
		return Integer.parseInt(departure.replace(":", ""));
	}

	@Override
	public String marshal(Integer departure) throws Exception {
		String s = departure.toString();
		String ss = s.substring(0, 2) + ":" + s.substring(2, 4);
		return ss;
	}
}