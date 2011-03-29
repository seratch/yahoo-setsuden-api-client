package com.github.seratch.yahooapis.setsuden.response;

import com.github.seratch.yahooapis.setsuden.fields.Area;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class ElectricPowerUsage {

	private Area area = Area.tokyo;

	private Long usageKilowattPerHour;

	private Long capacityKilowattPerHour;

	private LocalDate date;

	public DateTimeFormatter getDateFormat() {
		DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
				.appendYear(4, 4).appendLiteral("-").appendMonthOfYear(2)
				.appendLiteral("-").appendDayOfMonth(2).toFormatter();
		return inputFormatter;
	}

	private Integer hour;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Long getUsageKilowattPerHour() {
		return usageKilowattPerHour;
	}

	public void setUsageKilowattPerHour(Long usageKilowattPerHour) {
		this.usageKilowattPerHour = usageKilowattPerHour;
	}

	public Long getCapacityKilowattPerHour() {
		return capacityKilowattPerHour;
	}

	public void setCapacityKilowattPerHour(Long capacityKilowattPerHour) {
		this.capacityKilowattPerHour = capacityKilowattPerHour;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("ElectricPowerUsage [area:");
		buf.append(area.toString());
		buf.append(",usageKilowattPerHour:");
		buf.append(usageKilowattPerHour);
		buf.append(",capacityKilowattPerHour:");
		buf.append(capacityKilowattPerHour);
		buf.append(",date:");
		buf.append(date);
		buf.append(",hour:");
		buf.append(hour);
		buf.append("]");
		return buf.toString();
	}

}
