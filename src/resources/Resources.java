package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Resources {
	public static String getPostJson() {
		String res_add="/maps/api/place/add/json";
		return res_add;
	}
	public static String getDelJson() {
		String res_del="/maps/api/place/delete/json";
		return res_del;
	}
	public static String getPostXML() {
		String res_add="/maps/api/place/add/xml";
		return res_add;
	}
	public static String getDelXML() {
		String res_del="/maps/api/place/delete/xml";
		return res_del;
	}
	public static XmlPath getXmlPath(Response r) {
		String response=r.asString();
		XmlPath x=new XmlPath(response);
		return x;
	}	
	public static JsonPath getJsonPath(Response r) {
		String response=r.asString();
		JsonPath jp=new JsonPath(response);
		return jp;
	}
}
