package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProvider;
import io.restassured.response.Response;

public class DataProviderTestUser {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProvider.class)
	public void getAllUser(String userID, String username, String fname, String lname, String email, String password, String phonenum) {
		
		User user=new User();
		
		user.setId(Integer.parseInt(userID));
		user.setUsername(username);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phonenum);
		
		Response res=UserEndPoints.createUser(user);
		res.then().log().all(); 
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProvider.class)
	public void deleteAllUserNames(String username) {
		
		Response resp=UserEndPoints.deleteUser(username);
		Assert.assertEquals(resp.getStatusCode(), 200);
		
	}
}
















