package com.casperszj.events.model.facade;

import java.util.Calendar;
import java.util.List;

import com.casperszj.events.model.SocialEvent;

public interface EventModel {

	// add event to model
	public abstract void addEvent(SocialEvent socialEvent);

	// delete event in model
	public abstract void deleteEvent(String eventID);

	// edit event in model
	public abstract void editEvent(String oldEventID, SocialEvent event);

	// get event by the event id
	public abstract SocialEvent getEventByID(String eventID);

	// Return list of events
	public abstract List<SocialEvent> getSortedEventList();

	// Return list of events for the given date (day)
	public abstract List<SocialEvent> getDayEvents(Calendar day);

	// return list of events, return -1 on error
	public abstract int getEventCount();

}