package com.github.seratch.yahooapis.setsuden.response;

import com.github.seratch.yahooapis.setsuden.fields.Area;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ElectricPowerUsageSAXParser extends DefaultHandler {

	private String tagName = "";

	private ElectricPowerUsage electricPowerUsage = new ElectricPowerUsage();

	public ElectricPowerUsage getElectricPowerUsage() {
		return electricPowerUsage;
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (!"".equals(name)) {
			tagName = name;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if ("Area".equals(tagName)) {
			String value = new String(ch, start, length);
			electricPowerUsage.setArea(Area.valueOf(value));
		}
		if ("Usage".equals(tagName)) {
			String value = new String(ch, start, length);
			electricPowerUsage.setUsageKilowattPerHour(Long.valueOf(value));
		}
		if ("Capacity".equals(tagName)) {
			String value = new String(ch, start, length);
			electricPowerUsage.setCapacityKilowattPerHour(Long.valueOf(value));
		}
		if ("Date".equals(tagName)) {
			String value = new String(ch, start, length);
			electricPowerUsage.setDate(electricPowerUsage.getDateFormat()
					.parseDateTime(value).toLocalDate());
		}
		if ("Hour".equals(tagName)) {
			String value = new String(ch, start, length);
			electricPowerUsage.setHour(Integer.valueOf(value));
		}
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		tagName = "";
	}

}
