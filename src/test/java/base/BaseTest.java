package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseTest {

    @BeforeClass
    public void setup() {
        ConfigReader.loadConfig();
        
        RestAssured.baseURI = ConfigReader.getProperty("baseURL");
    }
}
