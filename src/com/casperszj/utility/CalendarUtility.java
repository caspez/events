package com.casperszj.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public final class CalendarUtility {

	// Get formatted calendar string
	public static String getCalendarString(Calendar dateTime) {
		// example: 24 May 2014, 10:13 PM
		SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy, h:mm a",
				Locale.getDefault());
		return format.format(dateTime.getTime());
	}

	public static String getTimeString(Calendar time) {
		SimpleDateFormat format = new SimpleDateFormat("h:mm a",
				Locale.getDefault());
		return format.format(time.getTime());
	}

	public static String getDateString(Calendar date) {
		SimpleDateFormat format = new SimpleDateFormat("d MMMM yyyy",
				Locale.getDefault());
		return format.format(date.getTime());
	}

}
