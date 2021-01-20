package kosmos1.assignment.qa.testcases;

import kosmos1.assignment.qa.base.TestBase;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class restAPIValidation extends TestBase {
	String resource;
	String param;
	String path;
	RequestSpecification requestspecification;
	
	@BeforeClass
	public void setUp(){
		baseURI = prop.getProperty("ApiURL");
		resource = prop.getProperty("Res");
		param = prop.getProperty("Param");
		path = prop.getProperty("Path");
		requestspecification = given();
	}
		
	@Test
	public void Testcase(){
		
		requestspecification.get(resource).then().statusCode(200).log().all();
	}
	
	@Test
	public void TestcaseEULA(){
		
		Response response = requestspecification.contentType(ContentType.JSON).request(Method.GET, path + param);
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		Assert.assertEquals(statusCode, 200,"Response code is not Ok");
		
		String eula_b64 = response.then().extract().path("eula_b64");
		System.out.println("eula_b64="+eula_b64);	
		
		
	}
}