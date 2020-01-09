package restassured;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.Resources;

public class GetJson {
	
	@Test
	public void get() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res=
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","AIzaSyA5GiU3JDX2KBzVDHIPF5Fa2Hd5KUBXPR0").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
		JsonPath jp=Resources.getJsonPath(res);
		int count=jp.get("results.size()");
		System.out.println(count);
		for(int i=0;i<count;i++) {
			String s = jp.get("results["+i+"].name");
			s = s+": " +jp.get("results["+i+"].id");
			System.out.println(s);
		}
		//ArrayList<Json> js = new ArrayList<>();

	}

}
