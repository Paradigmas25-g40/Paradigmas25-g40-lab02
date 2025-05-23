package parser;

import feed.Feed;

/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion 
 * */

public class RedditParser extends GeneralParser {
    @Override
    public Feed parse(String xml, String siteName) {
        throw new UnsupportedOperationException("RedditParser no implementado.");
    }
}
