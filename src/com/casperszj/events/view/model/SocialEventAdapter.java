package com.casperszj.events.view.model;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.casperszj.events.R;
import com.casperszj.events.controller.EditEventController;
import com.casperszj.events.model.SocialEvent;
import com.casperszj.utility.CalendarUtility;

public class SocialEventAdapter extends ArrayAdapter<SocialEvent> {

	private Activity activity;

	public SocialEventAdapter(Activity activity, int textViewResourceId,
			List<SocialEvent> events) {
		super(activity, textViewResourceId, events);

		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		SocialEvent event = getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.events_list_row, parent, false);
		}

		// Get text views
		TextView eventTitleText = (TextView) convertView
				.findViewById(R.id.eventTitleText);
		TextView eventStartingTimeText = (TextView) convertView
				.findViewById(R.id.startingTimeText);
		TextView numberOfAttendees = (TextView) convertView
				.findViewById(R.id.numberOfAttendees);

		// set text views
		eventTitleText.setText(event.getTitle());
		eventStartingTimeText.setText(CalendarUtility.getCalendarString(event
				.getStartDateTime()));

		int attendeeCount = event.getAttendeeCount();
		if (attendeeCount == 1)
			numberOfAttendees.setText(String.valueOf(attendeeCount)
					+ " attendee");
		else
			numberOfAttendees.setText(String.valueOf(attendeeCount)
					+ " attendees");

		convertView.setOnClickListener(new EditEventController(event.getId(),
				activity));

		return convertView;

	}
}
