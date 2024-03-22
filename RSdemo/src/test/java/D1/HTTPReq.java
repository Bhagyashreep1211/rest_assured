package D1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPReq {
 int id;
 //String name;
	
 /*
 given()
 when sending an api request(content type, set cookies,add auth, param,set headers etc

 when()
 get, put,post delete
 then()
 valid status code,extract response,extract header cookies & response body





 */

 @Test(priority=1)
 void getUsers()
 {
 	given()
 	.when() 
 		.get("https://reqres.in/api/users?page=2")
 	.then()
 		.statusCode(200)
 		.body("page",equalTo(2))
 		.log().all();
 }

 @Test (priority=2)
 void createUser()
 {
 	HashMap data=new HashMap();
 	data.put("name","Ajay");
 	data.put("job", "SDE"); 
 	
 	
 	//name=given(),
 	id=given()
 		.contentType("application/json")
 		.body(data)
 		
 	.when()
 		.post("https://reqres.in/api/users")
 		.jsonPath().getInt("id");
 		//.jsonPath().getString("name");
 	
 	 //System.out.println("My id is: " + id);
 	 //System.out.println("My id is:" + id);
 	//.then()
 		//.statusCode(201)
 		.then()
 			.log().all();
 } 

 @Test (priority=3,dependsOnMethods= {"createUser"})
 void updateUser()
 {
 	HashMap data=new HashMap();
 	data.put("name","AJ");
 	data.put("job", "Flutter dev"); 
 	
 	

 	given()
 		.contentType("application/json")
 		.body(data)
 		
 	.when()
 		.put("https://reqres.in/api/users/" + id)
 		
 	.then()
 			.statusCode(200)
 			.log().all();
 	
 }

 @Test (priority=4)
 void deleteUser()
 {
 	given()
 	
 	.when()
 		.delete("https://reqres.in/api/users/" + id)
 	
 	.then()
 		.statusCode(204)
 		.log().all();
 	System.out.println("The user has deleted sucessfully:" + id);
 }

 }


 	





 
