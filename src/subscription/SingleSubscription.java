package subscription;

import java.util.ArrayList;
import java.util.List;


/*Esta clse abstrae el contenido de una sola suscripcion que ocurre en lista de suscripciones que figuran en el archivo de suscrpcion(json) */
public class SingleSubscription {
	
	private String url;
	private List<String> urlParams;
	private String urlType;
	
	
	public SingleSubscription(String url, List<String> urlParams, String urlType) {
		super();
		this.url = url;
		this.urlParams = new ArrayList<String>() ;
		this.urlType = urlType;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getUrlParams() {
		return urlParams;
	}
	public String getUrlParams(int i) {
		return this.urlParams.get(i);
	}
	public void setUrlParams(String urlParam) {
		this.urlParams.add(urlParam);
	}
	public int getUrlParamsSize() {
		return urlParams.size();
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	} 
	
	@Override
	public String toString() {
		return "{url=" + getUrl() + ", urlParams=" + getUrlParams().toString() + ", urlType=" + getUrlType() + "}";
	}
	
	public void prettyPrint(){
		System.out.println(this.toString());
	}
	
	
	public String getFeedToRequest(int i){
		return this.getUrl().replace("%s",this.getUrlParams(i));
	}
	
	public static void main(String[] args) {
		System.out.println("SingleSubscriptionClass");
		SingleSubscription s = new SingleSubscription("https://rss.nytimes.com/services/xml/rss/nyt/%s.xml", null, "rss");
		s.setUrlParams("Business");
		s.setUrlParams("Technology");
		System.out.println(s.getFeedToRequest(0));
		s.prettyPrint();
	}
	
	
	
	
}
