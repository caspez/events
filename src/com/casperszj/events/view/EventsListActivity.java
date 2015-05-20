package com.casperszj.events.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.casperszj.events.R;
import com.casperszj.events.application.EventsApplication;
import com.casperszj.events.model.SocialEvent;
import com.casperszj.events.model.facade.EventModel;
import com.casperszj.events.view.model.SocialEventAdapter;

public class EventsListActivity extends ListActivity {

	public static final int CHANGED_EVENTS = 10;
	private ArrayAdapter<SocialEvent> eventAdapter;
	private EventModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);

		// EventModel model = new EventModelFacade();
		this.model = EventsApplication.getModel();

		setTitle();

		eventAdapter = new SocialEventAdapter(this, 0,
				this.model.getSortedEventList());
		setListAdapter(eventAdapter);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == AddEventActivity.BLANK_TITLE) {
			Toast.makeText(getApplicationContext(), R.string.blankTitle,
					Toast.LENGTH_SHORT).show();
		} else if (resultCode == AddEventActivity.EDITED_EVENT) {
			Toast.makeText(getApplicationContext(), R.string.editedEvent,
					Toast.LENGTH_SHORT).show();
		} else if (resultCode == AddEventActivity.ADDED_EVENT) {
			Toast.makeText(getApplicationContext(), R.string.addedEvent,
					Toast.LENGTH_SHORT).show();
		} else if (resultCode == RESULT_CANCELED) {
			Toast.makeText(getApplicationContext(), R.string.didNotAddEvent,
					Toast.LENGTH_SHORT).show();
		} else if (resultCode == AddEventActivity.DELETED_EVENT)
			Toast.makeText(getApplicationContext(), R.string.deletedEvent,
					Toast.LENGTH_SHORT).show();

		if (resultCode == CHANGED_EVENTS
				|| resultCode == AddEventActivity.EDITED_EVENT
				|| resultCode == AddEventActivity.ADDED_EVENT
				|| resultCode == AddEventActivity.DELETED_EVENT) {
			setTitle();
			this.eventAdapter.clear();
			this.eventAdapter.addAll(EventsApplication.getModel()
					.getSortedEventList());
			this.eventAdapter.notifyDataSetChanged();

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_addEvent:// add new event
			startActivityForResult(new Intent(this, AddEventActivity.class),
					AddEventActivity.NEW_EVENT);
			return true;
		case R.id.action_monthView:
			startActivityForResult(new Intent(this, MonthViewActivity.class),
					MonthViewActivity.FROM_LIST_VIEW);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}

	}

	public void setTitle() {
		int eventCount = model.getEventCount();
		if (eventCount == 1)
			this.setTitle(String.valueOf(eventCount) + " event");
		else
			this.setTitle(String.valueOf(eventCount) + " events");

	}

}