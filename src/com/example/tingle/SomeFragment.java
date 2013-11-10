package com.example.tingle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class SomeFragment extends Fragment {
	public static int PAGINATE_NUM = 1000;
	public static int current_num = 0;
	public boolean shouldLoopFlag = false;
	Handler handler;
	Runnable flipper;
	public boolean flag = true;
	View profileRL;
	View loadingProfile;

	public boolean hotbool = false;
	public boolean notbool = false;
	public int count = 0;
	User person;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View vw = inflater.inflate(R.layout.profile, null);
		person = ((TingleActivity) getActivity()).getTingles().get(
				current_num++ % PAGINATE_NUM);
		profileRL = (View) vw.findViewById(R.id.profileRL);
		loadingProfile = (View) vw.findViewById(R.id.loadingProfile);
		ImageView imageView = (ImageView) vw.findViewById(R.id.picture);
		final View voteLL = (View) vw.findViewById(R.id.voteLL);
		final View notLL = (View) vw.findViewById(R.id.notLL);
		final View hotLL = (View) vw.findViewById(R.id.hotLL);

		View hot = (View) vw.findViewById(R.id.hot);
		View not = (View) vw.findViewById(R.id.not);

		notLL.setBackgroundColor(getResources().getColor(
				R.color.hotnot_unselected));
		hotLL.setBackgroundColor(getResources().getColor(
				R.color.hotnot_unselected));
		notbool = false;
		hotbool = false;
		voteLL.setVisibility(View.GONE);

		final Context instance = getActivity();
		profileRL.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				Intent intent = new Intent(getActivity(),
						FakeActivity.class);
				startActivity(intent);
				flag = false;
				return false;
			}
			
		});
		profileRL.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (count++ == 2) {

				}

				if (voteLL.getVisibility() == View.VISIBLE) {
					voteLL.setVisibility(View.GONE);
				} else {
					voteLL.setVisibility(View.VISIBLE);
				}
			}

		});

		not.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				notLL.setBackgroundColor(getResources().getColor(
						R.color.hotnot_selected));
				hotLL.setBackgroundColor(getResources().getColor(
						R.color.hotnot_unselected));
				notbool = true;
				hotbool = false;
			}

		});
		hot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				notLL.setBackgroundColor(getResources().getColor(
						R.color.hotnot_unselected));
				hotLL.setBackgroundColor(getResources().getColor(
						R.color.hotnot_selected));
				notbool = false;
				hotbool = true;
			}

		});
		DisplayImageOptions options = ((TingleApplication) getActivity()
				.getApplication()).getImageOptions();

		ImageLoader.getInstance().displayImage(
				// "http://imgur.com/" + person.image_url + ".png"
				person.image_url, imageView, options,
				new ImageLoadingListener() {
					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						shouldDisplayLoadingSign(false);
						TextView name = (TextView) vw.findViewById(R.id.name);
						name.setTypeface(FontFactory.getKI(getActivity()));
						TextView genderage = (TextView) vw
								.findViewById(R.id.genderage);
						name.setText(person.first_name);
						genderage.setText(person.getGender() + ", "
								+ person.getAge());

						genderage.setTypeface(FontFactory.getKI(getActivity()));
						startTheTimer();
					}

					@Override
					public void onLoadingCancelled(String arg0, View arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLoadingFailed(String arg0, View arg1,
							FailReason arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onLoadingStarted(String arg0, View arg1) {

						shouldDisplayLoadingSign(true);
					}
				});

		return vw;
	}

	public void shouldDisplayLoadingSign(boolean shouldDisplayLoadingSign) {
		if (shouldDisplayLoadingSign) {
			profileRL.setVisibility(View.GONE);
			loadingProfile.setVisibility(View.VISIBLE);
			Animation rotation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.rotate);
			rotation.setRepeatCount(Animation.INFINITE);
			loadingProfile.setAnimation(rotation);
		} else {
			profileRL.setVisibility(View.VISIBLE);
			loadingProfile.clearAnimation();
			loadingProfile.setVisibility(View.GONE);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		flag = true;
	}

	@Override
	public void onPause() {
		super.onPause();
		flag = false;
	}

	private void startTheTimer() {
		handler = new Handler();
		final Context instance = getActivity();
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int size = 0;
				try {
					size = ((TingleActivity) getActivity()).getTingles().size();
				} catch (NullPointerException npe) {
					// this is how we know we've exited
					/*
					 * Intent intent = new Intent(getActivity(),
					 * FakeActivity.class); startActivity(intent);
					 */
					return;
				}
				if (flag && current_num % PAGINATE_NUM != 0
						&& current_num < size) {

					if (hotbool) {
						new Thread(new Runnable() {

							@Override
							public void run() {

								TingleService
										.post("https://evening-dawn-4592.herokuapp.com/vote/hot?voter="
												+ SPref.getUsername()
												+ "&candidate="
												+ person.getUsername());

							}

						}).start();
					} else if (notbool) {
						new Thread(new Runnable() {

							@Override
							public void run() {

								TingleService
										.post("https://evening-dawn-4592.herokuapp.com/vote/not?voter="
												+ SPref.getUsername()
												+ "&candidate="
												+ person.getUsername());

							}

						}).start();
					}

					Fragment fragment = new SomeFragment();
					FragmentManager fm = getActivity()
							.getSupportFragmentManager();
					FragmentTransaction transaction = fm.beginTransaction();
					transaction.setCustomAnimations(R.anim.slide_in_left,
							R.anim.slide_out_right);
					transaction.replace(R.id.contentFragment, fragment);
					transaction.commit();
				} else {
					flag = false;
					getActivity().runOnUiThread(new Runnable() {

						@Override
						public void run() {
							startActivity(new Intent(getActivity(),
									TingleActivity.class));
						}

					});
				}
			}

		};
		new Thread(r).start();

	}
}