package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraResources {
	public static String postUser() {
		return "/rest/auth/1/session";
	}
	public static String postIssue() {
		return "/rest/api/2/issue";
	}
	public static String postComment(String id) {
		return "/rest/api/2/issue/"+id+"/comment";
	}
	public static String editComment(String issueid,String commentid) {
		return "/rest/api/2/issue/"+issueid+"/comment/"+commentid;
	}
	public static String deleteIssue(String issueid) {
		return "/rest/api/2/issue/"+issueid;
	}
	public static JsonPath getJsonPath(Response res) {
		JsonPath js=new JsonPath(res.asString());
		return js;
	}

}
