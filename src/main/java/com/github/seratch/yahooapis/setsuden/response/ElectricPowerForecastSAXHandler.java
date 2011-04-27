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

import com.github.seratch.yahooapis.setsuden.fields.Area;
import org.joda.time.DateTime;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ElectricPowerForecastSAXHandler extends DefaultHandler {

	private String tagName = "";

	private DateTime currentTime;

	private DateTime updateTime;

	private List<ElectricPowerForecast> electricPowerForecasts = new ArrayList<ElectricPowerForecast>();

	ElectricPowerForecast getCurrentElement() {
		return electricPowerForecasts.get(electricPowerForecasts.size() - 1);
	}

	public List<ElectricPowerForecast> getElectricPowerForecasts() {
		return electricPowerForecasts;
	}

	public DateTime getCurrentTime() {
		return currentTime;
	}

	public DateTime getUpdateTime() {
		return updateTime;
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if (!"".equals(name)) {
			tagName = name;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if ("CurrentTime".equals(tagName)) {
			String value = new String(ch, start, length);
			currentTime = ElectricPowerForecast.getTimeFormat().parseDateTime(value);
		}
		if ("UpdateTime".equals(tagName)) {
			String value = new String(ch, start, length);
			updateTime = ElectricPowerForecast.getTimeFormat().parseDateTime(value);
		}
		if ("Forecast".equals(tagName)) {
			electricPowerForecasts.add(new ElectricPowerForecast());
		}
		if ("Area".equals(tagName)) {
			String value = new String(ch, start, length);
			getCurrentElement().setArea(Area.valueOf(value));
		}
		if ("Usage".equals(tagName)) {
			String value = new String(ch, start, length);
			getCurrentElement().setUsageKilowattPerHour(Long.valueOf(value));
		}
		if ("Capacity".equals(tagName)) {
			String value = new String(ch, start, length);
			getCurrentElement().setCapacityKilowattPerHour(Long.valueOf(value));
		}
		if ("Date".equals(tagName)) {
			String value = new String(ch, start, length);
			getCurrentElement().setDate(ElectricPowerForecast.getDateFormat().parseDateTime(value).toLocalDate());
		}
		if ("Hour".equals(tagName)) {
			String value = new String(ch, start, length);
			getCurrentElement().setHour(Integer.valueOf(value));
		}
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		tagName = "";
	}

}
