package eu.gloria.ws.rest.karma;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class ConnectionFactory {

	public static ConnectionFactory karmaInstance;
	
	
	protected ConnectionFactory(){
		
	}
	public static ConnectionFactory getMySqlConnectionFactory(String db){
		if (db.equals("karma")){
			if (karmaInstance == null){
				karmaInstance = new ConnectionFactory();
			}
		}
		
		return karmaInstance;
	}
	public Connection createConnection() throws SQLException, NamingException{
//		Context ctx = new InitialContext();
//		Context env = (Context) ctx.lookup("jdbc:comp/env");
//		
//		DataSource ds = (DataSource) ctx.lookup("jdbc/KarmaDB");
//		Connection con = ds.getConnection();
//		return con;
		
		 PoolProperties p = new PoolProperties();
         p.setUrl("jdbc");
         p.setDriverClassName("com.mysql.jdbc.Driver");
         p.setUsername("user");
         p.setPassword("password");
         p.setValidationQuery("SELECT 1");
         p.setMaxActive(100);
         p.setMaxWait(10000);
         p.setMaxIdle(30);

         DataSource ds = new DataSource();
         ds.setPoolProperties(p);
         
         return ds.getConnection();
		
	}
	public Connection getConnection() throws SQLException, NamingException{
		return createConnection();
	}
}
