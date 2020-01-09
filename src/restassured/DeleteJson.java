package restassured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.PayLoad;
import resources.Resources;

public class DeleteJson {
	Properties prop;
	@BeforeTest
	public void getData() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		prop.load(file);
	}
	
	@Test
	public void post() {
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res=
		given().queryParam("key", prop.getProperty("KEY")).
		body(PayLoad.getJSonBody()).when().post(Resources.getPostJson()).
		then().assertThat().statusCode(200).contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).extract().response();
		JsonPath js=Resources.getJsonPath(res);
		String placeid=js.get("place_id");
		System.out.println(placeid);
		given().queryParams("key", "qaclick123").body("{\r\n" + 
				"\"place_id\" :"+ placeid+"\r\n" + 
				"}").when().post(Resources.getDelJson()).then().assertThat().statusCode(200).and().
		contentType(ContentType.JSON);
		System.out.println("deleted");
	}
	
}
