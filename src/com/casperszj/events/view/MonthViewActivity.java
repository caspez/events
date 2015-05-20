package com.casperszj.events.view;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.casperszj.events.R;
import com.casperszj.events.view.model.DayMonthAdapter;

@SuppressLint("SimpleDateFormat")
public class MonthViewActivity extends Activity {

	// 7*6=42 (7 days in a week * 6 possible weeks covered by month*/
	public static final int DAYS_VISIBILE = 42;
	public static final int FROM_LIST_VIEW = 2;

	private ArrayAdapter<Calendar> monthAdapter;
	private List<Calendar> daysArrayList;
	private GridView monthGridView;
	private Button previousButton;
	private Button nextButton;
	private Calendar currentViewingMonth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.month_view);

		setResult(Activity.RESULT_OK);

		// set the week day names on view
		setWeekDayNames();

		// Set button listeners
		setButtons();

		currentViewingMonth = getFirstDayOfMonth();

		// create list of days from first day of current month
		daysArrayList = createMonthList(currentViewingMonth);

		// set adapter
		monthAdapter = new DayMonthAdapter(this, 0, daysArrayList);

		// set grid view based on month array
		monthGridView = (GridView) findViewById(R.id.monthView);
		monthGridView.setAdapter(monthAdapter);

	}

	private void refreshGridView() {
		daysArrayList = createMonthList(currentViewingMonth);
		monthAdapter.clear();
		monthAdapter.addAll(daysArrayList);
		monthGridView.setAdapter(monthAdapter);
		monthAdapter.notifyDataSetChanged();
	}

	private void setButtons() {
		previousButton = (Button) findViewById(R.id.previousMonth);
		nextButton = (Button) findViewById(R.id.nextMonth);
		this.previousButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// go back 1 month
				currentViewingMonth.add(Calendar.MONTH, -1);
				refreshGridView();
			}
		});
		this.nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// go back 1 month
				currentViewingMonth.add(Calendar.MONTH, 1);
				refreshGridView();
			}
		});

	}

	// create month list
	private List<Calendar> createMonthList(Calendar month) {
		// set title as month name
		this.setTitle(new SimpleDateFormat("MMMM yyyy").format(month.getTime()));

		// get week day of the first day of the month
		int startingDay = month.get(Calendar.DAY_OF_WEEK) - 1;

		// get last grid position
		int endOfMonth = month.getActualMaximum(Calendar.DAY_OF_MONTH);

		// create month calendar array
		Calendar[] monthDates = new Calendar[DAYS_VISIBILE];
		for (int i = 1; i <= endOfMonth; i++) {
			// minus 1 since first day of week or month is 1
			monthDates[i - 1 + startingDay] = new GregorianCalendar(
					month.get(Calendar.YEAR), month.get(Calendar.MONTH), i);
		}
		ArrayList<Calendar> calendarList = new ArrayList<Calendar>();
		calendarList.addAll(Arrays.asList(monthDates));
		return calendarList;
	}

	private void setWeekDayNames() {
		// week day titles
		// get days of week strings and write to view
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] days = dfs.getShortWeekdays();
		List<String> daysArrayList = new ArrayList<String>(
				Arrays.asList((String[]) days));
		// since days start at element 1, need to rotate 1 to left
		Collections.rotate(daysArrayList, -1);
		daysArrayList.remove(daysArrayList.size() - 1);

		GridView dayTitles = (GridView) findViewById(R.id.daysOfWeek);
		dayTitles.setAdapter(new ArrayAdapter<String>(this,
				R.layout.name_of_day, R.id.dayName, daysArrayList));
	}

	// returns the first day of the current month
	private Calendar getFirstDayOfMonth() {
		Calendar month = Calendar.getInstance();
		// reset calendar to the first day of the month
		month.set(Calendar.DAY_OF_MONTH,
				month.getActualMinimum(Calendar.DAY_OF_MONTH));
		// reset time of calendar
		month.set(Calendar.HOUR_OF_DAY,
				month.getActualMinimum(Calendar.HOUR_OF_DAY));
		month.set(Calendar.MINUTE, month.getActualMinimum(Calendar.MINUTE));
		month.set(Calendar.SECOND, month.getActualMinimum(Calendar.SECOND));
		month.set(Calendar.MILLISECOND,
				month.getActualMinimum(Calendar.MILLISECOND));

		return month;
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

		if (resultCode == AddEventActivity.EDITED_EVENT
				|| resultCode == AddEventActivity.ADDED_EVENT
				|| resultCode == AddEventActivity.DELETED_EVENT) {
			refreshGridView();
			// set result code to give to listview on return
			setResult(EventsListActivity.CHANGED_EVENTS);

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.month_view, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_addEvent:// add new event
			startActivityForResult(new Intent(this, AddEventActivity.class),
					AddEventActivity.NEW_EVENT);
			return true;
		case R.id.action_listView:
			onBackPressed(); // to prevent endless activity paths
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
