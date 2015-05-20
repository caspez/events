package com.casperszj.events.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class EventHashMap extends HashMap<String, SocialEvent> {
	// Sorted tree of event ids ordered by calendar
	private SortedMap<Calendar, SocialEvent> socialEventsSorted;

	public EventHashMap() {
		super();
		this.socialEventsSorted = new TreeMap<Calendar, SocialEvent>();
	}

	public SocialEvent put(String eventID, SocialEvent event) {
		// add to both sorted map and hash map
		this.socialEventsSorted.put(event.getStartDateTime(), event);
		return super.put(eventID, event);
	}

	@Override
	public SocialEvent remove(Object key) {
		// remove from both sorted map and hash map
		SocialEvent event;
		if ((event = super.remove(key)) != null)
			this.socialEventsSorted.remove(event.getStartDateTime());
		
		return event;
	}

	// Returns collection of sorted events
	public Collection<SocialEvent> values() {
		return socialEventsSorted.values();
	}

	public Collection<SocialEvent> getDayEvents(Calendar startDay,
			Calendar nextDay) {
		return socialEventsSorted.subMap(startDay, nextDay).values();
	}

	public int size() {
		if (super.size() != socialEventsSorted.size())
			return -1;
		return super.size();
	}

}
