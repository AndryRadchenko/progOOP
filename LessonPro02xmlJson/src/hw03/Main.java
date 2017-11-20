package hw03;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Main {

	public static void main(String[] args) {
		
		String request = "https://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote";
        String result = pageParse(request);
        
        File file = new File("src\\hw03\\yahooQuery.xml");
        toFile(file, result);
                
		Lizt list= new Lizt();
		File fileRes = new File("src\\hw03\\yahooReParsed.xml");
		
		try{
			JAXBContext jax = JAXBContext.newInstance(Lizt.class);
			Unmarshaller unmarshaller = jax.createUnmarshaller();
			list = (Lizt) unmarshaller.unmarshal(file);
			
			Marshaller marshaller = jax.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(list, fileRes);
			marshaller.marshal(list, System.out);
		} catch (JAXBException e){
			e.printStackTrace();
		}
	}
	
	public static String pageParse(String urlIn) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlIn);
			URLConnection urlc = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			String text = "";
			for (; (text = br.readLine()) != null;) {
				sb.append(text).append(System.lineSeparator());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return sb.toString();
	}
	
	public static void toFile(File file, String result) {
		try (BufferedWriter f = new BufferedWriter(new FileWriter(file))) {
			f.write(result);
			f.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
