package com.example.tingle;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class TinglerActivity extends FragmentActivity {

	Handler handler;
	Runnable flipper;
	ImageView tingle;
	TextView stop;
	TextView okvibrate;
	TextView tingler_textview;
	List<Long> vibrate = new LinkedList<Long>();;
	long lastTime = 0;
	String serializedPattern;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
		tingle = (ImageView) findViewById(R.id.tingle);
		stop = (TextView) findViewById(R.id.stopvibrate);
		okvibrate = (TextView) findViewById(R.id.okvibrate);
		tingler_textview = (TextView) findViewById(R.id.tingler_textview);

		stop.setTypeface(FontFactory.getKI(this));
		tingler_textview.setTypeface(FontFactory.getKI(this));
		okvibrate.setTypeface(FontFactory.getKI(this));
		final Context instance = this;
		tingle.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(vibrate.size()==0)
				{
					lastTime = System.currentTimeMillis();
					start();
				}
				switch (event.getAction()) {
				
				case MotionEvent.ACTION_DOWN:
					Animation shake = AnimationUtils.loadAnimation(instance,
							R.anim.shake);
					shake.setRepeatCount(Animation.INFINITE);
					tingle.setAnimation(shake);
					stop.setText("Save");
					if (vibrate.size() != 0) {
						long time = System.currentTimeMillis() - lastTime;

						vibrate.add(time);

						Log.d("asdf", "adding " + time);
					}
					lastTime = System.currentTimeMillis();
					break;
				case MotionEvent.ACTION_UP:
					tingle.clearAnimation();
					long time = System.currentTimeMillis() - lastTime;

					Log.d("asdf", "adding " + time);
					vibrate.add(time);
					lastTime = System.currentTimeMillis();
					break;
				}
				return true;
			}

		});
		tingle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	public void start() {
		stop.setText("Save");
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stop();
			}

		});
	}

	public void stop() // candouble as play
	{
		stop.setText("Play");
		final Context instance = this;
		okvibrate.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				SPref.addVibrate(serializedPattern);
				Intent intent = new Intent(instance, SignUp.class);
				startActivity(intent);
				finish();
				
			}
			
		});
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (Long l : vibrate)
					Log.d("tiome", "" + l.longValue());
				// Get instance of Vibrator from current Context
				Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

				// Start without a delay
				// Each element then alternates between vibrate, sleep, vibrate,
				// sleep...
				long[] pattern = new long[vibrate.size()];
				serializedPattern = "";
				for (int i = 0; i < vibrate.size(); i++) {
					serializedPattern = serializedPattern + vibrate.get(i)+".";
					pattern[i] = vibrate.get(i);
				}
				// The '-1' here means to vibrate once
				// '0' would make the pattern vibrate indefinitely
				vib.vibrate(pattern, -1);
				vibrate = new LinkedList<Long>();
			}

		}); // if doubling as play then change click listener to replay

	}

}