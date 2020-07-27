package ResourceUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static JsonPath rawToJson(Response res)
	{
		String responseString=res.asString();
		JsonPath x=new JsonPath(responseString);
		return x;
	}

}
