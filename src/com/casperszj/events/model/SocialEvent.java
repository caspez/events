package com.casperszj.events.model;

import java.util.Calendar;
import java.util.List;

public interface SocialEvent {

	public abstract String getTitle();

	public abstract void setTitle(String title);
	
	public abstract Calendar getStartDateTime();

	public abstract void setStartDateTime(Calendar startDateTime);

	public abstract Calendar getEndDateTime();

	public abstract void setEndDateTime(Calendar endDateTime);

	public abstract String getVenue();

	public abstract void setVenue(String venue);

	public abstract void setLongitude(float longitude);

	public abstract float getLongitude();

	public abstract void setLatitude(float latitude);

	public abstract float getLatitude();

	public abstract String getNote();

	public abstract void setNote(String note);

	public abstract List<Attendee> getAttendees();

	public abstract void addAttendee(Attendee attendee);
	
	public abstract void removeAttendee(Attendee attendee);
	
	public abstract int getAttendeeCount();

	public abstract String getId();
	
	public abstract String getVoiceNoteFile();	
	
	public abstract void setVoiceNoteFile(String voiceNoteFile);
	

}