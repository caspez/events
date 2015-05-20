package com.casperszj.events.controller;

import java.util.Calendar;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.casperszj.events.view.DatePickerFragment;

public class EditDateListener implements OnClickListener {

	private FragmentManager fragment;
	Button dateButton;
	Calendar currentDate;

	public EditDateListener(FragmentManager fragment, Button dateButton, Calendar currentDate) {
		this.fragment = fragment;
		this.dateButton = dateButton;
		this.currentDate = currentDate;
	}

	@Override
	public void onClick(View v) {
		DialogFragment newFragment = new DatePickerFragment(dateButton, currentDate);
		newFragment.show(this.fragment, "datePicker");
	}

}
