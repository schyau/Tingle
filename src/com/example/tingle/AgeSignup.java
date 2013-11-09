package com.example.tingle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgeSignup extends Activity {
	EditText age_edittext;
	TextView age_textview;
	TextView next_button_age;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.agesignup);
		age_edittext = (EditText) findViewById(R.id.age_edittext);
		age_textview = (TextView) findViewById(R.id.age_textview);
		next_button_age = (TextView) findViewById(R.id.next_button_age);
		age_textview.setTypeface(FontFactory.getKI(this));
		final Context instance = this;
		next_button_age.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (age_edittext == null || "".equals(age_edittext)) {
					return;
				}
				int age = -1;

				String ageStr = age_edittext.getText().toString();
				try {
					age = Integer.parseInt(ageStr);
				} catch (Exception e) {
					Toast.makeText(instance, ageStr + " is not a valid age",
							Toast.LENGTH_SHORT).show();
					return;
				}
				SPref.addAge(age + "");
				Intent intent = new Intent(instance, TinglerActivity.class);
				startActivity(intent);
				finish();
			}

		});
	}
}