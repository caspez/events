package com.casperszj.events.view.model;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.casperszj.events.R;
import com.casperszj.events.application.EventsApplication;
import com.casperszj.events.controller.AddEventDayListener;
import com.casperszj.events.model.SocialEvent;
import com.casperszj.events.model.facade.EventModel;

public class DayMonthAdapter extends ArrayAdapter<Calendar> {

	private Activity activity;
	private LayoutInflater inflater = (LayoutInflater) getContext()
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	public DayMonthAdapter(Activity activity, int textViewResourceId,
			List<Calendar> month) {
		super(activity, textViewResourceId, month);
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get the date of the day
		Calendar day = getItem(position);

		// If the sub view has not already been defined
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.month_view_day, parent,
					false);

			TextView dayOfMonth = (TextView) convertView
					.findViewById(R.id.dayOfMonth);
			if (day != null) {

				// set the day date
				dayOfMonth.setText(String.valueOf(day
						.get(Calendar.DAY_OF_MONTH)));

				// get any events for the day
				EventModel model = EventsApplication.getModel();
				List<SocialEvent> events = model.getDayEvents(day);

				// if day has events
				if (events != null && events.size() > 0) {
					// Get layout
					LinearLayout layout = (LinearLayout) convertView
							.findViewById(R.id.dayOfMonthLayout);

					for (SocialEvent event : events) {
						// Get the title of the event
						String eventTitle = event.getTitle();
						if (eventTitle != null) {
							TextView eventName = new TextView(this.activity);
							eventName.setSingleLine();
							// eventName.setEllipsize(TextUtils.TruncateAt.END);
							eventName
									.setEllipsize(TextUtils.TruncateAt.MARQUEE);
							eventName.setMarqueeRepeatLimit(-1);// -1 is inifity
							eventName.setHorizontallyScrolling(true);

							eventName.setText(eventTitle);
							eventName.setPadding(0, 0, 0, 0);
							eventName.setSelected(true);
							layout.addView(eventName);
						}
					}

				}
				convertView.setOnClickListener(new AddEventDayListener(
						this.activity, day));
			}
		}

		return convertView;

	}

}
