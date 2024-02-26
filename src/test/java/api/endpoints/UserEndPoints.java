package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	//here we maintain the create, update, delete & read type
	//we need to take helps of Routers class and User class from api.payload
	
	public static Response createUser(User payload) {
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		
		.when()
			.post(Routes.post_URL);
		
		return response;
	}  
	
	public static Response getUser(String username) {
		
		Response response=given()
			.pathParam("username", username)
				
		.when()
			.get(Routes.get_URL);
		
		return response;
	}

	public static Response updateUser(String username, User payload) {
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
		
		.when()
			.put(Routes.put_URL);
		
		return response;
	} 
	
	public static Response deleteUser(String username) {
		
		Response response=given()
			.pathParam("username", username)
				
		.when()
			.delete(Routes.delete_URL);
		
		return response;
	}
}


















