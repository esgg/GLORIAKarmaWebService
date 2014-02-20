package eu.gloria.ws.rest.karma.factory;

import eu.gloria.ws.rest.karma.dao.KarmaDAO;
import eu.gloria.ws.rest.karma.dao.impl.KarmaDAOImpl;

public class KarmaFactory {
	public static KarmaDAO create(){
		return (new KarmaDAOImpl());
	}
}
