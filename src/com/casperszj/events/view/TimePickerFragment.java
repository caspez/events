package com.casperszj.events.view;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.casperszj.utility.CalendarUtility;

public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

	private Button timeButton;
	private Calendar currentTime;

	public TimePickerFragment(Button timeButton, Calendar currentTime) {
		this.timeButton = timeButton;
		this.currentTime = currentTime;
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Create DatePickerDialog instance
		return new TimePickerDialog(getActivity(), this,
				currentTime.get(Calendar.HOUR_OF_DAY),
				currentTime.get(Calendar.MINUTE), false);
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
		currentTime.set(Calendar.MINUTE, minute);
		timeButton.setText(CalendarUtility.getTimeString(currentTime));
	}
}