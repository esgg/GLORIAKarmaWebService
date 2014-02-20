package eu.gloria.ws.rest.karma;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import eu.gloria.ws.rest.karma.dao.KarmaDAO;
import eu.gloria.ws.rest.karma.dao.PolicyDAO;
import eu.gloria.ws.rest.karma.dto.Policy;
import eu.gloria.ws.rest.karma.factory.KarmaFactory;
import eu.gloria.ws.rest.karma.factory.PolicyFactory;

@Path("/karma")
public class KarmaService {
	@GET
	@Path("/execute/ping")
	@Produces(MediaType.TEXT_PLAIN)
	public String echo(){
		return "HI!";
	}
	
	@GET
	@Path("/execute/get_karma/{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKarma(@PathParam("user") String user){
		
		JSONObject karma = new JSONObject();
		int karmaValue;
		
		System.out.println("Parameter:"+user);
		
		KarmaDAO karmaDAO = KarmaFactory.create();
		
		
		try {
			karmaValue = karmaDAO.get(user);
			karma.append("user", user);
			karma.append("karma", karmaValue);
			System.out.println("Value of karma:"+karmaValue);
			if (karmaValue != -1){
				return Response.status(200).type(MediaType.APPLICATION_JSON).entity(karma).build();
				
			} else {
				return Response.status(404).build();
			}
			
		} catch (SQLException e) {
			return Response.status(500).build();
		} catch (JSONException e) {
			return Response.status(500).build();
		}
		
		
	}
	
	@GET
	@Path("/execute/get_policies")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getPolicies(){
		
		JSONArray listPolicies = new JSONArray();
		System.out.println("get policies");
		
		return listPolicies;
	}
	
	@Path("execute/update_policy")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updatePolicy(Policy data){
		
		PolicyDAO dao = PolicyFactory.create();
		
		try {
			dao.update(data);
			return Response.status(200).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(409).build();
		}
	}
	
	@GET
	@Path("/execute/command")
	public Response sendCommand(){
		
		Client client = Client.create();
		
		client.addFilter(new HTTPBasicAuthFilter("egonzalez@ciclope.info", "nas.liferay.rudin"));
		
		WebResource webResource = client.
			resource("http://ws.users.gloria-project.eu:8080/GLORIAAPI/logs/errors/today");
		
		
		
//		WebResource webResource = client.
//				resource("http://venus.datsi.fi.upm.es:8080/GLORIAAPI/logs/errors/today");
		
//		webResource.header("Authorization", "Basic aG9sYThAbm9sb3NlLmNvbTpob2xhODg4");
//		webResource.header("Access-Control-Allow-Origin","*");
//		webResource.header("Access-Control-Allow-Credentials", "true");
//		webResource.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
//		webResource.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
		
		
		ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
		
		
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
	 
		String output = response.getEntity(String.class);
//		String output = webResource.accept("application/json").get(String.class);
	 
		System.out.println("Output from Server .... \n");
		System.out.println(output);
		
		return Response.status(200).build();
	}
}
