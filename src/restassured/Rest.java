package restassured;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Rest {
	
	@Test
	public void get() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","AIzaSyA5GiU3JDX2KBzVDHIPF5Fa2Hd5KUBXPR0").
		log().all().
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON);

	}

}
