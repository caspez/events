package com.casperszj.events.model;

import java.util.Calendar;
import java.util.List;

public class SimpleSocialEvent extends AbstractSocialEvent {

	public SimpleSocialEvent(String title, String venue, float latitude,
			float longitude, Calendar startDateTime, Calendar endDateTime,
			String note, List<Attendee> attendeeList, String voiceNoteFile) {
		super(title, venue, latitude, longitude, startDateTime, endDateTime, note,
				attendeeList, voiceNoteFile);
		// TODO Auto-generated constructor stub
	}



}
