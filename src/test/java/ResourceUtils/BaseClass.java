package ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import io.restassured.RestAssured;

public class BaseClass {

	public static Properties prop;
	public static FileInputStream fis;
	@BeforeTest
	public void getData() throws IOException {
		prop= new Properties();
		fis=new FileInputStream("C:\\AMRITA\\Automation software\\NWM\\IgtestAssignmentProject\\src\\test\\java\\ResourceUtils\\env.properties");
		prop.load(fis);
		RestAssured.baseURI=prop.getProperty("EndPoint");
		
	}
	
}
