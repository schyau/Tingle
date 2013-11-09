package com.example.tingle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	Button hotButton;
	Button notButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
		hotButton = (Button) findViewById(R.id.hot);
		Button notButton = (Button) findViewById(R.id.not);
		hotButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});
		String msg = SPref.getAge() + SPref.getGender() + SPref.getName()
				+ SPref.getPassword() + SPref.getUsername();
		Log.d("asdf", msg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class HotOrNotClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == hotButton) {
			} else {
			}

			// fetch next one
		}

	}
}