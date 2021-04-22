package com.ina.message.VO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtils {

private static final String timeFormat = "HH:mm:ss";
	
	public static String getCurrentTimeStamp() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
		return LocalDateTime.now().format(formatter);
	}
}
