package resources;

public class JiraPayLoad {
	public static String getSessionBody() {
		return "{ \"username\": \"usakshi5\", \"password\": \"R@jeev31\" }";
	}
	public static String getIssueBody() {
		return "{\r\n" + 
				"	\"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\"key\": \"TEST\"},\r\n" + 
				"       \"summary\": \"Credit card defect\",\r\n" + 
				"       \"description\": \"Creating second bug using the REST API\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}";
	}
	public static String getAddCommentBody() {
		return "{\r\n" + 
				"    \"body\": \"Adding comments from Rest API\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
	}
	public static String getEditCommentBody() {
		return "{\r\n" + 
				"    \"body\": \"updating comments from Rest API\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
	}

}
