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

public class ElectricPowerUsageRequestParameters {

	public ElectricPowerUsageRequestParameters() {
	}

	public ElectricPowerUsageRequestParameters(String yyyymmddhh) {
		this.yyyymmddhh = yyyymmddhh;
	}

	public ElectricPowerUsageRequestParameters(Area area, String yyyymmddhh) {
		this.area = area;
		this.yyyymmddhh = yyyymmddhh;
	}

	public ElectricPowerUsageRequestParameters(Output output, Area area, String yyyymmddhh) {
		this.output = output;
		this.area = area;
		this.yyyymmddhh = yyyymmddhh;
	}

	public ElectricPowerUsageRequestParameters(Output output, String jsonpCallbackUrl, Area area,
							 String yyyymmddhh) {
		this.output = output;
		this.jsonpCallbackUrl = jsonpCallbackUrl;
		this.area = area;
		this.yyyymmddhh = yyyymmddhh;
	}

	private Output output;

	private String jsonpCallbackUrl;

	private Area area = Area.tokyo;

	private String yyyymmddhh;

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

	public String getYyyymmddhh() {
		return yyyymmddhh;
	}

	public void setYyyymmddhh(String yyyymmddhh) {
		this.yyyymmddhh = yyyymmddhh;
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
		if (yyyymmddhh != null) {
			if (buf.length() > 0)
				buf.append("&");
			buf.append("datetime=");
			buf.append(yyyymmddhh);
		}
		return buf.toString();
	}


}
