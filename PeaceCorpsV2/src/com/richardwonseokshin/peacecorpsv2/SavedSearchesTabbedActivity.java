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
		public int getCount() {
			// Show 3 total pages.
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


	
	
	
	public class FragmentSearchTab1 extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentSearchTab1() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tabbed_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			dummyTextView.append("TAB ONE TEXT");
			
			if(vSearchScreen == null)
				SavedSearchesTabbedActivity.this.searchScreen();
			
			return vSearchScreen;
		}
	}
	
	
	public class FragmentSearchTab2 extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentSearchTab2() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tabbed_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			dummyTextView.append("TAB TWO TEXT");
			
			if(svResultsGlobal == null)
				svResultsGlobal = new ScrollView(getActivity());
			
			return svResultsGlobal;
		}
	}
	
	public class FragmentSearchTab3 extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		public FragmentSearchTab3() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
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
	
	View vSearchScreen = null;

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

        getActivity().runOnUiThread(new Runnable() {				
			@Override
			public void run() {
			    progress = ProgressDialog.show(getActivity(), "Loading Cached Openings",
			    	    "Please Wait One Moment.", true);
			}
		});

        
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
							
							progress.dismiss();
						}
					});
				    
				    String cachedString = "";
				    String cachedString2 = "";
				    
				    int counter = 0;

				    String nextField = "";
				    while(scanner.hasNext()){
				    	nextField = scanner.next();
				    	cachedString =  cachedString + "," + nextField;
				    	cachedString2 = cachedString + "," + nextField;
				    	
				    	if(counter == 25){
					    	final String finalNextField = cachedString;

					        getActivity().runOnUiThread(new Runnable() {				
								@Override
								public void run() {
									tvSavedSearches.append(finalNextField + "\n");
									tvSavedSearches.postInvalidate();	
									svResultsGlobal.fullScroll(View.FOCUS_DOWN);		
									svResultsGlobal.postInvalidate();
								}
							});
					        counter = 0;
							cachedString = "";

					        try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
				    	}
				    	else{
				    		counter += 1;
				    	}
				    }

					
					/*
			        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			        StringBuilder out = new StringBuilder();
			        String line;
			        while ((line = reader.readLine()) != null) {
			            out.append(line);
			        }
			        final String cachedString = out.toString();
			        reader.close();
			        */
			        	        	
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void getHTMLOnComplete(final String result, final boolean writeToCache){	
		
		if(writeToCache){
		    try {
			    File fileAllOpeningsCache = new File(getActivity().getFilesDir(), "all_openings_cache.txt"); // Pass getCacheDir()
			    if(fileAllOpeningsCache.exists()){
			    	fileAllOpeningsCache.delete();
			    }
			    fileAllOpeningsCache.createNewFile();
			    
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileAllOpeningsCache));
				bos.write(result.getBytes());
				bos.flush();
				bos.close();				
			    
		        getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(getActivity().getApplicationContext(), "Saved Openings to Cache", 
								   Toast.LENGTH_LONG).show();						
					}
				});
		        
		        
		        /*
				getActivity().getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, SavedSearchesTabbedActivity.newInstance(), SavedSearchesTabbedActivity.TAG).commit();
		        */

				
			} catch (FileNotFoundException e) {e.printStackTrace();}
		    catch (IOException e) {e.printStackTrace();}
		}
		else{
			alOpeningsInformation.clear();
			
			JSONObject jObject = null;
			try {
				jObject = new JSONObject(result);
				JSONArray jArray = jObject.getJSONArray("results");
				//JSONArray jArray = new JSONArray(result);
				for (int i=0; i < jArray.length(); i++)
				{
				    try {
				    	final OpeningInformation openingInformation = new OpeningInformation();
				    	alOpeningsInformation.add(openingInformation);
				    	
				        JSONObject currentJSONObject = jArray.getJSONObject(i);
				        // Pulling items from the array
				        
				        openingInformation.title = currentJSONObject.getString("title");
				        openingInformation.req_id = currentJSONObject.getString("req_id");
				        openingInformation.country = currentJSONObject.getString("country");
				        openingInformation.region = currentJSONObject.getString("region");
				        openingInformation.sector = currentJSONObject.getString("sector");
				        openingInformation.apply_date = currentJSONObject.getString("apply_date");
				        openingInformation.know_date = currentJSONObject.getString("know_date");
				        openingInformation.staging_start_date = currentJSONObject.getString("staging_start_date");
				        openingInformation.featured = currentJSONObject.getBoolean("featured");
				        openingInformation.project_description = currentJSONObject.getString("project_description");
				        openingInformation.required_skills = currentJSONObject.getString("required_skills");
				        openingInformation.desired_skills = currentJSONObject.getString("desired_skills");
				        openingInformation.language_skills = currentJSONObject.getString("language_skills");
				        openingInformation.language_skills_comments = currentJSONObject.getString("language_skills_comments");
				        openingInformation.volunteers_requested = currentJSONObject.getInt("volunteers_requested");
				        openingInformation.accepts_couples = currentJSONObject.getBoolean("accepts_couples");
				        openingInformation.living_conditions_comments = currentJSONObject.getString("living_conditions_comments");
				        openingInformation.country_medical_considerations = currentJSONObject.getString("country_medical_considerations");
				        openingInformation.country_site_url = currentJSONObject.getString("country_site_url");
				        openingInformation.country_flag_image = currentJSONObject.getString("country_flag_image");
				        openingInformation.opening_url = currentJSONObject.getString("opening_url");
				        
				        getActivity().runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								//Toast.makeText(getActivity().getApplicationContext(), openingInformation.toString(), 
									//	   Toast.LENGTH_LONG).show();							
							}
						});
				    } catch (JSONException e) {
				    }
				}
			} 
			catch (JSONException e) {e.printStackTrace();}					
			
			getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					svResultsGlobal = displayResults();				
					mSectionsPagerAdapter.notifyDataSetChanged();
					mViewPager.setCurrentItem(1);				
				}
			});
		}		
	}

	ScrollView svResultsGlobal = null;
	
	
    public void getHTML(final String urlToRead, final boolean writeToCache) {	  
    	threadGetHTML = new Thread(){
			@Override
			public void run() {
				super.run();
		    	URL url;
		    	HttpURLConnection conn;
		    	BufferedReader rd;
		    	String line;
		    	String result = "";
		    	try {
		    		url = new URL(urlToRead);
		    		conn = (HttpURLConnection) url.openConnection();
		    		conn.setRequestMethod("GET");
		    		
		    		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		         
		    		while ((line = rd.readLine()) != null) {
		    			result += line;
		    		}
		    		
		    		rd.close();
		    	} 
		    	catch (IOException e) {e.printStackTrace();} 
		    	catch (Exception e) {e.printStackTrace();}		    			    	   
		    	
		    	getHTMLOnComplete(result, writeToCache);
		    	
		    	try {
					threadGetHTML.join();
				} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}    		
    	};
    	threadGetHTML.start();
   }
    
    
    public ScrollView displayResults() {
    	final LinearLayout llResults = new LinearLayout(this.getActivity());
    		llResults.setOrientation(LinearLayout.VERTICAL);
    		llResults.setGravity(Gravity.CENTER);
    		
    		
    		for (int i = 0; i<alOpeningsInformation.size(); i++) {
    			LinearLayout llCard = new LinearLayout(getActivity());
    				llCard.setOrientation(LinearLayout.VERTICAL);
    				llCard.setGravity(Gravity.CENTER);
    				llCard.setBackgroundColor(Color.argb(255, 0, 128, 141));
    				llCard.setPadding(10, 10, 10, 10);

    				
    			TextView tvOpeningTitle = new TextView(getActivity());
    				tvOpeningTitle.setGravity(Gravity.CENTER);
    				tvOpeningTitle.setTextSize(14);
    				tvOpeningTitle.setText(alOpeningsInformation.get(i).title);
    				tvOpeningTitle.append("\n" + alOpeningsInformation.get(i).region);
    				tvOpeningTitle.append(" - " + alOpeningsInformation.get(i).country + " - ");
    				tvOpeningTitle.append(alOpeningsInformation.get(i).sector);
    				tvOpeningTitle.setTextColor(Color.argb(255, 0, 128, 141));
    				tvOpeningTitle.setBackgroundColor(Color.argb(255, 225, 222, 199));
    				
    				

    			ImageView ivSector = new ImageView(getActivity());
    			if(alOpeningsInformation.get(i).sector.compareTo("Agriculture") == 0){
    				ivSector.setBackgroundResource(R.drawable.sectoragriculture);
    			}
    			else if(alOpeningsInformation.get(i).sector.compareTo("Community Economic Development") == 0){
    				ivSector.setBackgroundResource(R.drawable.sectorcommunity);
    			}
    			else if(alOpeningsInformation.get(i).sector.compareTo("Education") == 0){
    				ivSector.setBackgroundResource(R.drawable.sectoreducation);
    			}
    			else if(alOpeningsInformation.get(i).sector.compareTo("Health") == 0){
    				ivSector.setBackgroundResource(R.drawable.sectorhealth);
    			}
    			else if(alOpeningsInformation.get(i).sector.compareTo("Youth in Development") == 0){
    				ivSector.setBackgroundResource(R.drawable.sectoryouth);	
    			}
    			else if(alOpeningsInformation.get(i).sector.compareTo("Environment") == 0){
    				ivSector.setBackgroundResource(R.drawable.sectorenvironment);
    			}
    			else{
    				ivSector.setBackgroundResource(R.drawable.sectoranything);	
    			}
    			
    			TextView tvCardDescription = new TextView(getActivity());
    				tvCardDescription.setGravity(Gravity.LEFT);
    				tvCardDescription.setTextSize(12);
    				tvCardDescription.setText("Apply Date: " + alOpeningsInformation.get(i).apply_date + "\n");
    				tvCardDescription.append("Know by: " + alOpeningsInformation.get(i).know_date + "\n\n");
    				tvCardDescription.append("Required Skills: " + alOpeningsInformation.get(i).required_skills + "\n");
    				tvCardDescription.append("Desired Skills: " + alOpeningsInformation.get(i).desired_skills + "\n");
    				tvCardDescription.append("Language Skills: " + alOpeningsInformation.get(i).language_skills + "\n");
    				tvCardDescription.setTextColor(Color.argb(255, 0, 128, 141));
    				tvCardDescription.setBackgroundColor(Color.argb(255, 225, 222, 199));    				
    				
    				
        			View vP = new View(getActivity());
        			vP.setBackgroundColor(Color.argb(0,0,0,0));
        			llResults.addView(vP, 10, 10);
        			
    				llCard.addView(tvOpeningTitle, (int)(supportUtility.pointScreenDimensions.x*.9), (int)(supportUtility.pointScreenDimensions.x*.9*.2));
    				vP = new View(getActivity());
        			vP.setBackgroundColor(Color.argb(0,0,0,0));
    				llCard.addView(vP, 10, 10);
    				llCard.addView(ivSector,  (int)(supportUtility.pointScreenDimensions.x*.9*.2),  (int)(supportUtility.pointScreenDimensions.x*.9*.2));
    				vP = new View(getActivity());
        			vP.setBackgroundColor(Color.argb(0,0,0,0));
    				llCard.addView(vP, 10, 10);
    				llCard.addView(tvCardDescription);
    				
    				vP = new View(getActivity());
        			vP.setBackgroundColor(Color.argb(0,0,0,0));
        			llResults.addView(vP, 10, 10);
    				
    			llResults.addView(llCard);    			    			
    		}
    	final ScrollView svResults = new ScrollView(this.getActivity());
    	svResults.addView(llResults);
    	svResultsGlobal = svResults;
    	return svResults;
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
