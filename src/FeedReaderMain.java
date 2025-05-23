import feed.Feed;
import feed.Article;
import httpRequest.httpRequester;
import parser.RssParser;
import parser.SubscriptionParser;
import subscription.Subscription;
import subscription.SingleSubscription;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
//import namedEntity.heuristic.RandomHeuristic;


public class FeedReaderMain {

	private static void printHelp(){
    	System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}

	public static void main(String[] args) {
    	System.out.println("************* FeedReader version 1.0 *************");
    	try {
        	Subscription subscriptions = SubscriptionParser.parse("config/subscriptions.json");
        	httpRequester requester = new httpRequester();
        	RssParser parser = new RssParser();

        	for (SingleSubscription sub : subscriptions.getSubscriptionsList()) {
            	for (int i = 0; i < sub.getUrlParamsSize(); i++) {
                	String url = sub.getFeedToRequest(i);
                	String xml = requester.getFeedRss(url);
                	if (xml == null) continue;
                	Feed feed = parser.parse(xml, url);

                	if (args.length == 0) {
                    	feed.prettyPrint();
                	}
                	else if (args.length == 1 && args[0].equals("-ne")) {
                    	Heuristic heuristic = new QuickHeuristic();
                    	for (Article article : feed.getArticleList()) {
                        	article.computeNamedEntities(heuristic);
                    	}
                    	feed.printNamedEntitiesTable();
                	} else {
                    	printHelp();
                    	return;
                	}
            	}
        	}
    	} catch (Exception e) {
        	e.printStackTrace();
    	}
	}
}
