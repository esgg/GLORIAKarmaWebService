package eu.gloria.ws.rest.karma.factory;

import eu.gloria.ws.rest.karma.dao.PolicyDAO;
import eu.gloria.ws.rest.karma.dao.impl.PolicyDAOImpl;


public class PolicyFactory {
	public static PolicyDAO create(){
		return (new PolicyDAOImpl());
	}
}
