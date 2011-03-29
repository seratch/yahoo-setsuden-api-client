package com.github.seratch.yahooapis.setsuden;

import com.github.seratch.yahooapis.setsuden.fields.Area;
import com.github.seratch.yahooapis.setsuden.fields.Output;
import com.github.seratch.yahooapis.setsuden.request.RequestParameters;
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerUsageResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class SetsudenYahooApiClientTest {

	static String applicationId;
	SetsudenYahooApiClient client;

	static {
		Properties props = new Properties();
		try {
			props.load(SetsudenYahooApiClientTest.class.getClassLoader()
					.getResourceAsStream("yahoo-developer.properties"));
			applicationId = props.getProperty("applicationId");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() {
		client = new SetsudenYahooApiClient(applicationId);
	}

	@Test
	public void type() throws Exception {
		assertNotNull(SetsudenYahooApiClient.class);
	}

	@Test
	public void instantiation() throws Exception {
		assertNotNull(client);
	}

	@Test
	public void getLatestPowerUsage_A$RequestParameters_XML() throws Exception {
		SetsudenYahooApiClient client = new SetsudenYahooApiClient(applicationId);
		RequestParameters params = new RequestParameters(Output.xml,
				Area.tokyo, "2011032901");
		ElectricPowerUsageResponse response = client.getLatestPowerUsage(params);
		System.out.println(response.getElectricPowerUsage().toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerUsage());
	}

	@Test
	public void getLatestPowerUsage_A$RequestParameters_JSON() throws Exception {
		RequestParameters params = new RequestParameters(Output.json,
				Area.tokyo, "2011032901");
		ElectricPowerUsageResponse actual = client.getLatestPowerUsage(params);
		assertNotNull(actual);
		assertNotNull(actual.getElectricPowerUsage());
	}

	@Test
	public void getLatestPowerUsage_A$RequestParameters_PHP() throws Exception {
		RequestParameters params = new RequestParameters(Output.php,
				Area.tokyo, "2011032901");
		ElectricPowerUsageResponse actual = client.getLatestPowerUsage(params);
		assertNotNull(actual);
	}

}
