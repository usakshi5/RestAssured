package restassured;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import resources.Resources;

public class DeleteXML {
	Properties prop;
	@BeforeTest
	public void getDate() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		prop.load(file);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	@Test
	public void post() throws IOException {
		String postdata = GenerateStringFromResource(System.getProperty("user.dir")+"\\resources\\xmlbody.xml");
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res=
		given().queryParam("key", prop.getProperty("KEY")).
		body(postdata).when().post(Resources.getPostXML()).then().assertThat().statusCode(200).contentType(ContentType.XML).and()
		.extract().response();
		XmlPath x=Resources.getXmlPath(res);
		String placeid=x.get("response.place_id");
		System.out.println(placeid);
		
	}
	
}
