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
package com.github.seratch.yahooapis.setsuden.request;

import com.github.seratch.yahooapis.setsuden.fields.Area;
import com.github.seratch.yahooapis.setsuden.fields.Output;

public class ElectricPowerForecastRequestParameters {

	public ElectricPowerForecastRequestParameters() {
	}

	public ElectricPowerForecastRequestParameters(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}

	public ElectricPowerForecastRequestParameters(Area area, String yyyymmdd) {
		this.area = area;
		this.yyyymmdd = yyyymmdd;
	}

	public ElectricPowerForecastRequestParameters(Output output, Area area, String yyyymmdd) {
		this.output = output;
		this.area = area;
		this.yyyymmdd = yyyymmdd;
	}

	public ElectricPowerForecastRequestParameters(
			Output output, String jsonpCallbackUrl, Area area, String yyyymmdd) {
		this.output = output;
		this.jsonpCallbackUrl = jsonpCallbackUrl;
		this.area = area;
		this.yyyymmdd = yyyymmdd;
	}

	public ElectricPowerForecastRequestParameters(Integer resultsMaxCount, Integer resultsStartIndex) {
		this.resultsMaxCount = resultsMaxCount;
		this.resultsStartIndex = resultsStartIndex;
	}

	public ElectricPowerForecastRequestParameters(Area area, Integer resultsMaxCount, Integer resultsStartIndex) {
		this.area = area;
		this.resultsMaxCount = resultsMaxCount;
		this.resultsStartIndex = resultsStartIndex;
	}

	public ElectricPowerForecastRequestParameters(Output output, Area area, Integer resultsMaxCount, Integer resultsStartIndex) {
		this.output = output;
		this.area = area;
		this.resultsMaxCount = resultsMaxCount;
		this.resultsStartIndex = resultsStartIndex;
	}

	public ElectricPowerForecastRequestParameters(
			Output output, String jsonpCallbackUrl, Area area, Integer resultsMaxCount, Integer resultsStartIndex) {
		this.output = output;
		this.jsonpCallbackUrl = jsonpCallbackUrl;
		this.area = area;
		this.resultsMaxCount = resultsMaxCount;
		this.resultsStartIndex = resultsStartIndex;
	}

	private Output output;

	private String jsonpCallbackUrl;

	private Area area = Area.tokyo;

	private String yyyymmdd;

	private Integer resultsMaxCount;

	private Integer resultsStartIndex;

	public Output getOutput() {
		return output == null ? Output.xml : output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public String getJsonpCallbackUrl() {
		return jsonpCallbackUrl;
	}

	public void setJsonpCallbackUrl(String jsonpCallbackUrl) {
		this.jsonpCallbackUrl = jsonpCallbackUrl;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getyyyymmdd() {
		return yyyymmdd;
	}

	public void setyyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}

	public Integer getResultsMaxCount() {
		return resultsMaxCount;
	}

	public void setResultsMaxCount(Integer resultsMaxCount) {
		this.resultsMaxCount = resultsMaxCount;
	}

	public Integer getResultsStartIndex() {
		return resultsStartIndex;
	}

	public void setResultsStartIndex(Integer resultsStartIndex) {
		this.resultsStartIndex = resultsStartIndex;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		if (output != null) {
			if (buf.length() > 0)
				buf.append("&");
			buf.append("output=");
			buf.append(output.toString());
		}
		if (jsonpCallbackUrl != null) {
			if (buf.length() > 0)
				buf.append("&");
			buf.append("callback=");
			buf.append(jsonpCallbackUrl);
		}
		if (area != null) {
			if (buf.length() > 0)
				buf.append("&");
			buf.append("area=");
			buf.append(area.toString());
		}
		if (yyyymmdd != null) {
			if (buf.length() > 0)
				buf.append("&");
			buf.append("date=");
			buf.append(yyyymmdd);
		} else {
			if (resultsMaxCount != null) {
				if (buf.length() > 0)
					buf.append("&");
				buf.append("results=");
				buf.append(resultsMaxCount.toString());
			} else {
				if (buf.length() > 0)
					buf.append("&");
				buf.append("results=10");
			}
			if (resultsStartIndex != null) {
				if (buf.length() > 0)
					buf.append("&");
				buf.append("start=");
				buf.append(resultsStartIndex.toString());
			} else {
				if (buf.length() > 0)
					buf.append("&");
				buf.append("start=1");
			}
		}
		return buf.toString();
	}


}
