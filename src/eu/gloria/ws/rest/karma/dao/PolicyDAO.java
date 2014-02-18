package eu.gloria.ws.rest.karma.dao;

import java.sql.SQLException;

import eu.gloria.ws.rest.karma.dto.Policy;

public interface PolicyDAO {

	public boolean update(Policy policy) throws SQLException;
	
}
