package fr.sogeti.scenarioTest;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;

public class FonctionalTest {

	@BeforeClass
	public static void setup() {

        String port = System.getProperty("server.port");

        if (port == null) {

            RestAssured.port = 9090;

        } else {

            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {

            basePath = "/api/v1/";
        }

        RestAssured.basePath = basePath;

        String baseHost = System.getenv("server.host");

        if (baseHost == null) {

            baseHost = "http://10.226.160.85";
        }

        RestAssured.baseURI = baseHost;
    }

}
