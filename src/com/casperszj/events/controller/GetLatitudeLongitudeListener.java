package com.casperszj.events.controller;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.casperszj.events.R;
import com.google.android.gms.maps.GoogleMap;

public class GetLatitudeLongitudeListener implements OnClickListener {

	private Activity activity;

	public GetLatitudeLongitudeListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		EditText venue = (EditText) activity.findViewById(R.id.addVenue);
		String location = venue.getText().toString();

		EditText latitudeText = (EditText) activity
				.findViewById(R.id.addLatitude);
		EditText longitudeText = (EditText) activity
				.findViewById(R.id.addLongitude);

		List<Address> addresses = null;
		try {// try getting first result
				// create geocoder object
			if (location != null && !location.isEmpty()) {
				Geocoder geocoder = new Geocoder(
						activity.getApplicationContext());

				addresses = geocoder.getFromLocationName(location, 1);

				if (addresses != null && addresses.size() > 0) {
					double latitude = addresses.get(0).getLatitude();
					double longitude = addresses.get(0).getLongitude();
					latitudeText.setText(String.valueOf(latitude));
					longitudeText.setText(String.valueOf(longitude));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			addresses = null;
			e.printStackTrace();
		} finally {
			if (addresses == null)
				Toast.makeText(
						activity.getApplicationContext(),
						activity.getResources().getString(
								R.string.toast_geocoder_fail),
						Toast.LENGTH_SHORT).show();
			else {
				Toast.makeText(
						activity.getApplicationContext(),
						activity.getResources().getString(
								R.string.toast_geocoder_success)
								+ " " + location, Toast.LENGTH_SHORT).show();

			}
		}

	}
}
