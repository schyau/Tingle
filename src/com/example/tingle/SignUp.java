package com.example.tingle;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp extends Activity {
	ImageView profilepicture;
	View maleLL;
	View femaleLL;
	View loadingsingup;
	View singupLoadingRL;
	View singupRL;
	TextView hot;
	TextView not;
	TextView fetchingtinglers;

	private static final int SELECT_PHOTO = 100;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.singup);
		profilepicture = (ImageView) findViewById(R.id.profilepicture);
		maleLL = (View) findViewById(R.id.maleLL);
		femaleLL = (View) findViewById(R.id.femaleLL);
		loadingsingup = (ImageView) findViewById(R.id.loadingsingup);
		fetchingtinglers = (TextView) findViewById( R.id.fetchingtinglers);
		fetchingtinglers.setTypeface(FontFactory.getKI(this));
		singupLoadingRL = (View) findViewById(R.id.singupLoadingRL);
		singupRL = (View) findViewById(R.id.singupRL);
		hot = (TextView) findViewById(R.id.maleTextView);
		not = (TextView) findViewById(R.id.femaleTextView);
		hot.setTypeface(FontFactory.getKI(this));
		not.setTypeface(FontFactory.getKI(this));
		maleLL.setOnClickListener(new BlankClick());
		femaleLL.setOnClickListener(new BlankClick());
		

		profilepicture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		final Activity instance = this;
		switch (requestCode) {
		case SELECT_PHOTO:
			if (resultCode == RESULT_OK) {
				final Uri selectedImage = imageReturnedIntent.getData();
				String localLocation = getRealPathFromURI(this, selectedImage);
				SPref.addPhotoLocation(localLocation);

				InputStream imageStream;
				try {
					imageStream = getContentResolver().openInputStream(
							selectedImage);
					Bitmap yourSelectedImage = BitmapFactory
							.decodeStream(imageStream);
					profilepicture.setImageBitmap(yourSelectedImage);
					maleLL.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							shouldDisplayLoadingSign(true);
							SPref.addGender("male");
							new SendToSam(selectedImage, instance).execute();
						}
					});
					femaleLL.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							shouldDisplayLoadingSign(true);
							SPref.addGender("female");
							new SendToSam(selectedImage, instance).execute();
						}
					});
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public String getRealPathFromURI(Context context, Uri contentUri) {
		Cursor cursor = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA };
			cursor = context.getContentResolver().query(contentUri, proj, null,
					null, null);
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	private void shouldDisplayLoadingSign(boolean shouldDisplayLoadingFlag) {
		if (shouldDisplayLoadingFlag) {
			Animation rotation = AnimationUtils.loadAnimation(this,
					R.anim.rotate);
			rotation.setRepeatCount(Animation.INFINITE);
			singupLoadingRL.setVisibility(View.VISIBLE);
			singupRL.setVisibility(View.GONE);
			loadingsingup.setAnimation(rotation);
		} else {
			singupLoadingRL.setVisibility(View.GONE);
		}
	}

	private class BlankClick implements OnClickListener {
		@Override
		public void onClick(View v) {
		}
	}

	public class SendToSam extends ImgurUploadTask {
		public SendToSam(Uri imageUri, Activity activity) {
			super(imageUri, activity);
		}

		@Override
		protected String doInBackground(Void... params) {
			String imgUrl = "http://imgur.com/" + super.doInBackground(params)+".png";
			String name = SPref.getName();
			String username = SPref.getUsername();
			String password = SPref.getPassword();
			String gender = SPref.getGender();
			String age = SPref.getAge();

			Log.d("asdfasdf", imgUrl);

			Log.d("asdfasdf", name);
			Log.d("asdfasdf", username);
			Log.d("asdfasdf", password);
			Log.d("asdfasdf", gender);
			Log.d("asdfasdf", age);

			String y = TingleService
					.post("https://evening-dawn-4592.herokuapp.com/user?username="
							+ username);
			String x = TingleService
					.put("https://evening-dawn-4592.herokuapp.com/user?username="
							+ username
							+ "&age="
							+ age
							+ "&gender="
							+ gender
							+ "&first_name=" + name + "&image_url=" + imgUrl);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			Intent intent = new Intent(mActivity, TingleActivity.class);
			startActivity(intent);
			finish();
		}
	}
}