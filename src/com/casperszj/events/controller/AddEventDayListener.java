package com.casperszj.events.controller;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.casperszj.events.view.AddEventActivity;

public class AddEventDayListener implements OnClickListener {

	

	private Activity activity;
	private Calendar day;

	public AddEventDayListener(Activity activity, Calendar day) {
		this.activity = activity;
		this.day = day;
	}

	@Override
	public void onClick(View v) {		
		Intent intent = new Intent(activity, AddEventActivity.class);
		//need to convert calendar to long so to send as extras
		intent.putExtra(AddEventActivity.DAY_AS_EXTRA, day.getTimeInMillis());
		this.activity.startActivityForResult(intent, AddEventActivity.ADD_DAY_EVENT);
	}

}
