package namedEntity.heuristic;

import java.util.Map;

public abstract class Heuristic {

	private static Map<String, String> categoryMap = Map.of(
			"Microsft", "Company", 
			"Apple", "Company", 
			"Google", "Company",
			"Musk", "Person",
			"Biden", "Person",
			"Trump", "Person",
			"Messi", "Person",
			"Federer", "Person",
			"USA", "Country",
			"Russia", "Country"
	);
	private static Map<String, String> subcategoryMap = Map.of(
        "USA", "país",
        "Russia", "país",
        "Messi", "nombre",
        "Federer", "nombre"
    );

    private static Map<String, String> topicMap = Map.of(
        "Messi", "deportes",
        "Federer", "deportes",
        "Trump", "política",
        "Biden", "política"
    );
	
    public String getCategory(String entity){
        return categoryMap.getOrDefault(entity, "otro");
    }

    public String getSubcategory(String entity){
        return subcategoryMap.getOrDefault(entity, "otro");
    }

    public String getTopic(String entity){
        return topicMap.getOrDefault(entity, "otro");
    }
	
	public abstract boolean isEntity(String word);
		
}
