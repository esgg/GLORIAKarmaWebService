package eu.gloria.ws.rest.karma.dao;

import java.sql.SQLException;

public interface KarmaDAO {
	public int get(String user) throws SQLException;
}
