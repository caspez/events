package com.casperszj.events.application;
import com.casperszj.events.model.facade.EventModel;
import com.casperszj.events.model.facade.EventModelFacade;

import android.app.Application;

public class EventsApplication extends Application {

	private static EventModel model;

	public void onCreate() {
		// Create model
		model = new EventModelFacade();
		super.onCreate();
	}

	public static EventModel getModel(){
		return model;
	}
}
