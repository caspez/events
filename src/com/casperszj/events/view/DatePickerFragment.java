package com.casperszj.events.view;

import java.util.Calendar;

import com.casperszj.utility.CalendarUtility;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private Button dateButton;
	private Calendar currentDate;

	public DatePickerFragment(Button dateButton, Calendar currentDate) {
		this.dateButton = dateButton;
		this.currentDate = currentDate;
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Create DatePickerDialog instance
		return new DatePickerDialog(getActivity(), this,
				currentDate.get(Calendar.YEAR),
				currentDate.get(Calendar.MONTH),
				currentDate.get(Calendar.DAY_OF_MONTH));
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		currentDate.set(Calendar.YEAR, year);
		currentDate.set(Calendar.MONTH, month);
		currentDate.set(Calendar.DAY_OF_MONTH, day);
		dateButton.setText(CalendarUtility.getDateString(currentDate));

	}
}