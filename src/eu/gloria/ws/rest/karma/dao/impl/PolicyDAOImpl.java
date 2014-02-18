package eu.gloria.ws.rest.karma.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import eu.gloria.ws.rest.karma.ConnectionFactory;
import eu.gloria.ws.rest.karma.dao.PolicyDAO;
import eu.gloria.ws.rest.karma.dto.Policy;

public class PolicyDAOImpl implements PolicyDAO {

	@Override
	public boolean update(Policy policy) throws SQLException {
		
		boolean result = false;
		
		try {
			Connection con = ConnectionFactory.getMySqlConnectionFactory("karma").createConnection();
			
			PreparedStatement st = con.prepareStatement("UPDATE karma_policies SET id_policy=?,id_actions=?,points_executor=?,points_owner=? WHERE id=?");
			st.setInt(1,policy.getPolicyId());
			st.setInt(2,policy.getActionsId());
			st.setInt(3, policy.getExecutorPoints());
			st.setInt(4, policy.getOwnerPoints());
			st.setInt(5, policy.getId());
			
			if (st.executeUpdate()>0){
				result = true;
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		return result;
	}

}
