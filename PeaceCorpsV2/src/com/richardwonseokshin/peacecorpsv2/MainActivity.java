package com.richardwonseokshin.peacecorpsv2;
//http://www.paulusworld.com/technical/android-navigationdrawer-sliding-tabs

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnItemClickListener{

	private static final String TAG = MainActivity.class.getSimpleName();
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mDrawerItems;
	
	AndroidApplicationSupportUtility supportUtility = null;
	
	FrameLayout flMainScreen = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		/////////////////////////////////////////////////////////////////
		//Ported from Version 1
		super.onCreate(savedInstanceState);
        arrayMenuItems = getResources().getStringArray(R.array.drawer_titles);

		setContentView(R.layout.activity_main);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			//requestWindowFeature(Window.FEATURE_NO_TITLE); 
		//getSupportActionBar().hide();
		getActionBar().hide();
		
		supportUtility = new AndroidApplicationSupportUtility(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		flMainScreen = (FrameLayout)drawerLayout.getChildAt(0);
		final ImageView ivSplash = new ImageView(this);
			ivSplash.setBackgroundResource(R.drawable.splashscreen);
			flMainScreen.addView(ivSplash);
			flMainScreen.setBackgroundResource(R.drawable.splashscreen);
		//Ported from Version 1
		/////////////////////////////////////////////////////////////////
		
		mTitle = mDrawerTitle = getTitle();
		
		mDrawerItems = getResources().getStringArray(R.array.drawer_titles);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		// set a custom shadow that overlays the main content when the drawer oepns
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,  GravityCompat.START);
		
		// Add items to the ListView
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerItems));
		// Set the OnItemClickListener so something happens when a 
		// user clicks on an item.
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		mDrawerToggle = new ActionBarDrawerToggle(
				this, 
				mDrawerLayout, 
				R.drawable.ic_drawer, 
				R.string.drawer_open, 
				R.string.drawer_close
				) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
			}
			
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		// Set the default content area to item 0
		// when the app opens for the first time
		//if(savedInstanceState == null) {
		//	navigateTo(1);
		//}
		
		
		/*
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, SearchTabbedFragment.newInstance(), SearchTabbedFragment.TAG).commit();
		*/
		
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				getActionBar().show();
				flMainScreen.removeAllViews();
				flMainScreen.setBackgroundResource(R.drawable.blackgradient);
				
				

				/*
				int rID = llMainScreen.generateViewId();
				android.app.Fragment tabbedActivity = TabbedActivity.newInstance();	
		        getFragmentManager().beginTransaction().add(rID, tabbedActivity).commit();	
		        */	        
				
				flMainScreen.invalidate();
				
				getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, SearchTabbedFragment.newInstance(), SearchTabbedFragment.TAG).commit();
			}
		}, 2500);
	
	
	}
	
	/*
	 * If you do not have any menus, you still need this function
	 * in order to open or close the NavigationDrawer when the user 
	 * clicking the ActionBar app icon.
	 */
	/*
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	*/
	
	/*
	 * When using the ActionBarDrawerToggle, you must call it during onPostCreate()
	 * and onConfigurationChanged()
	 */
	/*
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	*/
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	private class DrawerItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			navigateTo(position);
			
			Toast.makeText(getApplicationContext(), arrayMenuItems[position] + " was clicked", Toast.LENGTH_LONG).show();
			
			selectItem(position);
			setTitle(arrayMenuItems[position]);
			drawerLayout.closeDrawer(Gravity.LEFT);
		}
		
		public void selectItem(int position){
			mDrawerList.setItemChecked(position, true);
		}
		public void setTitle(String title){
			//getSupportActionBar().setTitle(title);
			getActionBar().setTitle(title);
		}
	}
	
	private void navigateTo(int position) {
		Log.v(TAG, "List View Item: " + position);
		
		switch(position) {
		case 0:
			/*getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.content_frame, 
						ItemOne.newInstance(),
						ItemOne.TAG).commit();*/
			getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, SearchTabbedFragment.newInstance(), SearchTabbedFragment.TAG).commit();
			break;
		case 1:
			getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame,
						WebViewFragment.newInstance(),
						WebViewFragment.TAG).commit();
			break;
		case 2:
			
			/*
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame,
					WebViewFragment.newInstance(),
					WebViewFragment.TAG).commit();
					*/
		/*
			getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.content_frame, 
						ItemOne.newInstance(),
						ItemOne.TAG).commit();
			*/
			
			getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, SavedSearchesTabbedActivity.newInstance(), SavedSearchesTabbedActivity.TAG).commit();
				
			break;
		}
		
	}
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	
	
	
	//Ported Over from Version 1
	private DrawerLayout drawerLayout;
	private ListView listView;
	private String [] arrayMenuItems;
	private ActionBarDrawerToggle drawerListener;
	

	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);//hide overflow menu, triple dots
		return true;
	}
	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	     super.onPostCreate(savedInstanceState);
	     // Sync the toggle state after onRestoreInstanceState has occurred.
	     mDrawerToggle.syncState();
	}
	
	/*
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    //mDrawerToggle.onConfigurationChanged(newConfig);
	}
	*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	     // Pass the event to ActionBarDrawerToggle, if it returns
	     // true, then it has handled the app icon touch event
		
	     if (mDrawerToggle.onOptionsItemSelected(item)) {
	    	 //Toast.makeText(getApplicationContext(), "Menu Item Selected: " + item.getItemId(), 
	    			   //Toast.LENGTH_LONG).show();
	         return true;
	     }
	     // Handle your other action bar items...
	      
	     
		
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
		//	return true;
		//}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, arrayMenuItems[position] + " was clicked", Toast.LENGTH_LONG).show();
		selectItem(position);
		setTitle(arrayMenuItems[position]);
		drawerLayout.closeDrawer(Gravity.LEFT);
	}
	
	public void selectItem(int position){
		listView.setItemChecked(position, true);
	}
	public void setTitle(String title){
		//getSupportActionBar().setTitle(title);
		getActionBar().setTitle(title);
	}
	




}
