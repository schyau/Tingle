package com.example.tingle;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class TingleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .build();
        ImageLoader.getInstance().init(config);
    }
    
    public DisplayImageOptions getImageOptions (){
    	DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisc(true).build();
    	
    	return options;
    }
}