package com.casperszj.events.model;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public abstract class AbstractSocialEvent implements SocialEvent {

	// The title of the event (e.g. “Video game night”)
	private String title;

	// the date and time of an Event (e.g. April 12, 2013, 7.30PM-Late)
	private Calendar startDateTime;
	private Calendar endDateTime;

	// the location of the event (e.g. “Matt’s House, 13 Node.js”)
	private String venue;
	/*
	 * geographical latitude and longitude of the venue (e.g. -37.805631,
	 * 144.963053), you can get this from Google Maps.
	 */
	private float latitude;

	private float longitude;

	// Additional information about the event (e.g. “BYOD, costume and snacks!”)
	private String note;
	/*
	 * List of email addresses identifying individuals who are invited to this
	 * event (as picked from the user’s contacts list). Also name and mobile
	 * numbers for sending SMS's
	 */
	private List<Attendee> attendees;
	/*
	 * A randomly generated combination of numbers and letters, which uniquely
	 * identifies an event (not visible to the user)
	 */
	private String id;

	// Recorded voice note
	private String voiceNoteFile;

	public AbstractSocialEvent(String title, String venue, float latitude,
			float longitude, Calendar startDateTime, Calendar endDateTime,
			String note, List<Attendee> attendeeList, String voiceNoteFile) {
		this.title = title;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.venue = venue;
		this.latitude = latitude;
		this.longitude = longitude;
		this.note = note;
		this.attendees = attendeeList;
		this.id = UUID.randomUUID().toString();
		this.voiceNoteFile = voiceNoteFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#getStartDateTime()
	 */
	@Override
	public Calendar getStartDateTime() {
		return startDateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.SocialEvent#setStartDateTime(java.util.Calendar
	 * )
	 */
	@Override
	public void setStartDateTime(Calendar startDateTime) {
		this.startDateTime = startDateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#getEndDateTime()
	 */
	@Override
	public Calendar getEndDateTime() {
		return endDateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.SocialEvent#setEndDateTime(java.util.Calendar)
	 */
	@Override
	public void setEndDateTime(Calendar endDateTime) {
		this.endDateTime = endDateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#getVenue()
	 */
	@Override
	public String getVenue() {
		return venue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#setVenue(java.lang.String)
	 */
	@Override
	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Override
	public float getLatitude() {
		return latitude;
	}

	@Override
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Override
	public float getLongitude() {
		return longitude;
	}

	@Override
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String getNote() {
		return note;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#setNote(java.lang.String)
	 */
	@Override
	public void setNote(String note) {
		this.note = note;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#getAttendeeEmails()
	 */
	@Override
	public List<Attendee> getAttendees() {
		return attendees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.SocialEvent#addAttendeeEmail(java.lang.String)
	 */
	@Override
	public void addAttendee(Attendee attendee) {
		this.attendees.add(attendee);
	}

	@Override
	public void removeAttendee(Attendee attendee) {
		this.attendees.remove(attendee);

	}

	@Override
	public int getAttendeeCount() {
		if (attendees != null)
			return this.attendees.size();
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.SocialEvent#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	public String getVoiceNoteFile() {
		return voiceNoteFile;
	}

	public void setVoiceNoteFile(String voiceNoteFile) {
		this.voiceNoteFile = voiceNoteFile;
	}

}
