package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import namedEntity.NamedEntity;

/*Esta clase modela la lista de articulos de un determinado feed*/
public class Feed {
	String siteName;
	List<Article> articleList;
	
	public Feed(String siteName) {
		super();
		this.siteName = siteName;
		this.articleList = new ArrayList<Article>();
	}

	public String getSiteName(){
		return siteName;
	}
	
	public void setSiteName(String siteName){
		this.siteName = siteName;
	}
	
	public List<Article> getArticleList(){
		return articleList;
	}
	
	public void setArticleList(List<Article> articleList){
		this.articleList = articleList;
	}
	
	public void addArticle(Article a){
		this.getArticleList().add(a);
	}
	
	public Article getArticle(int i){
		return this.getArticleList().get(i);
	}
	
	public int getNumberOfArticles(){
		return this.getArticleList().size();
	}
	
	@Override
	public String toString(){
		return "Feed [siteName=" + getSiteName() + ", articleList=" + getArticleList() + "]";
	}
	

	public void prettyPrint(){
		for (Article a: this.getArticleList()){
			a.prettyPrint();
		}
		
	}

	public void printNamedEntitiesTable() {
		Map<String, Map<String, Map<String, Map<String, Integer>>>> table = new HashMap<>();
		for (Article article : this.getArticleList()) {
			for (NamedEntity ne : article.getNamedEntityList()) {
				String category = ne.getCategory() != null ? ne.getCategory() : "Otro";
				String subcategory = ne.getSubcategory() != null ? ne.getSubcategory() : "Otro";
				String topic = ne.getTopic() != null ? ne.getTopic() : "Otro";
				String name = ne.getName();

				table
					.computeIfAbsent(category, k -> new HashMap<>())
					.computeIfAbsent(subcategory, k -> new HashMap<>())
					.computeIfAbsent(topic, k -> new HashMap<>())
					.merge(name, ne.getFrequency(), Integer::sum);
			}
		}
		System.out.println("===============================================");
		System.out.println("Tabla de Entidades Nombradas (Clase/Subclase/Tema)");
		for (String category : table.keySet()) {
			System.out.println("Clase: " + category);
			for (String subcategory : table.get(category).keySet()) {
				System.out.println("  Subclase: " + subcategory);
				for (String topic : table.get(category).get(subcategory).keySet()) {
					System.out.println("    Tema: " + topic);
					System.out.printf("      %-25s %-10s\n", "Nombre", "Frecuencia");
					for (Map.Entry<String, Integer> entry : table.get(category).get(subcategory).get(topic).entrySet()) {
						System.out.printf("      %-25s %-10d\n", entry.getKey(), entry.getValue());
					}
				}
			}
		}
		System.out.println("===============================================");
	}
	
	public static void main(String[] args) {
		  Article a1 = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
			  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
			  new Date(),
			  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
			  );
		 
		  Article a2 = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
				  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
				  new Date(),
				  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
				  );
		  
		  Article a3 = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
				  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
				  new Date(),
				  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
				  );
		  
		  Feed f = new Feed("nytimes");
		  f.addArticle(a1);
		  f.addArticle(a2);
		  f.addArticle(a3);

		  f.prettyPrint();
		  
	}
	
}
