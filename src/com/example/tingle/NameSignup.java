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

public class NameSignup extends Activity {
	EditText name_edittext;
	TextView name_textview;
	TextView next_button_name;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.name_signup);
		name_edittext = (EditText) findViewById(R.id.name_edittext);
		name_textview = (TextView) findViewById(R.id.name_textview);
		name_textview.setTypeface(FontFactory.getKI(this));
		next_button_name = (TextView) findViewById(R.id.next_button_name);
		final Context instance = this;
		next_button_name.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (name_edittext == null || "".equals(name_edittext)) {
					return;
				}
				String name = name_edittext.getText().toString();
				SPref.addName(name);
				Intent intent = new Intent(instance, AgeSignup.class);
				startActivity(intent);
				finish();
			}
		});
	}
}