package com.example.tingle;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class TingleActivity extends FragmentActivity {

	Handler handler;
	Runnable flipper;
	public List<User> tingles;
	ImageView loadingSign;

	public List<User> getTingles() {
		return tingles;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.tingle_slideshow);
		loadingSign = (ImageView) findViewById(R.id.loadingSign);
		loadingSign.setVisibility(View.VISIBLE);
		repopulateList();
	}

	static int count = 0;

	public void repopulateList() {
		// make get request
		
		new AsyncTask<Void, Void, TingleSearchResult>() {

			@Override
			protected TingleSearchResult doInBackground(Void... params) {				


				TingleSearchResult searchResult = TingleService.getCandidates(SPref.getUsername(), SomeFragment.current_num);
				SomeFragment.current_num=0;
				return searchResult;
			}

			@Override
			protected void onPostExecute(TingleSearchResult searchResult) {
				tingles = searchResult.getData().getCandidates();
				loadingSign.setVisibility(View.GONE);
				
				startTinglin();
			}

		}.execute();
	}

	private void startTinglin() {
		SomeFragment fragment = new SomeFragment();
		fragment.shouldLoopFlag=true;
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.setCustomAnimations(R.anim.slide_in_left,
				R.anim.slide_out_right);
		transaction.replace(R.id.contentFragment, fragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}