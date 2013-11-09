package com.example.tingle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	static EditText username;
	static EditText password;
	static TextView login;
	static ImageView login_loading;
	static TextView create_new_account;
	static TextView login_title;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		SPref.init(getApplicationContext());
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.login);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (TextView) findViewById(R.id.login);
		login_loading = (ImageView) findViewById(R.id.login_loading);
		create_new_account = (TextView) findViewById(R.id.create_new_account);
		login_title = (TextView) findViewById(R.id.login_title);

		login_title.setTypeface(FontFactory.getKI(this));

		setIsLoading(false);
		final Context instance = this;
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String user = username.getText().toString();
				final String pass = password.getText().toString();
				if (user == null || "".equals(user) || pass == null
						|| "".equals(pass))
					return;

				setIsLoading(true);
				// send to sam using asynctask
				(new AsyncTask<Void, Void, TingleSearchResult>() {

					@Override
					protected TingleSearchResult doInBackground(Void... params) {
						return TingleService.getUser(user);
					}

					@Override
					protected void onPostExecute(TingleSearchResult result) {
						// parse sam's string
						if( result == null || result.getData() == null || result.getData().getUser()==null )
							
						{
							Toast.makeText(instance, "This is user is not recognized, you can sign up though!", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(instance, Login.class);
							startActivity(intent); // setIsLoading( false); 
							finish();
							return;
						}
						User user = result.getData().getUser();
						SPref.addGender(user.getGender());
						SPref.addName(user.getFirst_name());
						SPref.addAge(""+user.getAge());
						SPref.addPhotoLocation(user.getImage_url());
						SPref.addUsername(user.getUsername());
						
						Intent intent = new Intent(instance, TingleActivity.class);
						startActivity(intent); // setIsLoading( false); 
						finish();
					}

				}).execute();
			}
		});
		create_new_account.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String user = username.getText().toString();
				final String pass = password.getText().toString();
				if (user == null || "".equals(user) || pass == null
						|| "".equals(pass))
					return;
				new AsyncTask<Void,Void,TingleSearchResult>()
				{

					@Override
					protected TingleSearchResult doInBackground(Void... params) {
						return TingleService.getUser(user);

					}
					@Override
					protected void onPostExecute(TingleSearchResult param)
					{
						if(param !=null && param.getData() !=null && param.getData().getUser() == null )
						{
							SPref.addUsername(user);
							SPref.addPassword(pass);
							Intent intent = new Intent(instance, NameSignup.class);
							startActivity(intent); // setIsLoading( false); 
							finish();
						}
						else
						{
							Toast.makeText(instance, user+" already exists!", Toast.LENGTH_SHORT).show();
						}
					}
					
				}.execute();
			}
		});
	}

	/*
	 * Intent intent = new Intent(instance, SignUp.class);
	 * startActivity(intent); // setIsLoading( false); finish();
	 */

	private void setIsLoading(boolean shouldDisplayLoadingSign) {

		if (shouldDisplayLoadingSign) {
			Animation rotation = AnimationUtils.loadAnimation(this,
					R.anim.rotate);
			rotation.setRepeatCount(Animation.INFINITE);
			login_loading.startAnimation(rotation);
			username.setVisibility(View.GONE);
			password.setVisibility(View.GONE);
			login.setVisibility(View.GONE);
			create_new_account.setVisibility(View.GONE);
			login_loading.setVisibility(View.VISIBLE);
		} else {
			username.setVisibility(View.VISIBLE);
			password.setVisibility(View.VISIBLE);
			login.setVisibility(View.VISIBLE);
			create_new_account.setVisibility(View.VISIBLE);
			login_loading.clearAnimation();
			login_loading.setVisibility(View.GONE);
		}
	}

}