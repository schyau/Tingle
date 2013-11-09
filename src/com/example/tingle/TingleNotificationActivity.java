package com.example.tingle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class TingleNotificationActivity extends Activity {
	TextView sendtingle;
	TextView ignoretingle;
	TextView tinglersex;
	TextView tinglerage;
	TextView tinglerName;
	TextView tinglermessage;
	View tinglenotification;
	View loading_image;
	ImageView tinglerProfile;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.tingle_notification);
		sendtingle = (TextView) findViewById(R.id.sendtingle);
		ignoretingle = (TextView) findViewById(R.id.ignoretingle);
		tinglersex = (TextView) findViewById(R.id.tinglersex);
		tinglerage = (TextView) findViewById(R.id.tinglerage);
		tinglerName = (TextView) findViewById(R.id.tinglerName);
		tinglermessage = ( TextView ) findViewById(R.id.tinglermessage);
		tinglenotification = (View) findViewById(R.id.tinglenotification);
		loading_image = (View) findViewById(R.id.loading_image);
		tinglerProfile = (ImageView) findViewById(R.id.tinglerProfile);
		sendtingle.setTypeface(FontFactory.getKI(this));
		ignoretingle.setTypeface(FontFactory.getKI(this));
		tinglersex.setTypeface(FontFactory.getKI(this));
		tinglerage.setTypeface(FontFactory.getKI(this));
		tinglerName.setTypeface(FontFactory.getKI(this));
		tinglermessage.setTypeface(FontFactory.getKI(this));

		
		Bundle bundle = getIntent().getExtras();
		final String username = bundle.getString(PersonX.USERNAME_KEY);

		final String age = bundle.getString(PersonX.AGE_KEY);
		final String gender = bundle.getString(PersonX.GENDER_KEY);
		final String photo = bundle.getString(PersonX.LOCALPHOTO_KEY);
		final String name = bundle.getString(PersonX.NAME_KEY);
		final Context instance = this;
		sendtingle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(instance, "A Tingle has been sent to " + name,
						Toast.LENGTH_SHORT).show();

			}

		});
		ignoretingle.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		tinglersex.setText(gender);
		tinglerage.setText(age);
		tinglerName.setText(name);
		ImageLoader.getInstance().displayImage(
				// "http://imgur.com/" + person.image_url + ".png"
				photo, tinglerProfile,
				((TingleApplication) getApplication()).getImageOptions(),
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
					}
				});

		shouldDisplayLoading(true);
		(new AsyncTask<Void, Void, TingleSearchResult>() {

			@Override
			protected TingleSearchResult doInBackground(Void... params) {
				return TingleService.getUser(name);
			}

			@Override
			protected void onPostExecute(TingleSearchResult result) {
				shouldDisplayLoading(false);
				User user = result.getData().getUser();
				/*if (user != null) {
					tinglersex.setText(user.getGender());
					tinglerage.setText("" + user.getAge());
					tinglerName.setText(user.getFirst_name());
				}*/

				Log.d("asdf", "asdf");
			}
		}).execute();
	}

	public void shouldDisplayLoading(boolean shouldDisplayLoading) {
		if (shouldDisplayLoading) {
			tinglenotification.setVisibility(View.GONE);
			loading_image.setVisibility(View.VISIBLE);
			Animation rotation = AnimationUtils.loadAnimation(this,
					R.anim.rotate);
			rotation.setRepeatCount(Animation.INFINITE);
			loading_image.setAnimation(rotation);
		} else {
			tinglenotification.setVisibility(View.VISIBLE);
			loading_image.clearAnimation();
			loading_image.setVisibility(View.GONE);

		}
	}

}