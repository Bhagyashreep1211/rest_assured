package D2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.testng.annotations.Test;

public class PostReqtypesbody {
//1) post using HashMap	
//@Test(priority=1)

void hashmapPOST() {
	HashMap data =new HashMap(); 
    
    data.put("name", "John");
    data.put("location", "Dallas");
    data.put("phone", "1234567890");
    //data.put("id", "1");
    String courseArray[] = {"Math", "Physics"};
    
    data.put("courses", courseArray);
    
    given()
        .contentType("application/json")
        .body(data)
    .when()
        .post("http://localhost:3000/data")
    .then()
        //.statusCode(200)
        .body("name", equalTo("John"))
        .body("location", equalTo("Dallas"))
        .body("phone", equalTo("1234567890"))
        .body("courses[0]", equalTo("Math"))
        .body("courses[1]", equalTo("Physics"))
        .header("Content-Type","application/json")
        .log().all();
    //charset utf ?? not working
}
 
//2) post using org.json library
//@Test(priority=1)

void orgjsonPOST() 
{
	JSONObject data =new JSONObject();
	data.put("name","John");
	data.put("location", "Dallas");
	data.put("phone", "1234567890");
	
	String courseArray[] = {"Math", "Physics"};	
	data.put("courses", courseArray);
  
  given()
      .contentType("application/json")
      .body(data.toString())
  .when()
      .post("http://localhost:3000/data")
  .then()
      //.statusCode(200)
      .body("name", equalTo("John"))
      .body("location", equalTo("Dallas"))
      .body("phone", equalTo("1234567890"))
      .body("courses[0]", equalTo("Math"))
      .body("courses[1]", equalTo("Physics"))
      .header("Content-Type","application/json")
      .log().all();
  
}


//@Test(priority=2)
void testDelete()
{
	given()
	.when()
		.delete("http://localhost:3000/data/6a5f")
		
	.then()
		.statusCode(200);
}


//3) post using POJO class 
//@Test(priority=1)

void POJO() 
{
	POJO_postreq data =new POJO_postreq();
	
	data.setName("John");
	data.setLocation("Dallas");
	data.setPhone("1234567890");
	
	String coursesArray[] = {"Math", "Physics"};	
	data.setCourses(coursesArray);
  
  given()
      .contentType("application/json")
      .body(data)
  .when()
      .post("http://localhost:3000/data")
  .then()
      .statusCode(201)
      .body("name", equalTo("John"))
      .body("location", equalTo("Dallas"))
      .body("phone", equalTo("1234567890"))
      .body("courses[0]", equalTo("Math"))
      .body("courses[1]", equalTo("Physics"))
      .header("Content-Type","application/json")
      .log().all();
  
}

//4) post using external class 
@Test(priority=1)

void Externaljsonfile() throws FileNotFoundException 
{
	File f =new File(".//body.json");
	FileReader fr =new FileReader(f);
	JSONTokener jt =new JSONTokener(fr);
	JSONObject data =new JSONObject(jt);
	
	
	
given()
    .contentType("application/json")
    
    
    .body(data.toString())
.when()
    .post("http://localhost:3000/data")
.then()
    .statusCode(201)
    .body("name",equalTo("Mike"))
    .body("location",equalTo("India"))
    .body("phone",equalTo("5551234567"))
    .body("courses[0]",equalTo("Angular"))
    .body("courses[1]",equalTo("C++"))
    .header("Content-Type","application/json")
    .log().all();

}

@Test(priority=2)
void testDelete1()
{
	given()
	.when()
		.delete("http://localhost:3000/data/")
		
	.then()
		.statusCode(200);
}
}

