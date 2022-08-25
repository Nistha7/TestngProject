package testcases;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test_get {
	
	@Parameters({ "suite-param" })
	@BeforeSuite
	public void beforeSuite(@Optional("suite param") String param) {
		long id = Thread.currentThread().getId();
		System.out.println("Run Before Suite: param value is-"+param);
		System.out.println("Run Before Suite. Thread id is: " + id);
	}

	@Parameters({ "suite-param" })
	@BeforeTest                                             
	  public void beforeTest(@Optional("suite param") String param)  
	  {  
		long id = Thread.currentThread().getId();
		System.out.println("Run Before test: param value is-"+param);
		System.out.println("Run Before test. Thread id is: " + id);
	  }  
	
	
	@Parameters({ "suite-param", "test-param" })
	@BeforeClass
	void beforeClass(@Optional("suite param") String param, @Optional("tets param") String paramTwo) {
		long id = Thread.currentThread().getId();
		System.out.println("Run before class : param one value is- "+param);
		System.out.println("Run before class : param two value is- "+paramTwo);
		System.out.println("Run Before class. Thread id is: " + id);
	}
	
	@Parameters({ "suite-param", "test-param" })
	@BeforeMethod
	void beforeMethod(@Optional("suite param") String param, @Optional("tets param") String paramTwo) {
		long id = Thread.currentThread().getId();
		System.out.println("Run before method : param one value is- "+param);
		System.out.println("Run before method : param two value is- "+paramTwo);
		System.out.println("Run Before method. Thread id is: " + id);
	}
	
	
	@Test(priority=1, description="To get the list of all users",groups = { "test-group-get" })
	void test_getAll(@Optional("suite param") String param){
		
	given()
	
	.when()
		.get("https://reqres.in/api/users?page=2")
		
	.then()
		.statusCode(200);
	System.out.println("Test method belongs to get group.");
	
	}
	
	@Test(priority=2, description="To create a new user and checking the response",groups = { "test-group-post" })
	void test_post() {
		JSONObject data = new JSONObject(); 
		SoftAssert softassert = new SoftAssert();
		data.put("id","10");
		data.put("email","joy.smith@reqres.in");
		data.put("first_name","Joy");
		data.put("last_name","Smith");
		data.put("avatar","https://reqres.in/img/faces/1-image.jpg");
		
		Response res= given()
			.header("Content-Type","application/json")
			.body(data.toJSONString())
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().body()
			.extract().response();
		
		JsonPath js = new JsonPath(res.asString());
        System.out.println(js.toString());
        softassert.assertEquals(js.getString("data.first_name"), "Joy");
        softassert.assertEquals(js.getString("data.last_name"), "Smith");
        
        System.out.println("Test method belongs to post group.");
			
	}
	
	@Test(priority=3, description="To get a specific user and get the response",groups = { "test-group-get" })
	void test_givenUser() {
		SoftAssert softassert = new SoftAssert();
		Response response =given()
		
		.when()
			.get(" https://reqres.in/api/users/2")
			
			.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		 	JsonPath js = new JsonPath(response.asString());
	        System.out.println(js.toString());
	        softassert.assertEquals(js.getString("data.first_name"), "Janet");
	        Assert.assertEquals(js.getString("data.last_name"), "Weaver"); 
	        
	        System.out.println("Test method belongs to get group.");
	
	}
	
	@Parameters({ "suite-param", "test-param" })
	@AfterClass
	void afterClass(@Optional("suite param") String param, @Optional("tets param") String paramTwo) {
		long id = Thread.currentThread().getId();
		System.out.println("Run after class : param one value is- "+param);
		System.out.println("Run after class : param two value is- "+paramTwo);
		System.out.println("Run after class. Thread id is: " + id);
	}
	
	@Parameters({ "suite-param", "test-param" })
	@AfterMethod
	void afterMethod(@Optional("suite param") String param, @Optional("tets param") String paramTwo) {
		long id = Thread.currentThread().getId();
		System.out.println("Run after method : param one value is- "+param);
		System.out.println("Run after method : param two value is- "+paramTwo);
		System.out.println("Run after method. Thread id is: " + id);
	}
	
	@Parameters({ "suite-param" })
	@AfterTest                                             
	  public void afterTest(@Optional("suite param") String param)  
	  {  
		long id = Thread.currentThread().getId();
		System.out.println("Run after test : param one value is- "+param);
		System.out.println("Run after test. Thread id is: " + id);
	  }
	
	@Parameters({ "suite-param" })
	@AfterSuite
	public void afterSuite(@Optional("suite param") String param) {
		long id = Thread.currentThread().getId();
		System.out.println("Run after suite : param one value is- "+param);
		System.out.println("Run after suite. Thread id is: " + id);
	}
	
	
}
