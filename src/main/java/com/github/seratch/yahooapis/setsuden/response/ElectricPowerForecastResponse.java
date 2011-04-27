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
package com.github.seratch.yahooapis.setsuden.response;

import com.github.seratch.yahooapis.setsuden.exception.ClientException;
import com.github.seratch.yahooapis.setsuden.fields.Area;
import com.github.seratch.yahooapis.setsuden.fields.Output;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectricPowerForecastResponse {

	private Output requestedOutput;

	private Integer statusCode;

	private Map<String, List<String>> headers = new HashMap<String, List<String>>();

	private String rawContent;

	public Output getRequestedOutput() {
		return requestedOutput;
	}

	public void setRequestedOutput(Output requestedOutput) {
		this.requestedOutput = requestedOutput;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public String getRawContent() {
		return rawContent;
	}

	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}

	public DateTime getCurrentTime() {
		if (requestedOutput == Output.xml) {
			String errorMessage = "Cannot parse xml data";
			try {
				ElectricPowerForecastSAXHandler handler = new ElectricPowerForecastSAXHandler();
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				parser.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler);
				return handler.getCurrentTime();
			} catch (ParserConfigurationException e) {
				throw new ClientException(errorMessage, e);
			} catch (SAXException e) {
				throw new ClientException(errorMessage, e);
			} catch (IOException e) {
				throw new ClientException(errorMessage, e);
			}
		} else if (requestedOutput == Output.json) {
			String dateTimeString = JSONObject.fromObject(rawContent).getJSONObject(
					"ElectricPowerForecasts").getString("CurrentTime");
			return new ElectricPowerForecast().getTimeFormat().parseDateTime(dateTimeString);
		} else if (requestedOutput == Output.php) {
			throw new UnsupportedOperationException("Currently unsupported");
		} else {
			throw new ClientException("Unexpected value:" + requestedOutput.toString(),
					new IllegalArgumentException());
		}
	}

	public DateTime getUpdateTime() {
		if (requestedOutput == Output.xml) {
			String errorMessage = "Cannot parse xml data";
			try {
				ElectricPowerForecastSAXHandler handler = new ElectricPowerForecastSAXHandler();
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				parser.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler);
				return handler.getUpdateTime();
			} catch (ParserConfigurationException e) {
				throw new ClientException(errorMessage, e);
			} catch (SAXException e) {
				throw new ClientException(errorMessage, e);
			} catch (IOException e) {
				throw new ClientException(errorMessage, e);
			}
		} else if (requestedOutput == Output.json) {
			String dateTimeString = JSONObject.fromObject(rawContent).getJSONObject(
					"ElectricPowerForecasts").getString("UpdateTime");
			return new ElectricPowerForecast().getTimeFormat().parseDateTime(dateTimeString);
		} else if (requestedOutput == Output.php) {
			throw new UnsupportedOperationException("Currently unsupported");
		} else {
			throw new ClientException("Unexpected value:" + requestedOutput.toString(),
					new IllegalArgumentException());
		}
	}

	public List<ElectricPowerForecast> getElectricPowerForcasts() {
		List<ElectricPowerForecast> forecasts = new ArrayList<ElectricPowerForecast>();
		if (requestedOutput == Output.xml) {
			String errorMessage = "Cannot parse xml data";
			try {
				ElectricPowerForecastSAXHandler handler = new ElectricPowerForecastSAXHandler();
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				parser.parse(new ByteArrayInputStream(rawContent.getBytes("UTF-8")), handler);
				forecasts = handler.getElectricPowerForecasts();
			} catch (ParserConfigurationException e) {
				throw new ClientException(errorMessage, e);
			} catch (SAXException e) {
				throw new ClientException(errorMessage, e);
			} catch (IOException e) {
				throw new ClientException(errorMessage, e);
			}
		} else if (requestedOutput == Output.json) {
			JSONArray jsonElements = JSONObject.fromObject(rawContent).getJSONObject(
					"ElectricPowerForecasts").getJSONArray("Forecast");
			for (int i = 0; i < jsonElements.size(); i++) {
				JSONObject json = jsonElements.getJSONObject(i);
				ElectricPowerForecast forecast = new ElectricPowerForecast();
				Area area = Area.valueOf(json.getString("Area"));
				forecast.setArea(area);
				long usage = json.getJSONObject("Usage").getLong("$");
				forecast.setUsageKilowattPerHour(usage);
				long capacity = json.getJSONObject("Capacity").getLong("$");
				forecast.setCapacityKilowattPerHour(capacity);
				LocalDate date = forecast.getDateFormat()
						.parseDateTime(json.getString("Date")).toLocalDate();
				forecast.setDate(date);
				int hour = json.getInt("Hour");
				forecast.setHour(hour);
				forecasts.add(forecast);
			}
		} else if (requestedOutput == Output.php) {
			throw new UnsupportedOperationException("Currently unsupported");
		} else {
			throw new ClientException("Unexpected value:" + requestedOutput.toString(),
					new IllegalArgumentException());
		}
		return forecasts;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("ElectricPowerForecastResponse [statusCode:");
		buf.append(statusCode);
		buf.append(",headers:");
		buf.append(headers.toString());
		buf.append(",rawContent:");
		buf.append(rawContent);
		buf.append("]");
		return buf.toString();
	}
}
