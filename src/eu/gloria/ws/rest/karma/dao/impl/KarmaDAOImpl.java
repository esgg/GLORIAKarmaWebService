package eu.gloria.ws.rest.karma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import eu.gloria.ws.rest.karma.ConnectionFactory;
import eu.gloria.ws.rest.karma.dao.KarmaDAO;

public class KarmaDAOImpl implements KarmaDAO{

	@Override
	public int get(String user) throws SQLException {
		int karma=-1;
		
		try {
			Connection con = ConnectionFactory.getMySqlConnectionFactory("karma").createConnection();
			
			PreparedStatement st = con.prepareStatement("SELECT * FROM KARMA WHERE user='"+user+"'");
			
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()){
				karma = rs.getInt("karma");
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return karma;
	}

}
