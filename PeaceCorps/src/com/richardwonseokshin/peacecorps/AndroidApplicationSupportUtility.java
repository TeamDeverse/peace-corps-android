package com.richardwonseokshin.peacecorps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.PointF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

public class AndroidApplicationSupportUtility {
	public static int FULLSCREEN_PORTRAIT = 0;
	public static int FULLSCREEN_LANDSCAPE = 1;

	Activity activity = null;
	
	
	Point pointScreenDimensions = null;
	PointF pointScreenScale = null;
	
	@SuppressLint("NewApi")
	public AndroidApplicationSupportUtility(Activity activity) {
		this.activity = activity;
		
		pointScreenDimensions = new Point();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
		activity.getWindowManager().getDefaultDisplay().getSize(pointScreenDimensions);
		}
		else{
			pointScreenDimensions.x = activity.getWindowManager().getDefaultDisplay().getWidth();
			pointScreenDimensions.y = activity.getWindowManager().getDefaultDisplay().getHeight();
		}
		
		pointScreenScale = new PointF(pointScreenDimensions.x/540f, pointScreenDimensions.y/960f);
	}
	
	
	public void setFullScreenLandscape(){
		activity.runOnUiThread(new Runnable() {	
			@Override
			public void run() {
				activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
				activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

			}
		});
	}
	
	public void setFullScreenPortrait(){
		activity.runOnUiThread(new Runnable() {	
			@Override
			public void run() {
				activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
				activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

			}
		});
	}
	
	public Point getScreenDimensions(){
		return pointScreenDimensions;
	}
	
	public void setScreenScale(float anchorWidth, float anchorHeight){
		pointScreenScale.x = pointScreenDimensions.x /anchorWidth;
		pointScreenScale.y = pointScreenDimensions.y/anchorHeight;
	}
	
	public PointF getScreenScale(){
		return pointScreenScale;
	}
	
	
	
	//Creates file in directory (in application directory, please include extension)
	//Deletes and overwrites file
	public void writeStringToFile(String directoryName, String fileName){
		File root = Environment.getExternalStorageDirectory();
	    File fileDirectoryForApp = new File(root.getAbsolutePath() + File.separator + directoryName);

	    if(!fileDirectoryForApp.exists())
	    	fileDirectoryForApp.mkdir();

	    
		File fileToWrite = null;
		fileToWrite = new File(fileDirectoryForApp, fileName);   
		
        try {
            if (fileToWrite.exists ()){
            	fileToWrite.delete();
            }
            fileToWrite.createNewFile();
        } 
        catch (Exception e) {
               e.printStackTrace();
        }
	}
	
	public String getStringFromFile(String directoryName, String fileName){
		File root = Environment.getExternalStorageDirectory();
	    File fileDirectoryForApp = new File(root.getAbsolutePath() + File.separator + directoryName);
 
	    File inputFile = new File(fileDirectoryForApp, fileName);
	    
	    
	    String stringToReturn = "";
	    if(!fileDirectoryForApp.exists())
	    	fileDirectoryForApp.mkdir();
	    
	    if (!inputFile.exists()) {
	    	try{
		    	inputFile.createNewFile();
		    	return stringToReturn;
	    	}
	    	catch(Exception e){}
	    }
	    else{
	    	StringBuilder sb = new StringBuilder();
		    try{		    	
			    FileInputStream fileInputStream = new FileInputStream(inputFile);
			    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			    
			    String line = null;			    
			    while ((line = bufferedReader.readLine()) != null) {
			      sb.append(line);
			    }
			    
			    bufferedReader.close();
			    fileInputStream.close();      
		    }
			catch(Exception e){}
		    stringToReturn = sb.toString();
	    }
		return stringToReturn;
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	Thread threadStringFromServer = null;
    StringBuilder sb = null;
	public StringBuilder getTextFromURLTextFile(final String urlAsString){
		sb = new StringBuilder();
		
		threadStringFromServer = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
		        	    URL url = new URL(urlAsString);
		        	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));		        	    
					    String line = "";			    
					    					    
					    while ((line = in.readLine()) != null) {
					      sb.append(line);
					    }
					    
					    in.close();
					    threadStringFromServer.join();
		        	    
					}
					catch(Exception e){}
				}
			});	
			threadStringFromServer.start();
			
			try{
				Thread.sleep(100);
			}
			catch(Exception e){}
			return sb;
			
	}
	
	public int getActionBarHeight(){
		// Calculate ActionBar height
		TypedValue tv = new TypedValue();
		if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
		{
		    return TypedValue.complexToDimensionPixelSize(tv.data,activity.getResources().getDisplayMetrics());
		}
		else{
			return -1;
		}
	}
	
	@SuppressLint("NewApi")
	public int getSoftbuttonsbarHeight() {
	    // getRealMetrics is only available with API 17 and +
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
	        DisplayMetrics metrics = new DisplayMetrics();
	        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        int usableHeight = metrics.heightPixels;
	        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
	        int realHeight = metrics.heightPixels;
	        if (realHeight > usableHeight)
	            return realHeight - usableHeight;
	        else
	            return 0;
	    }
	    return 0;
	}
}
