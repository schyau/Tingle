package com.example.tingle;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class FakeActivity extends Activity {

	User user;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		user = new User();
		user.age = 10;
		user.first_name = "Milhouse";
		user.gender = "Male";
		user.username = "Milhaus";
		user.image_url = "http://st-listas.20minutos.es/images/2010-12/264195/2771238_640px.jpg?1291845754.jpg";
		someFunc(user);
		finish();

	}

	private void someFunc(User user) {

		String title = "A Tingler is nearby!";
		String message = "Tap to checkout " + user.first_name;
		Notification notif = new Notification(
		R.drawable.tinlgesmall, message,
		System.currentTimeMillis());
		notif.flags = Notification.FLAG_AUTO_CANCEL;
		notif.defaults |= Notification.DEFAULT_SOUND;
		notif.defaults |= Notification.DEFAULT_VIBRATE;

		Intent notificationIntent = new Intent(this,
				TingleNotificationActivity.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		notificationIntent.putExtra(PersonX.USERNAME_KEY, user.username);
		notificationIntent.putExtra(PersonX.AGE_KEY, "" + user.age);
		notificationIntent.putExtra(PersonX.GENDER_KEY, user.gender);
		notificationIntent.putExtra(PersonX.LOCALPHOTO_KEY, user.image_url);
		notificationIntent.putExtra(PersonX.NAME_KEY, user.first_name);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		notif.setLatestEventInfo(this, title, message, contentIntent);
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		mNotificationManager.notify(1, notif);
	}
}