package parser;

import feed.Article;
import feed.Feed;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class RssParser extends GeneralParser {

    public Feed parse(String xml, String siteName) {
        Feed feed = new Feed(siteName);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String title = getTagValue("title", eElement);
                    String description = getTagValue("description", eElement);
                    //String pubDateStr = getTagValue("pubDate", eElement);
                    String link = getTagValue("link", eElement);

                    Article article = new Article(title, description, new Date(), link);
                    feed.addArticle(article);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feed;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nlList = element.getElementsByTagName(tag);
        if (nlList != null && nlList.getLength() > 0) {
            Node node = nlList.item(0);
            if (node != null && node.getFirstChild() != null) {
                return node.getFirstChild().getNodeValue();
            }
        }
        return "";
    }
}