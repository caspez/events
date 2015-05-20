package com.casperszj.events.controller;

import java.util.Calendar;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.casperszj.events.view.TimePickerFragment;

public class EditTimeListener implements OnClickListener {

	private FragmentManager fragment;
	private Button timeButton;
	private Calendar currentTime;

	public EditTimeListener(FragmentManager fragment, Button timeButton,
			Calendar currentTime) {
		this.fragment = fragment;
		this.timeButton = timeButton;
		this.currentTime = currentTime;
	}

	@Override
	public void onClick(View v) {
		DialogFragment newFragment = new TimePickerFragment(timeButton,
				currentTime);
		newFragment.show(this.fragment, "timePicker");
	}

}
