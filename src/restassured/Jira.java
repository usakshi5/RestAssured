package restassured;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.JiraPayLoad;
import resources.JiraResources;

public class Jira {
	Properties prop;
	@BeforeTest
	public void getData() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		prop.load(file);
	}
	@Test
	public void createSession() {
		System.out.println("Runnin createSession");
		RestAssured.baseURI=prop.getProperty("baseURI");
		Response res=given().header("Content-Type","application/json").
				body(JiraPayLoad.getSessionBody()).
				when().
				post(JiraResources.postUser()).
				then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
		JsonPath jp=JiraResources.getJsonPath(res);
		String value=jp.get("session.value");
		prop.setProperty("sessionid", value);
	}
	
	@Test(dependsOnMethods= {"createSession"})
	public void createIssue() {
		System.out.println("Runnin createIssue");
		RestAssured.baseURI=prop.getProperty("baseURI");
		String sessionid=prop.getProperty("sessionid");
		Response res=given().header("Cookie","JSESSIONID="+sessionid).header("Content-Type","application/json").
				body(JiraPayLoad.getIssueBody()).
				when().post(JiraResources.postIssue()).
				then().assertThat().statusCode(201).extract().response();
		JsonPath jp=JiraResources.getJsonPath(res);
		System.out.println("createIssueresponse "+res.asString());
		String issueid=jp.get("id");
		prop.setProperty("issueid", issueid);
	}
	
	@Test(dependsOnMethods= {"createIssue"})
	public void addComments() {
		System.out.println("Running addComments");
		RestAssured.baseURI=prop.getProperty("baseURI");
		String sessionid=prop.getProperty("sessionid");
		String issueid=prop.getProperty("issueid");
		Response res=given().header("Cookie","JSESSIONID="+sessionid).header("Content-Type","application/json").
				body(JiraPayLoad.getAddCommentBody()).when().post(JiraResources.
				postComment(issueid)).then().assertThat().statusCode(201).extract().response();
		System.out.println(res.asString());
		JsonPath jp=JiraResources.getJsonPath(res);
		String commentid=jp.get("id");
		System.out.println("commentid: "+commentid);
		prop.setProperty("commentid", commentid);
	}
	@Test(dependsOnMethods= {"addComments"})
	public void editComments() {
		System.out.println("Running editComments");
		RestAssured.baseURI=prop.getProperty("baseURI");
		String sessionid=prop.getProperty("sessionid");
		String issueid=prop.getProperty("issueid");
		String commentid=prop.getProperty("commentid");
		System.out.println("issueid: "+issueid+" commentid: "+commentid);
		given().header("Cookie","JSESSIONID="+sessionid).header("Content-Type","application/json").
				body(JiraPayLoad.getEditCommentBody()).when().put(JiraResources.
				editComment(issueid,commentid)).then().assertThat().statusCode(200);
	}
	@Test(dependsOnMethods= {"editComments"})
	public void deleteIssue() {
		System.out.println("Running deleteIssue");
		RestAssured.baseURI=prop.getProperty("baseURI");
		String sessionid=prop.getProperty("sessionid");
		String issueid=prop.getProperty("issueid");
		given().header("Cookie","JSESSIONID="+sessionid).header("Content-Type","application/json")
				.when().delete(JiraResources.deleteIssue(issueid))
				.then().assertThat().statusCode(204);
	}

}
