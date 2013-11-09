package com.example.tingle;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SPref {
	private static SharedPreferences sharedPreferences;

	private SPref() {
	}

	public static void init(Context context) {

		sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	private static void savePreferences(String key, String value) {
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static void addVibrate(String vibrate) {
		savePreferences(PersonX.VIBRATE_KEY, vibrate);
	}

	public static String getVibrate() {
		return sharedPreferences.getString(PersonX.VIBRATE_KEY,null);
	}

	public static void addPhotoLocation(String localLocation) {
		savePreferences(PersonX.LOCALPHOTO_KEY, localLocation);
	}

	public static void addUsername(String username) {
		savePreferences(PersonX.USERNAME_KEY, username);
	}

	public static void addName(String name) {
		savePreferences(PersonX.NAME_KEY, name);
	}

	public static void addPassword(String password) {
		savePreferences(PersonX.PASSWORD_KEY, password);
	}

	public static void addGender(String gender) {
		savePreferences(PersonX.GENDER_KEY, gender);
	}

	public static void addAge(String age) {
		savePreferences(PersonX.AGE_KEY, age);
	}

	public static String getUsername() {
		return sharedPreferences.getString(PersonX.USERNAME_KEY, null);
	}

	public static String getName() {
		return sharedPreferences.getString(PersonX.NAME_KEY, null);
	}

	public static String getPassword() {
		return sharedPreferences.getString(PersonX.PASSWORD_KEY, null);
	}

	public static String getGender() {
		return sharedPreferences.getString(PersonX.GENDER_KEY, null);
	}

	public static String getAge() {
		return sharedPreferences.getString(PersonX.AGE_KEY, null);
	}

	public static String getPhotoLocation() {
		return sharedPreferences.getString(PersonX.LOCALPHOTO_KEY, null);
	}

}