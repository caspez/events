package com.casperszj.events.controller;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;

import com.casperszj.events.view.AddEventActivity;

public class AddContactListener implements OnClickListener {

	private Activity activity;

	public AddContactListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
				Contacts.CONTENT_URI);
		activity.startActivityForResult(contactPickerIntent,
				AddEventActivity.ADD_INVITEES);

	}

}
