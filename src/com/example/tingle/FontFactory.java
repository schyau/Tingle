package com.example.tingle;

import android.content.Context;
import android.graphics.Typeface;

public class FontFactory {

	private static Typeface t1;

	public static Typeface getKI(Context c) {
		if (t1 == null) {
			t1 = Typeface.createFromAsset(c.getAssets(), "Klavika-Italic.ttf");
		}
		return t1;
	}
}