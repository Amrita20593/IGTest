package Services;

import org.testng.Assert;
import org.testng.annotations.Test;
import ResourceUtils.BaseClass;
import ResourceUtils.Resoursepath;
import ResourceUtils.ReusableMethods;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AssertionTest extends BaseClass {

	@Test

	public static void getApiAssertion1Test() {
		Response res =given().log().all().
				when().
				get(Resoursepath.getApiTest()).then().log().all().assertThat().statusCode(200).extract().response();
		int statusCode = res.getStatusCode();
		System.out.println("StatusCode="+statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println("Assertion1 Result:Validating correct Response Status code as 200");

	}

	@Test

	public static void getApiAssertion2Test() {



		Response res =given().log().all().
				when().
				get(Resoursepath.getApiTest());
		Headers allHeaders = res.getHeaders();
		System.out.println("Response all headers value:");
		for(Header header : allHeaders)
		{

			System.out.println( header.getName() +"="+ header.getValue());
		}
		String contentType=res.header("Content-Type");
		Assert.assertEquals(contentType,"application/json; charset=UTF-8");
		String xCTC=res.header("X-Cloud-Trace-Context");
		Assert.assertNotNull(xCTC);
		String server=res.header("Server");
		Assert.assertEquals(server,"Google Frontend");
		String connection=res.header("Connection");
		Assert.assertEquals(connection,"keep-alive");
		String cl=res.header("Content-Length");
		Assert.assertNotNull(cl);
		System.out.println("Assertion2 result:Validated Response Header for JSON response");

	}
	@Test

	public static void getApiAssertion3Test() throws NullPointerException{

		Response res = given().log().all().
				when().
				get(Resoursepath.getApiTest()).then().log().all().assertThat().statusCode(200).extract().response();
		JsonPath js=ReusableMethods.rawToJson(res);
		String status=js.getString("status");
		Assert.assertEquals(status, "200");
		System.out.println("Assertion3 Result:returning correct code as 200");
		String age=js.getString("employeeData[0].age");
		Assert.assertEquals(age, "25");
		System.out.println("Assertion3 Result:returning correct age as 25");
		String role=js.getString("employeeData[0].role");
		Assert.assertEquals(role, "QA Automation Developer");
		System.out.println("Asertion3 Result:returning correct role as 'QA Automation Developer'");
		String dob=js.getString("employeeData[0].dob");
		Assert.assertEquals(dob, "25-02-1994");
		System.out.println("Asertion3 Result:returning correct dob as '25-02-1994'");
		String message=js.getString("message");
		Assert.assertEquals(message, "data retrieved successful");
		System.out.println("Asertion3 Result:returning correct message as 'data retrieved successful'");

	}
	@Test

	public static void getApiAssertion4Test() throws NullPointerException{

		Response res = given().log().all().
				when().
				get(Resoursepath.getApiTest()).then().log().all().assertThat().statusCode(200).extract().response();

		JsonPath js=ReusableMethods.rawToJson(res);
		String company=js.getString("employeeData[0].company");

		Assert.assertEquals(company, "ABC Infotech","Assertion4 Result:Fail as company name is not same as 'ABC Infotech'");

	}  
}





