package com.github.seratch.yahooapis.setsuden;

import com.github.seratch.yahooapis.setsuden.request.RequestParameters;
import com.github.seratch.yahooapis.setsuden.request.Urls;
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerUsageResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetsudenYahooApiClient {

	private static final String USER_AGENT = "SetsudenYahooApiClient HTTP Fetcher (+https://github.com/seratch/yahoo-setsuden-api-client)";

	private String applicationId;

	public SetsudenYahooApiClient(String applicationId) {
		this.applicationId = applicationId;
	}

	public ElectricPowerUsageResponse getLatestPowerUsage(
			RequestParameters params) throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(Urls.LATEST_POWER_USAGE);
		url.append("?appid=");
		url.append(applicationId);
		url.append("&");
		url.append(params.toString());
		HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
				.openConnection();
		conn.setConnectTimeout(3000);
		conn.setReadTimeout(10000);
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestMethod("GET");
		conn.connect();
		ElectricPowerUsageResponse response = new ElectricPowerUsageResponse();
		response.setRequestedOutput(params.getOutput());
		response.setStatusCode(conn.getResponseCode());
		response.setHeaders(conn.getHeaderFields());
		response.setRawContent(getResponseCotent(conn, "UTF-8"));
		return response;
	}

	String getResponseCotent(HttpURLConnection conn, String charset)
			throws IOException {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = conn.getInputStream();
			Reader isr = (charset != null) ? new InputStreamReader(is, charset)
					: new InputStreamReader(is);
			br = new BufferedReader(isr);
			StringBuilder buf = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
			return buf.toString();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e2) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (Exception e2) {
				}
			}
		}
	}

}
