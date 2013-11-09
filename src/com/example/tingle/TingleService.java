//https://evening-dawn-4592.herokuapp.com/user?username=foo

package com.example.tingle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class TingleService {
	private static final String TAG = TingleService.class.getName();
	private static Gson gson = new GsonBuilder().setFieldNamingPolicy(
			FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

	// use a builder for disssss
	public static TingleSearchResult getUser(String username) {
		String getURL = "https://evening-dawn-4592.herokuapp.com/user"
				+ "?username=" + username;
		Log.d(TAG, "url: " + getURL);
		String response = search(getURL);

		Log.d(TAG, "Response: " + response);
		TingleSearchResult tingleSearchResult = null;
		try {
			tingleSearchResult = gson.fromJson(response,
					TingleSearchResult.class);
		} catch (JsonSyntaxException ex) {
			Log.e(TAG, ex.getCause() + " : " + ex.getLocalizedMessage());
		}
		return tingleSearchResult;
	}

	public static TingleSearchResult getCandidates(String username, int start) {
		String getURL = "https://evening-dawn-4592.herokuapp.com/candidates?username="
				+ username
				+ "&start_index="
				+ 0
				+ "&count="
				+ SomeFragment.PAGINATE_NUM;
		Log.d(TAG, "url: " + getURL);
		String response = search(getURL);

		Log.d(TAG, "Response: " + response);
		TingleSearchResult tingleSearchResult = null;
		try {
			tingleSearchResult = gson.fromJson(response,
					TingleSearchResult.class);
		} catch (JsonSyntaxException ex) {
			Log.e(TAG, ex.getCause() + " : " + ex.getLocalizedMessage());
		}
		return tingleSearchResult;
	}

	private static String search(String getURL) {
		String response = "";
		try {
			Log.d(TAG, getURL);
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(getURL);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			if (resEntityGet != null) {
				// do something with the response
				response = EntityUtils.toString(resEntityGet);
			}
		} catch (Exception e) {

		}
		return response;
	}

	public static String put(String url) {
		String response = "";
		try {
			Log.d(TAG, url);
			HttpClient client = new DefaultHttpClient();
			HttpPut put = new HttpPut(url);
			HttpResponse responsePut = client.execute(put);
			HttpEntity resEntityPut = responsePut.getEntity();
			if (resEntityPut != null) {
				// do something with the response
				response = EntityUtils.toString(resEntityPut);
			}
		} catch (Exception e) {

		}
		return response;
	}

	public static String post(String url) {
		String response = "";
		try {
			Log.d(TAG, url);
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			HttpResponse responsePost = client.execute(post);
			HttpEntity resEntityPost = responsePost.getEntity();
			if (resEntityPost != null) {
				// do something with the response
				response = EntityUtils.toString(resEntityPost);
			}
		} catch (Exception e) {

		}
		return response;
	}
}
