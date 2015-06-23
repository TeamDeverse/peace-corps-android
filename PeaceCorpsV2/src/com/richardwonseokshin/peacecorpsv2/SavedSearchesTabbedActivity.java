package com.richardwonseokshin.peacecorpsv2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SavedSearchesTabbedActivity extends Fragment {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	
	ProgressDialog progress;

	public static final String TAG = SavedSearchesTabbedActivity.class.getSimpleName();
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	
	public static SavedSearchesTabbedActivity newInstance() {
		return new SavedSearchesTabbedActivity();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_item_one, container, false);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getChildFragmentManager()){

					@Override
					public int getItemPosition(Object object) {
						return SectionsPagerAdapter.POSITION_NONE;// super.getItemPosition(object);
					}
		};
		
		mViewPager = (ViewPager) v.findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		return v;
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = null;
			Bundle args = null;
			switch(position){
				case 0: 
					fragment = new FragmentSearchTab1();
					args = new Bundle();
					args.putInt(FragmentSearchTab1.ARG_SECTION_NUMBER, position + 1);
					fragment.setArguments(args);
					return fragment;					
				case 1: 
					fragment = new FragmentSearchTab2();
					args = new Bundle();
					args.putInt(FragmentSearchTab2.ARG_SECTION_NUMBER, position + 1);
					fragment.setArguments(args);
					return fragment;
				case 2: 
					fragment = new FragmentSearchTab3();
					args = new Bundle();
					args.putInt(FragmentSearchTab3.ARG_SECTION_NUMBER, position + 1);
					fragment.setArguments(args);
					return fragment;
				default: break;
			}
			return null;

		}

		@Override
		public int getCount() { // Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "SAVED";
			case 1:
				return "FAVORITES";
			case 2:
				return "SORT";
			}
			return null;
		}
	}
	
	@SuppressLint("ValidFragment")
	public class FragmentSearchTab1 extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentSearchTab1() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			if(vSearchScreen != null  && vSearchScreen.getParent()!= null)
				((ViewGroup)vSearchScreen.getParent()).removeView(vSearchScreen);
			
			if(vSearchScreen == null)
				SavedSearchesTabbedActivity.this.searchScreen();
			
			return vSearchScreen;
		}
	}
	
	ScrollView svFavorites = null;
	
	@SuppressLint("ValidFragment")
	public class FragmentSearchTab2 extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentSearchTab2() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			if(svFavorites != null  && svFavorites.getParent()!= null)
				((ViewGroup)svFavorites.getParent()).removeView(svFavorites);
			
			if(svFavorites == null)
				svFavorites = SavedSearchesTabbedActivity.this.displayFavorites();
			
			return svFavorites;
		}
	}
	
	@SuppressLint("ValidFragment")
	public class FragmentSearchTab3 extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentSearchTab3() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			//placeholder for now, Will become details pane
			View rootView = inflater.inflate(R.layout.fragment_tabbed_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

    LinearLayout llSearchRegionAndSector;
    LinearLayout llBackgroundRegionSectorSearchResults;
    LinearLayout llBackgroundTab3;
    LinearLayout llBackgroundTab4;
    LinearLayout llBackgroundTab5;
	ImageView ivTabBarItem1;
	ImageView ivTabBarItem2;
	ImageView ivTabBarItem3;
	ImageView ivTabBarItem4;
	ImageView ivTabBarItem5;
	
	Thread threadGetCacheFromDisk = null;
	
	ScrollView vSearchScreen = null;

	public View searchScreen(){

		supportUtility = new AndroidApplicationSupportUtility(this.getActivity());
		
		final LinearLayout llSavedSearches = new LinearLayout(getActivity());
		//llSavedSearches.setBackgroundResource(R.drawable.blackgradient);
		llSavedSearches.setGravity(Gravity.CENTER);
		
		final TextView tvSavedSearches = new TextView(getActivity());
		tvSavedSearches.setTextColor(Color.WHITE);
		tvSavedSearches.setText("Getting Openings From Cache");
		tvSavedSearches.setTextSize(20);
		tvSavedSearches.setGravity(Gravity.CENTER);

		/* Was causing issues with loading did not ever go away
        getActivity().runOnUiThread(new Runnable() {				
			@Override
			public void run() {
			    progress = ProgressDialog.show(getActivity(), "Loading Cached Openings",
			    	    "Please Wait One Moment.", true);
			}
		});*/
        
		threadGetCacheFromDisk = new Thread(){
			@Override
			public void run() {
				super.run();
				
				try {					
				    File fileAllOpeningsCache = new File(getActivity().getFilesDir(), "all_openings_cache.txt"); // Pass getCacheDir()
			        //InputStream in;
					//in = new FileInputStream(fileAllOpeningsCache);
					
				    final Scanner scanner = new Scanner(fileAllOpeningsCache); 
				    scanner.useDelimiter(",");
					
					//final String cachedString = new Scanner(fileAllOpeningsCache).useDelimiter("\\Z").next();
				    
			        getActivity().runOnUiThread(new Runnable() {				
						@Override
						public void run() {				
							llSavedSearches.setGravity(Gravity.LEFT);
							tvSavedSearches.setTextSize(12);
							tvSavedSearches.setGravity(Gravity.LEFT);
							
							//progress.dismiss();
						}
					});
				    
				    String cachedString = "";
				    String cachedString2 = "";
				    
				    int counter = 0;
				    int numRecords = 0;

				    String nextField = "";
				    while(scanner.hasNext()){
				    	nextField = scanner.next();
				    	cachedString =  cachedString + ", " + nextField;
				    	cachedString2 = cachedString + ", " + nextField;
				    	
				    	if(counter == 25){
					    	final String finalNextField = cachedString;

					        getActivity().runOnUiThread(new Runnable() {				
								@Override
								public void run() {
									tvSavedSearches.append(finalNextField + "\n");
									tvSavedSearches.postInvalidate();	
									vSearchScreen.fullScroll(View.FOCUS_DOWN);		
									vSearchScreen.postInvalidate();
								}
							});
					        counter = 0;
							cachedString = "";

							
					        try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
				    	} else{
				    		counter += 1;
				    	}
				    	if (!scanner.hasNext()) {
				    		final String finalNextField = cachedString;
				    		getActivity().runOnUiThread(new Runnable() {				
								@Override
								public void run() {
									tvSavedSearches.append(finalNextField + "\n");
									tvSavedSearches.postInvalidate();	
									vSearchScreen.fullScroll(View.FOCUS_DOWN);		
									vSearchScreen.postInvalidate();
								}
							});
				    	}
				    }
			        	        	
			        try {
						threadGetCacheFromDisk.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}			
		};

		threadGetCacheFromDisk.start();
		llSavedSearches.addView(tvSavedSearches);
		
		ScrollView svSavedSearches = new ScrollView(getActivity());
		
		svSavedSearches.addView(llSavedSearches);
		vSearchScreen = svSavedSearches;
		return svSavedSearches;
	}
	
	Thread threadGetFavorites = null;
	
	public ScrollView displayFavorites(){
		final LinearLayout llfavorites = new LinearLayout(getActivity());
		//llSavedSearches.setBackgroundResource(R.drawable.blackgradient);
		llfavorites.setGravity(Gravity.CENTER);
		
		final TextView tvFavoriteReq_id = new TextView(getActivity());
		tvFavoriteReq_id.setTextColor(Color.WHITE);
		tvFavoriteReq_id.setText("Getting favorites From Cache");
		tvFavoriteReq_id.setTextSize(20);
		tvFavoriteReq_id.setGravity(Gravity.CENTER);
        
        threadGetFavorites = new Thread(){
			@Override
			public void run() {
				super.run();
				
				try {					
				    File favoritesCache = new File(getActivity().getFilesDir(), "all_favorites_cache.txt"); // Pass getCacheDir()
				    final Scanner scanner = new Scanner(favoritesCache); 
				    scanner.useDelimiter(",");
				    
			        getActivity().runOnUiThread(new Runnable() {				
						@Override
						public void run() {				
							llfavorites.setGravity(Gravity.LEFT);
							tvFavoriteReq_id.setTextSize(12);
							tvFavoriteReq_id.setGravity(Gravity.LEFT);
						}
					});
				    
				    String cachedreq_id = "";
				    
				    int counter = 0;

				    String nextField = "";
				    while(scanner.hasNext()){
				    	nextField = scanner.next();
				    	cachedreq_id =  cachedreq_id + "," + nextField;
				    	
				    	if(counter == 25){
					    	final String finalNextField = cachedreq_id;

					        getActivity().runOnUiThread(new Runnable() {				
								@Override
								public void run() {
									tvFavoriteReq_id.append(finalNextField + "\n");
									tvFavoriteReq_id.postInvalidate();	
									svFavorites.fullScroll(View.FOCUS_DOWN);		
									svFavorites.postInvalidate();
								}
							});
					        counter = 0;
							cachedreq_id = "";

					        try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
				    	}
				    	else{
				    		counter += 1;
				    	}
				    	
				    	if (!scanner.hasNext()) {
				    		final String finalNextField = cachedreq_id;
				    		getActivity().runOnUiThread(new Runnable() {				
								@Override
								public void run() {
									tvFavoriteReq_id.append(finalNextField + "\n");
									tvFavoriteReq_id.postInvalidate();	
									svFavorites.fullScroll(View.FOCUS_DOWN);		
									svFavorites.postInvalidate();
								}
							});
				    	}
				    }
			        	        	
			        try {
			        	threadGetFavorites.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}			
		};

		threadGetFavorites.start();
		
		llfavorites.addView(tvFavoriteReq_id);
		
		ScrollView favorites = new ScrollView(getActivity());
		
		favorites.addView(llfavorites);
		svFavorites = favorites;
		return favorites;
	
	}

	

    
	private ImageView [] arrayImageViewsRegions = new ImageView[9];
	private ImageView [] arrayImageViewsSectors = new ImageView[7];
	private int [] arraySelectedRegions = {1,0,0,0,0,0,0,0,0};
	private String [] arrayRegionSlugs = {
			"Lazy Manual Loading", //anywhere
			"africa",//Africa
			"asia",//Asia
			"centralamerica",//Central America and Mexico
			"easteurope",//Eastern Europe and Central Asia
			"northafr",//North Africa and the Middle East
			"pacificislands",//Pacific Island
			"southamerica",//South America
			"caribbean"//The Caribbean
	};
	
	private String [] arraySectorSlugs = {
			"Lazy Manual Loading", //anything
			"agriculture",
			"community+economic+development",
			"education",
			"environment",
			"health",
			"youth+in+development"
	};

	private int totalNumRegionsSelected = 1;
	private int [] arraySelectedSectors = {1,0,0,0,0,0,0};
	private int totalNumSectorsSelected = 1;

	
	AndroidApplicationSupportUtility supportUtility = null;
	//ActionBarDrawerToggle mDrawerToggle = null;
	
    private Integer[] mRegionsThumIds = {
            R.drawable.regionanywhere, R.drawable.regionafrica,
            R.drawable.regionasia, R.drawable.regioncentralamericamexico,
            R.drawable.regioneasterneuropecentralasia, R.drawable.regionnorthafricamiddle,
            R.drawable.regionpacificislands, R.drawable.regionsouthamerica,
            R.drawable.regioncaribbean
    };
    private String[] mRegionsCaptions = {
            "Anywhere\n\n", "Africa\n\n",
            "Asia\n\n", "Central America\nand Mexico\n",
            "Eastern Europe\nand Central Asia\n", "North Africa and\nthe Middle East\n",
            "Pacific Islands\n\n", "South America\n\n",
            "The Caribbean\n\n"
    };
    private Integer[] mSectorThumIds = {
            R.drawable.sectoranything, R.drawable.sectoragriculture,
            R.drawable.sectorcommunity, R.drawable.sectoreducation,
            R.drawable.sectorenvironment, R.drawable.sectorhealth,
            R.drawable.sectoryouth
    };
    private String[] mSectorCaptions = {
            "Anything\n", "Agriculture\n",
            "Community\nEconomic\nDevelopment", "Education\n",
            "Environment\n", "Health\n",
            "Youth in\nDevelopment\n"
    };
    
	ArrayList<OpeningInformation>alOpeningsInformation = new ArrayList<OpeningInformation>();
	TextView tvHTML = null;
	Thread threadGetHTML;
	
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}

}
