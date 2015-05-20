package com.casperszj.events.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.casperszj.events.view.AddEventActivity;

public class EditEventController implements OnClickListener {

	private Activity activity;
	private String eventID;

	public EditEventController(String eventID, Activity activity) {
		this.activity = activity;
		this.eventID = eventID;
	}

	@Override
	public void onClick(View v) {
		// Start add event activity
		Intent intent = new Intent(activity, AddEventActivity.class);
		intent.putExtra(AddEventActivity.EVENT_ID_AS_EXTRA, this.eventID);
		this.activity.startActivityForResult(intent, AddEventActivity.EDIT_EVENT);
	}

}
