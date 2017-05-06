package com.krishnan.balaji.data.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>{

	DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
	@Override
	public LocalDateTime unmarshal(String v) throws Exception {
		return (LocalDateTime) dtf.parse(v);
	}

	@Override
	public String marshal(LocalDateTime v) throws Exception {
		return dtf.format(v);
	}

}
