package com.casperszj.events.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.casperszj.events.R;
import com.casperszj.events.application.EventsApplication;
import com.casperszj.events.controller.AddContactListener;
import com.casperszj.events.controller.EditDateListener;
import com.casperszj.events.controller.EditTimeListener;
import com.casperszj.events.controller.GetLatitudeLongitudeListener;
import com.casperszj.events.model.Attendee;
import com.casperszj.events.model.SimpleSocialEvent;
import com.casperszj.events.model.SocialEvent;
import com.casperszj.events.model.facade.EventModel;
import com.casperszj.utility.ContactToAttendee;
import com.casperszj.utility.CalendarUtility;
import com.casperszj.utility.VoiceRecorder;

public class AddEventActivity extends Activity {
	public static final int NEW_EVENT = 1;
	public static final int EDIT_EVENT = 2;
	public static final int ADD_INVITEES = 3;
	public static final int BLANK_TITLE = 4;
	public static final int EDITED_EVENT = 5;
	public static final int ADDED_EVENT = 6;
	public static final int DELETED_EVENT = 7;
	public static final int ADD_DAY_EVENT = 8;

	public static final String EVENT_ID_AS_EXTRA = "eventIdExtra";
	public static final String DAY_AS_EXTRA = "dayDateExtra";

	private EventModel model;
	private String oldEventId;
	private Button startingDateButton;
	private Button endingDateButton;
	private Button startingTimeButton;
	private Button endingTimeButton;
	private Button getLatitudeLongitude;
	private Calendar startTimeCalendar;
	private Calendar endTimeCalendar;
	private EditText titleText;
	private EditText venueText;
	private EditText latitudeText;
	private EditText longitudeText;
	private EditText notesText;
	private Button addAttendee;
	private List<Attendee> attendeeList;
	private Boolean editEvent;
	private String voiceNoteFile;
	private Button voicePlay;
	private Button voiceRecord;
	private Boolean play;
	private Boolean record;
	private VoiceRecorder voiceRecorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_event);

		this.model = EventsApplication.getModel();

		// set view fields
		setFields();

		// attempt to get old event
		SocialEvent oldEvent = model.getEventByID(getIntent().getStringExtra(
				EVENT_ID_AS_EXTRA));

		if (oldEvent != null) {
			editEvent = true;
			this.oldEventId = oldEvent.getId();
			prefillEditEventData(oldEvent);
			this.setTitle(R.string.editEvent);
		} else {// check to see if just date was provided
			// will result to -1 if date was not provided
			prefillAddEvent(getIntent().getLongExtra(DAY_AS_EXTRA, -1));
			editEvent = false;
		}

		// setup voice controller
		voiceRecorder = new VoiceRecorder(voiceNoteFile);

		setDateTimes();
		updateAttendees();
		addListeners();

	}

	private void setFields() {
		this.startingDateButton = (Button) findViewById(R.id.startDate);
		this.endingDateButton = (Button) findViewById(R.id.endDate);
		this.startingTimeButton = (Button) findViewById(R.id.startTime);
		this.endingTimeButton = (Button) findViewById(R.id.endTime);
		this.titleText = (EditText) findViewById(R.id.addTitle);
		this.venueText = (EditText) findViewById(R.id.addVenue);
		this.getLatitudeLongitude = (Button) findViewById(R.id.get_latitude_longitude);
		this.latitudeText = (EditText) findViewById(R.id.addLatitude);
		this.longitudeText = (EditText) findViewById(R.id.addLongitude);
		this.notesText = (EditText) findViewById(R.id.addNote);
		this.addAttendee = (Button) findViewById(R.id.addAttendee);
		this.voicePlay = (Button) findViewById(R.id.playNote);
		this.voiceRecord = (Button) findViewById(R.id.recordNote);
		this.play = false;
		this.record = false;
	}

	private void prefillAddEvent(long dateInMilliseconds) {
		this.startTimeCalendar = Calendar.getInstance();
		this.endTimeCalendar = Calendar.getInstance();
		if (dateInMilliseconds != -1) {
			this.startTimeCalendar.setTimeInMillis(dateInMilliseconds);
			this.endTimeCalendar.setTimeInMillis(dateInMilliseconds);
		}
		// create voice note file name based on date
		voiceNoteFile = VoiceRecorder.createFileName(Long
				.toString(dateInMilliseconds));

		// create attendee list
		this.attendeeList = new ArrayList<Attendee>();

	}

	private void prefillEditEventData(SocialEvent oldEvent) {
		// Copy calendar and attendee array for use in the view
		startTimeCalendar = (Calendar) oldEvent.getStartDateTime().clone();
		endTimeCalendar = (Calendar) oldEvent.getEndDateTime().clone();
		this.attendeeList = new ArrayList<Attendee>(oldEvent.getAttendees());

		titleText.setText(oldEvent.getTitle());
		venueText.setText(oldEvent.getVenue());
		latitudeText.setText(Float.toString(oldEvent.getLatitude()));
		longitudeText.setText(Float.toString(oldEvent.getLongitude()));
		notesText.setText(oldEvent.getNote());
		voiceNoteFile = oldEvent.getVoiceNoteFile();
	}

	private void setDateTimes() {
		this.startingTimeButton.setText(CalendarUtility
				.getTimeString(startTimeCalendar));
		this.startingDateButton.setText(CalendarUtility
				.getDateString(startTimeCalendar));
		this.endingTimeButton.setText(CalendarUtility
				.getTimeString(endTimeCalendar));
		this.endingDateButton.setText(CalendarUtility
				.getDateString(endTimeCalendar));
	}

	private void addListeners() {
		this.startingDateButton.setOnClickListener(new EditDateListener(
				getFragmentManager(), this.startingDateButton,
				startTimeCalendar));

		this.endingDateButton.setOnClickListener(new EditDateListener(
				getFragmentManager(), this.endingDateButton, endTimeCalendar));

		this.startingTimeButton.setOnClickListener(new EditTimeListener(
				getFragmentManager(), this.startingTimeButton,
				startTimeCalendar));

		this.endingTimeButton.setOnClickListener(new EditTimeListener(
				getFragmentManager(), this.endingTimeButton, endTimeCalendar));

		this.getLatitudeLongitude
				.setOnClickListener(new GetLatitudeLongitudeListener(this));

		this.addAttendee.setOnClickListener(new AddContactListener(this));

		this.voicePlay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!play) {
					if (voiceRecorder.startPlaying()) {
						play = true;
						voicePlay.setText(R.string.stopVoiceNote);
					}

				} else {
					voiceRecorder.stopPlaying();
					voicePlay.setText(R.string.playVoiceNote);
					play = false;
				}

			}
		});

		this.voiceRecord.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!record) {
					if (voiceRecorder.startRecording()) {
						record = true;
						voiceRecord.setText(R.string.stopVoiceNote);
					}
				} else {
					voiceRecorder.stopRecording();
					voiceRecord.setText(R.string.recordVoiceNote);
					record = false;
				}

			}
		});

	}

	// add lsit of attendees to view
	private void updateAttendees() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.attendeeLayout);
		// Check if layout already has elements
		if (((LinearLayout) layout).getChildCount() > 0) // if so remove
			((LinearLayout) layout).removeAllViews();
		if (this.attendeeList != null && this.attendeeList.size() > 0) {
			// for each attendee
			for (Attendee attendee : attendeeList) {
				// create textviews and write to view
				TextView attendeeName = new TextView(this);
				// attendeeName.setLayoutParams(new LayoutParams());
				attendeeName.setText(attendee.getName());
				layout.addView(attendeeName);
				TextView attendeeEmail = new TextView(this);
				attendeeEmail.setText(attendee.getEmailAddress());
				layout.addView(attendeeEmail);
				Button removeAttendee = new Button(this);
				removeAttendee.setText(R.string.removeAttendee);
				removeAttendee.setTag(attendee);
				removeAttendee.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// remove attendee from the list
						attendeeList.remove(v.getTag());
						updateAttendees();
					}
				});
				layout.addView(removeAttendee);
			}
		} else {
			TextView noAttendees = new TextView(this);
			noAttendees.setText(R.string.noAttendees);
			layout.addView(noAttendees);
		}
	}

	private Boolean saveEvent() {
		// input validation
		// If event title is false, return blank
		String titleText = this.titleText.getText().toString();
		if (titleText == null || titleText.isEmpty()) {
			setResult(AddEventActivity.BLANK_TITLE);
			return false;
		}

		// Convert latitude and longitude to floats
		float latitude;
		try {
			latitude = Float.parseFloat(this.latitudeText.getText().toString());
		} catch (NumberFormatException nfe) {
			latitude = 0;
		}

		float longitude;
		try {
			longitude = Float.parseFloat(this.longitudeText.getText()
					.toString());
		} catch (NumberFormatException nfe) {
			longitude = 0;
		}

		SocialEvent newEvent = new SimpleSocialEvent(titleText, this.venueText
				.getText().toString(), latitude, longitude, startTimeCalendar,
				endTimeCalendar, this.notesText.getText().toString(),
				attendeeList, this.voiceNoteFile);// attendeeEmails

		if (editEvent) {
			model.deleteEvent(oldEventId);
			setResult(AddEventActivity.EDITED_EVENT);
		} else {
			setResult(AddEventActivity.ADDED_EVENT);
		}

		model.addEvent(newEvent);

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ADD_INVITEES) {
			if (resultCode == RESULT_OK) {
				ContactToAttendee contactParser = new ContactToAttendee(this,
						data);
				Attendee attendee;
				if ((attendee = contactParser.getNewAttendee()) != null) {
					attendeeList.add(attendee);
					updateAttendees();
					Toast.makeText(getApplicationContext(),
							R.string.addedAttendee, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(),
							R.string.blankAttendee, Toast.LENGTH_LONG).show();
				}

			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_saveAddEvent) {
			saveEvent();
			finish();
			return true;
		}
		if (id == R.id.action_deleteAddEvent) {
			setResult(AddEventActivity.DELETED_EVENT);
			if (editEvent)
				model.deleteEvent(oldEventId);
			finish();
			return true;
		}
		if (id == R.id.action_cancelAddEvent) {
			setResult(Activity.RESULT_CANCELED);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
