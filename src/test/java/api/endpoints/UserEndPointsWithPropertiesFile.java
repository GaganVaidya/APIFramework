package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPointsWithPropertiesFile {
	
	//take all URL from routes.properties files
	
	static ResourceBundle getURL() {
		
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	//here we maintain the create, update, delete & read type
	//we need to take helps of routes.propreties files and User class from api.payload
	
	public static Response createUser(User payload) {
		
		String post_url=getURL().getString("post_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		
		.when()
			.post(post_url);
		
		return response;
	}  
	
	public static Response getUser(String username) {
		
		String get_url=getURL().getString("get_url");
		
		Response response=given()
			.pathParam("username", username)
				
		.when()
			.get(get_url);
		
		return response;
	}

	public static Response updateUser(String username, User payload) {
		
		String update_url=getURL().getString("update_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
		
		.when()
			.put(update_url);
		
		return response;
	} 
	
	public static Response deleteUser(String username) {
		
		String delete_url=getURL().getString("delete_url");
		
		Response response=given()
			.pathParam("username", username)
				
		.when()
			.delete(delete_url);
		
		return response;
	}
}


















