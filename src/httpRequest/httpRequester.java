package httpRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import subscription.SingleSubscription;
import subscription.Subscription;
import parser.SubscriptionParser;

/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class httpRequester {
	private final HttpClient client;
	private final SubscriptionParser parser;
	
	public httpRequester() {
		this.client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.build();
		this.parser = new SubscriptionParser();
	}
	
	public String getFeedRss(String urlFeed){
		String feedRssXml = getUrl(urlFeed);
		return feedRssXml;
	}

	public String getFeedReedit(String urlFeed) {
		String feedReeditJson = getUrl(urlFeed);
		return feedReeditJson;
	}
	 
	private String getUrl(String urlFeed) {
		try{
			HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(urlFeed))
			.GET()
			.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				return response.body();
			}
		} catch (IOException | InterruptedException e){
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public Subscription processSubscription(String filePath) {
		Subscription subscriptions = SubscriptionParser.parse(filePath);
		
		for (SingleSubscription sub : subscriptions.getSubscriptionsList()) {
			for (int i = 0; i < sub.getUrlParamsSize(); i++) {
				String url = sub.getFeedToRequest(i);
				
				String content;
				if (sub.getUrlType().equals("rss")) {
					content = getFeedRss(url);
				} else {
					content = getFeedReedit(url);
				}
				
				if (content != null) {
					System.out.println("Feed obtenido: " + url);
				} else {
					System.out.println("Error al obtener feed: " + url);
				}
			}
		}
		return subscriptions;
	}
}