/*
 * Copyright 2011 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.github.seratch.yahooapis.setsuden;

import com.github.seratch.yahooapis.setsuden.request.ElectricPowerForecastRequestParameters;
import com.github.seratch.yahooapis.setsuden.request.ElectricPowerUsageRequestParameters;
import com.github.seratch.yahooapis.setsuden.request.Urls;
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerForecastResponse;
import com.github.seratch.yahooapis.setsuden.response.ElectricPowerUsageResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SetsudenYahooApiClient {

	private static final String USER_AGENT = "SetsudenYahooApiClient HTTP Fetcher (+https://github.com/seratch/yahoo-setsuden-api-client)";

	private String applicationId;

	public SetsudenYahooApiClient(String applicationId) {
		this.applicationId = applicationId;
	}

	public ElectricPowerUsageResponse getLatestPowerUsage() throws IOException {
		return getLatestPowerUsage(new ElectricPowerUsageRequestParameters());
	}

	public ElectricPowerUsageResponse getLatestPowerUsage(
			ElectricPowerUsageRequestParameters params) throws IOException {
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

	public ElectricPowerForecastResponse getElectricPowerForecasts() throws IOException {
		return getElectricPowerForecasts(new ElectricPowerForecastRequestParameters());
	}

	public ElectricPowerForecastResponse getElectricPowerForecasts(ElectricPowerForecastRequestParameters params) throws IOException {
		StringBuilder url = new StringBuilder();
		url.append(Urls.POWER_FORECAST);
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
		ElectricPowerForecastResponse response = new ElectricPowerForecastResponse();
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
