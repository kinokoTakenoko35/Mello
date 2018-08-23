package batch;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraping {

	static String url = "http://localhost:8080/Mello/jsp/";

	public static void main(String args[]) {
		Scraping jtest = new Scraping();
		try {
			Document doc = Jsoup.connect(url).get();
			String trend = jtest.getTrendByDom(doc);
			System.out.println(trend);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Use DOM methods to navigate a document
	//http://jsoup.org/cookbook/extracting-data/dom-navigation
	String getTrendByDom(Document doc) {
		String content = doc.getElementById("content").text();
		return content;
	}

}
