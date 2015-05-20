package com.casperszj.events.model.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.casperszj.events.model.EventHashMap;
import com.casperszj.events.model.SocialEvent;

public class EventModelFacade implements EventModel {

	// Hash map of events
	private HashMap<String, SocialEvent> events;

	public EventModelFacade() {
		this.events = new EventHashMap();

		// for (int i = 1; i <= 10; i++) {
		// Calendar startDateTime = new GregorianCalendar(Calendar
		// .getInstance().get(Calendar.YEAR), Calendar.getInstance()
		// .get(Calendar.MONTH), i);
		// Calendar endDateTime = new GregorianCalendar(Calendar
		// .getInstance().get(Calendar.YEAR), Calendar.getInstance()
		// .get(Calendar.MONTH), i+1);
		// // endDateTime.set(Calendar.DAY_OF_MONTH, i+1);
		// SocialEvent event = new SimpleSocialEvent("Test Event " + i,// title
		// "Melbourne",// venue
		// 0, 0,// lat and lon location
		// startDateTime,// startDateTime
		// endDateTime,// endDateTime
		// "This is a test event",// note
		// new ArrayList<Attendee>(),// attendeeEmails
		// null);//voice note file
		// addEvent(event);
		// }
	}

	// add event to model
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.facade.EventModel#addEvent(com.casperszj.events
	 * .model.SocialEvent)
	 */
	@Override
	public void addEvent(SocialEvent socialEvent) {
		this.events.put(socialEvent.getId(), socialEvent);
	}

	// delete event in model
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.facade.EventModel#deleteEvent(java.lang.String
	 * )
	 */
	@Override
	public void deleteEvent(String eventID) {
		this.events.remove(eventID);
	}

	// edit event in model
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.facade.EventModel#editEvent(java.lang.String,
	 * com.casperszj.events.model.SocialEvent)
	 */
	@Override
	public void editEvent(String oldEventID, SocialEvent event) {
		// Remove old event
		this.deleteEvent(oldEventID);

		// Add new event
		this.addEvent(event);
	}

	// get event by the event id
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.casperszj.events.model.facade.EventModel#getEventByID(java.lang.String
	 * )
	 */
	@Override
	public SocialEvent getEventByID(String eventID) {
		if (eventID != null)
			return events.get(eventID);
		return null;

	}

	// Return list of events for the given date (day)
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.casperszj.events.model.facade.EventModel#getSortedEventList()
	 */
	@Override
	public List<SocialEvent> getSortedEventList() {
		return new ArrayList<SocialEvent>(events.values());
	}

	@Override
	public List<SocialEvent> getDayEvents(Calendar day) {
		// Create copy of day
		Calendar nextDay = (Calendar) day.clone();
		// add 1 day
		nextDay.add(Calendar.DAY_OF_MONTH, 1);

		// Cast to array list
		return new ArrayList<SocialEvent>(((EventHashMap) events).getDayEvents(
				day, nextDay));
	}

	@Override
	public int getEventCount() {
		return events.size();
	}
}
