package com.casperszj.utility;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Contacts;

import com.casperszj.events.model.Attendee;

//some code below based on MAD week 3 lab
public class ContactToAttendee {

	private Context contextActivity;
	private Intent intentContactPicker;

	public ContactToAttendee(Context contextActivity, Intent intentContactPicker) {
		this.contextActivity = contextActivity;
		this.intentContactPicker = intentContactPicker;
	}

	public Attendee getNewAttendee() {
		String name, email;
		// If contact does not have a name or email
		if ((name = getContactName()) == null
				|| (email = getContactEmail()) == null)
			return null;
		return new Attendee(name, email, getContactMobile());
	}

	// return contact name or null if exception
	private String getContactName() {
		Cursor cursor = null;
		String displayName = null;

		try {
			// Get contact name from contact picker
			cursor = contextActivity.getContentResolver().query(
					intentContactPicker.getData(), null, null, null, null);
			if (cursor.moveToFirst())
				displayName = cursor.getString(cursor
						.getColumnIndexOrThrow(Contacts.DISPLAY_NAME));
		} catch (Exception e) {
			// return null on exception when trying to get contact
			displayName = null;
		} finally {// free cursor
			if (cursor != null)
				cursor.close();
		}

		return displayName;
	}

	// return contact email or null if exception
	private String getContactEmail() {
		Cursor cursor = null;
		String email = null;

		try {
			// Get contact email from contact picker
			cursor = contextActivity.getContentResolver().query(
					Email.CONTENT_URI,
					null,
					Email.CONTACT_ID + "=?",
					new String[] { intentContactPicker.getData()
							.getLastPathSegment() }, null);

			if (cursor.moveToFirst())
				email = cursor.getString(cursor.getColumnIndex(Email.DATA));
		} catch (Exception e) {
			// return null on exception when trying to get contact email
			email = null;
		} finally {// free cursor
			if (cursor != null)
				cursor.close();
		}

		return email;
	}

	// return contact mobile or null if exception
	private String getContactMobile() {
		/*Cursor cursor = null;
		String mobile = null;

		try {
			// Get contact mobile from contact picker
			cursor = contextActivity.getContentResolver().query(
					Email.CONTENT_URI,
					null,
					Email.CONTACT_ID + "=?",
					new String[] { intentContactPicker.getData()
							.getLastPathSegment() }, null);

			if (cursor.moveToFirst())
				mobile = cursor.getString(cursor.getColumnIndex(Email.DATA));
		} catch (Exception e) {
			// return null on exception when trying to get contact's mobile
			mobile = null;
		} finally {// free cursor
			if (cursor != null)
				cursor.close();
		}

		return mobile;*/
		return null;
	}

}
