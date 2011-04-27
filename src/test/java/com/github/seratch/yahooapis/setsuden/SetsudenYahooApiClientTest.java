package com.github.seratch.yahooapis.setsuden;

import com.github.seratch.yahooapis.setsuden.fields.Area;
import com.github.seratch.yahooapis.setsuden.fields.Output;
import com.github.seratch.yahooapis.setsuden.request.ElectricPowerForecastRequestParameters;
import com.github.seratch.yahooapis.setsuden.request.ElectricPowerUsageRequestParameters;
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecast;
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecastResponse;
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
	public void getLatestPowerUsage_A$() throws Exception {
		SetsudenYahooApiClient client = new SetsudenYahooApiClient(applicationId);
		ElectricPowerUsageResponse response = client.getLatestPowerUsage();
		System.out.println(response.getElectricPowerUsage().toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerUsage());
	}

	@Test
	public void getLatestPowerUsage_A$ElectricPowerUsageRequestParameters_XML() throws Exception {
		SetsudenYahooApiClient client = new SetsudenYahooApiClient(applicationId);
		ElectricPowerUsageRequestParameters params = new ElectricPowerUsageRequestParameters(Output.xml, Area.tokyo, "2011032901");
		ElectricPowerUsageResponse response = client.getLatestPowerUsage(params);
		System.out.println(response.getElectricPowerUsage().toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerUsage());
	}

	@Test
	public void getLatestPowerUsage_A$ElectricPowerUsageRequestParameters_JSON() throws Exception {
		ElectricPowerUsageRequestParameters params = new ElectricPowerUsageRequestParameters(Output.json,
				Area.tokyo, "2011032901");
		ElectricPowerUsageResponse actual = client.getLatestPowerUsage(params);
		assertNotNull(actual);
		assertNotNull(actual.getElectricPowerUsage());
	}

	@Test
	public void getLatestPowerUsage_A$ElectricPowerUsageRequestParameters_PHP() throws Exception {
		ElectricPowerUsageRequestParameters params = new ElectricPowerUsageRequestParameters(Output.php,
				Area.tokyo, "2011032901");
		ElectricPowerUsageResponse actual = client.getLatestPowerUsage(params);
		assertNotNull(actual);
	}

	@Test
	public void getElectricPowerForecasts_A$() throws Exception {
		ElectricPowerForecastRequestParameters params = new ElectricPowerForecastRequestParameters();
		ElectricPowerForecastResponse response = client.getElectricPowerForecasts();
		System.out.println(response.getCurrentTime());
		System.out.println(response.getUpdateTime());
		System.out.println(response.getRawContent());
		for (ElectricPowerForecast forecast : response.getElectricPowerForcasts())
			System.out.println(forecast.toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerForcasts());
	}

	@Test
	public void getElectricPowerForecasts_A$ElectricPowerUsageRequestParameters_JSON() throws Exception {
		ElectricPowerForecastRequestParameters params = new ElectricPowerForecastRequestParameters();
		params.setOutput(Output.json);
		ElectricPowerForecastResponse response = client.getElectricPowerForecasts(params);
		System.out.println(response.getRawContent());
		for (ElectricPowerForecast forecast : response.getElectricPowerForcasts())
			System.out.println(forecast.toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerForcasts());
	}

	@Test
	public void getElectricPowerForecasts_A$ElectricPowerUsageRequestParameters_Date() throws Exception {
		ElectricPowerForecastRequestParameters params = new ElectricPowerForecastRequestParameters();
		params.setyyyymmdd("20110429");
		ElectricPowerForecastResponse response = client.getElectricPowerForecasts(params);
		System.out.println(response.getRawContent());
		for (ElectricPowerForecast forecast : response.getElectricPowerForcasts())
			System.out.println(forecast.toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerForcasts());
	}

	@Test
	public void getElectricPowerForecasts_A$ElectricPowerUsageRequestParameters_ResultsAndStart() throws Exception {
		ElectricPowerForecastRequestParameters params = new ElectricPowerForecastRequestParameters();
		params.setResultsMaxCount(3);
		params.setResultsStartIndex(100);
		ElectricPowerForecastResponse response = client.getElectricPowerForecasts(params);
		System.out.println(response.getRawContent());
		for (ElectricPowerForecast forecast : response.getElectricPowerForcasts())
			System.out.println(forecast.toString());
		assertNotNull(response);
		assertNotNull(response.getElectricPowerForcasts());
	}

}
