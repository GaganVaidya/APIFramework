package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsWithPropertiesFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestWithPropertiesFile {
	
	Faker faker; //use for creating the fake data for testing
	User userpayload; //taking from api.payload class for test data set-up
	
	public Logger logger;
	
	@BeforeMethod
	public void setupData() {
	
		faker= new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setPassword(faker.internet().password(5, 10));	
		
		//for log
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void postUser() {
		
			logger.info("<----------- Create User ---------->");
			
			Response response=UserEndPointsWithPropertiesFile.createUser(userpayload); //here we calling user_end_points class for sending the test data 
			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
			logger.info("<---------- User Created ---------->");
	}
	
	@Test(priority=2)
	public void readUser() {
		
		logger.info("<----------- Get User Info ---------->");
		
		Response response=UserEndPointsWithPropertiesFile.getUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 404);
	}
	
	@Test(priority=3)
	public void updateUserData() {
		
		logger.info("<----------- Update User ---------->");
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		
		Response response=UserEndPointsWithPropertiesFile.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().status();
		
		logger.info("<----------- User Updated ---------->");
		
		Response responseAfterUpdate=UserEndPointsWithPropertiesFile.getUser(this.userpayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("<----------- Get Updated User Info ---------->");
	}

	@Test(priority=4)
	public void deleteUserData() {
		
		logger.info("<----------- Delete User ---------->");
		
		Response response=UserEndPointsWithPropertiesFile.deleteUser(this.userpayload.getUsername());
		response.then().log().status();
		
		logger.info("<----------- User Deleted ---------->");
	}
}
















