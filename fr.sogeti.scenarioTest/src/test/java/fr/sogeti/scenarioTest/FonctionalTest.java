package fr.sogeti.scenarioTest;

import org.junit.BeforeClass;

import com.jayway.restassured.RestAssured;

public class FonctionalTest {

	@BeforeClass
	public static void setup() {

		String port = System.getProperty("server.port");

		if (port == null) {

			RestAssured.port = Integer.valueOf(9090);

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

			baseHost = "http://localhost";
		}

		RestAssured.baseURI = baseHost;
	}

}
