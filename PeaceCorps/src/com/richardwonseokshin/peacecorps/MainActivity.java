package com.richardwonseokshin.peacecorps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnItemClickListener{
	private DrawerLayout drawerLayout;
	private ListView listView;
	private String [] arrayMenuItems;
	private ActionBarDrawerToggle drawerListener;
	
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
	ActionBarDrawerToggle mDrawerToggle = null;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			//requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getSupportActionBar().hide();
		
		supportUtility = new AndroidApplicationSupportUtility(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		final LinearLayout flMainScreen = (LinearLayout)drawerLayout.getChildAt(0);
		final ImageView ivSplash = new ImageView(this);
			ivSplash.setBackgroundResource(R.drawable.splashscreen);
			flMainScreen.addView(ivSplash);
			

		final LinearLayout llSearchRegionAndSector = new LinearLayout(this);
			llSearchRegionAndSector.setOrientation(LinearLayout.VERTICAL);
			llSearchRegionAndSector.setBackgroundResource(R.drawable.blackgradient);
			
		LinearLayout llTabBar = new LinearLayout(this);
			llTabBar.setBackgroundColor(Color.BLACK);
			llTabBar.setOrientation(LinearLayout.HORIZONTAL);
			final ImageView ivTabBarItem1 = new ImageView(this);
			final ImageView ivTabBarItem2 = new ImageView(this);
			final ImageView ivTabBarItem3 = new ImageView(this);
			final ImageView ivTabBarItem4 = new ImageView(this);
			final ImageView ivTabBarItem5 = new ImageView(this);
			ivTabBarItem1.setBackgroundColor(Color.BLUE);
			ivTabBarItem2.setBackgroundColor(Color.CYAN);
			ivTabBarItem3.setBackgroundColor(Color.GREEN);
			ivTabBarItem4.setBackgroundColor(Color.YELLOW);
			ivTabBarItem5.setBackgroundColor(Color.MAGENTA);
			llTabBar.addView(ivTabBarItem1, supportUtility.pointScreenDimensions.x/5, supportUtility.getActionBarHeight());
			llTabBar.addView(ivTabBarItem2, supportUtility.pointScreenDimensions.x/5, supportUtility.getActionBarHeight());
			llTabBar.addView(ivTabBarItem3, supportUtility.pointScreenDimensions.x/5, supportUtility.getActionBarHeight());
			llTabBar.addView(ivTabBarItem4, supportUtility.pointScreenDimensions.x/5, supportUtility.getActionBarHeight());
			llTabBar.addView(ivTabBarItem5, supportUtility.pointScreenDimensions.x/5, supportUtility.getActionBarHeight());
			llSearchRegionAndSector.addView(llTabBar, supportUtility.pointScreenDimensions.x, supportUtility.getActionBarHeight());
			ivTabBarItem1.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN: 
							ivTabBarItem1.setBackgroundColor(Color.RED);
							ivTabBarItem1.postInvalidate();
							break;
						case MotionEvent.ACTION_MOVE: break;
						case MotionEvent.ACTION_UP: 
							ivTabBarItem1.setBackgroundColor(Color.BLUE);
							ivTabBarItem1.postInvalidate();
						    break;
					}
					return true;
				}
			});
			ivTabBarItem2.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN: 
							ivTabBarItem2.setBackgroundColor(Color.RED);
							ivTabBarItem2.postInvalidate();
							break;
						case MotionEvent.ACTION_MOVE: break;
						case MotionEvent.ACTION_UP: 
							ivTabBarItem2.setBackgroundColor(Color.CYAN);
							ivTabBarItem2.postInvalidate();
						    break;
					}
					return true;
				}
			});
			ivTabBarItem3.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN: 
							ivTabBarItem3.setBackgroundColor(Color.RED);
							ivTabBarItem3.postInvalidate();
							break;
						case MotionEvent.ACTION_MOVE: break;
						case MotionEvent.ACTION_UP: 
							ivTabBarItem3.setBackgroundColor(Color.GREEN);
							ivTabBarItem3.postInvalidate();
						    break;
					}
					return true;
				}
			});
			ivTabBarItem4.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN: 
							ivTabBarItem4.setBackgroundColor(Color.RED);
							ivTabBarItem4.postInvalidate();
							break;
						case MotionEvent.ACTION_MOVE: break;
						case MotionEvent.ACTION_UP: 
							ivTabBarItem4.setBackgroundColor(Color.YELLOW);
							ivTabBarItem4.postInvalidate();
						    break;
					}
					return true;
				}
			});
			ivTabBarItem5.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch(event.getAction()){
						case MotionEvent.ACTION_DOWN: 
							ivTabBarItem5.setBackgroundColor(Color.RED);
							ivTabBarItem5.postInvalidate();
							break;
						case MotionEvent.ACTION_MOVE: break;
						case MotionEvent.ACTION_UP: 
							ivTabBarItem5.setBackgroundColor(Color.MAGENTA);
							ivTabBarItem5.postInvalidate();
						    break;
					}
					return true;
				}
			});
		    
		    //llSearchRegionAndSector
			
	    final LinearLayout llRegions = new LinearLayout(this);
	    	llRegions.setOrientation(LinearLayout.HORIZONTAL);
	    	llRegions.setPadding(0, 8, 0, 8);
	    	View vPadding = new View(this);
    		vPadding.setBackgroundColor(Color.argb(0,0,0,0));
    		llRegions.addView(vPadding,8,supportUtility.pointScreenDimensions.x/4);
	    for(int i = 0; i < 9; i++){
	    	LinearLayout llRegionItem = new LinearLayout(this);
	    		llRegionItem.setBackgroundResource(mRegionsThumIds[i]);
	    	TextView tvRegionItemCaption = new TextView(this);
	    		tvRegionItemCaption.setText(mRegionsCaptions[i]);
	    		tvRegionItemCaption.setTextColor(Color.WHITE);
	    		tvRegionItemCaption.setTextSize(10);
	    		tvRegionItemCaption.setGravity(Gravity.CENTER | Gravity.BOTTOM);
	    	final ImageView ivRegionItemHighlight = new ImageView(this);
	    		arrayImageViewsRegions[i] = ivRegionItemHighlight;
	    		ivRegionItemHighlight.setBackgroundColor(Color.argb(0,0,0,0));
	    			if(i == 0){
	    				ivRegionItemHighlight.setBackgroundColor(Color.argb(150,120,0,0));
	    			}
    			final int index = i;
	    		ivRegionItemHighlight.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(arraySelectedRegions[index] == 0){
							arraySelectedRegions[index] = 1;
							totalNumRegionsSelected += 1;
		    				ivRegionItemHighlight.setBackgroundColor(Color.argb(150,120,0,0));
		    				
							if(index == 0){
								totalNumRegionsSelected = 1;
								for(int j = 1; j<9; j++){
									arraySelectedRegions[j] = 0;
						    		arrayImageViewsRegions[j].setBackgroundColor(Color.argb(0,0,0,0));
								}
							}
							else{
								if(arraySelectedRegions[0] == 1){
									arraySelectedRegions[0] = 0;
									totalNumRegionsSelected -= 1;
						    		arrayImageViewsRegions[0].setBackgroundColor(Color.argb(0,0,0,0));
								}
							}
						}
						else{
							if(totalNumRegionsSelected > 1){
								arraySelectedRegions[index] = 0;
								totalNumRegionsSelected -= 1;
					    		ivRegionItemHighlight.setBackgroundColor(Color.argb(0,0,0,0));
							}
							

						}	
						ivRegionItemHighlight.postInvalidate();
					}
				});	    		
	    	llRegionItem.addView(tvRegionItemCaption, supportUtility.pointScreenDimensions.x/4, supportUtility.pointScreenDimensions.x/4);
	    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(supportUtility.pointScreenDimensions.x/4, supportUtility.pointScreenDimensions.x/4);
	    		params.leftMargin = -supportUtility.pointScreenDimensions.x/4;
	    	llRegionItem.addView(ivRegionItemHighlight, params);
	    	llRegions.addView(llRegionItem,supportUtility.pointScreenDimensions.x/4,supportUtility.pointScreenDimensions.x/4);
	    	vPadding = new View(this);
	    		vPadding.setBackgroundColor(Color.argb(0,0,0,0));
	    	llRegions.addView(vPadding,8,supportUtility.pointScreenDimensions.x/4);
	    }
	    final HorizontalScrollView hsvRegions = new HorizontalScrollView(this);
	    hsvRegions.addView(llRegions);//, 9*16 + 9*supportUtility.pointScreenDimensions.x/4, 16 + supportUtility.pointScreenDimensions.x/4);
	    

    final LinearLayout llSectors = new LinearLayout(this);
    	llSectors.setOrientation(LinearLayout.HORIZONTAL);
    	//llSectors.setPadding(0, 8, 0, 8);
    	View vPadding2 = new View(this);
		vPadding2.setBackgroundColor(Color.argb(0,0,0,0));
		llSectors.addView(vPadding2,8,supportUtility.pointScreenDimensions.x/4);
    for(int i = 0; i < 7; i++){
    	LinearLayout llSectorItem = new LinearLayout(this);
    		llSectorItem.setBackgroundResource(mSectorThumIds[i]);
    	TextView tvSectorItemCaption = new TextView(this);
    		tvSectorItemCaption.setText(mSectorCaptions[i]);
    		tvSectorItemCaption.setTextSize(10);
    		tvSectorItemCaption.setTextColor(Color.WHITE);	
    		tvSectorItemCaption.setGravity(Gravity.CENTER | Gravity.BOTTOM);
    	final ImageView ivSectorItemHighlight = new ImageView(this);
			arrayImageViewsSectors[i] = ivSectorItemHighlight;
    		ivSectorItemHighlight.setBackgroundColor(Color.argb(0,0,0,0));
				if(i == 0){
					ivSectorItemHighlight.setBackgroundColor(Color.argb(150,120,0,0));
				}
			final int index = i;
			ivSectorItemHighlight.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(arraySelectedSectors[index] == 0){
						arraySelectedSectors[index] = 1;
						totalNumSectorsSelected += 1;
						ivSectorItemHighlight.setBackgroundColor(Color.argb(150,120,0,0));
						
						if(index == 0){
							totalNumSectorsSelected = 1;
							for(int j = 1; j<7; j++){
								arraySelectedSectors[j] = 0;
								arrayImageViewsSectors[j].setBackgroundColor(Color.argb(0,0,0,0));
							}
						}		
						else{
							if(arraySelectedSectors[0] == 1){
								arraySelectedSectors[0] = 0;
								totalNumSectorsSelected -= 1;
					    		arrayImageViewsSectors[0].setBackgroundColor(Color.argb(0,0,0,0));
							}
						}
					}
					else{
						if(totalNumSectorsSelected > 1){
							arraySelectedSectors[index] = 0;
							totalNumSectorsSelected -= 1;
							ivSectorItemHighlight.setBackgroundColor(Color.argb(0,0,0,0));
						}
						

					}	
					ivSectorItemHighlight.postInvalidate();
				}
			});
    	llSectorItem.addView(tvSectorItemCaption, supportUtility.pointScreenDimensions.x/4, supportUtility.pointScreenDimensions.x/4);
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(supportUtility.pointScreenDimensions.x/4, supportUtility.pointScreenDimensions.x/4);
    		params.leftMargin = -supportUtility.pointScreenDimensions.x/4;
    	llSectorItem.addView(ivSectorItemHighlight, params);
    	llSectors.addView(llSectorItem,supportUtility.pointScreenDimensions.x/4,supportUtility.pointScreenDimensions.x/4);
    	vPadding2 = new View(this);
    		vPadding2.setBackgroundColor(Color.argb(0,0,0,0));
    	llSectors.addView(vPadding2,8,supportUtility.pointScreenDimensions.x/4);
    }
    final HorizontalScrollView hsvSectors = new HorizontalScrollView(this);
    hsvSectors.addView(llSectors);//, 9*16 + 9*supportUtility.pointScreenDimensions.x/4, 16 + supportUtility.pointScreenDimensions.x/4);
    
    hsvSectors.setScrollbarFadingEnabled(false);
    hsvRegions.setScrollbarFadingEnabled(false);

		TextView tvHeader = new TextView(this);
		tvHeader.setText("Volunteer Openings");
		tvHeader.setGravity(Gravity.CENTER);
		tvHeader.setBackgroundColor(Color.argb(0,0,0,0));
		tvHeader.setTextSize(16);
		tvHeader.setTextColor(Color.WHITE);
	llSearchRegionAndSector.addView(tvHeader, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/16);
		TextView tvRegionHeader = new TextView(this);
		tvRegionHeader.setText("Select Region(s)");
		tvRegionHeader.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		tvRegionHeader.setBackgroundColor(Color.argb(0,0,0,0));
		tvRegionHeader.setTextSize(12);
		tvRegionHeader.setTextColor(Color.CYAN);
	llSearchRegionAndSector.addView(tvRegionHeader, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/16);
    llSearchRegionAndSector.addView(hsvRegions, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4);//, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4+16);
		TextView tvSectorHeader = new TextView(this);
		tvSectorHeader.setText("Select Sector(s)");
		tvSectorHeader.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		tvSectorHeader.setBackgroundColor(Color.argb(0,0,0,0));
		tvSectorHeader.setTextSize(12);
		tvSectorHeader.setTextColor(Color.GREEN);
	llSearchRegionAndSector.addView(tvSectorHeader, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/16);
    llSearchRegionAndSector.addView(hsvSectors, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4);//, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4+16);
    
    View vPaddingSearchButtong = new View(this);
    vPaddingSearchButtong.setBackgroundColor(Color.argb(0,0,0,0));
    llSearchRegionAndSector.addView(vPaddingSearchButtong, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/32);
    
    final TextView tvSearchButton = new TextView(this);
    tvSearchButton.setText("Search Opportunities >");
    tvSearchButton.setTextColor(Color.WHITE);
    tvSearchButton.setTextSize(16);
    tvSearchButton.setGravity(Gravity.CENTER);
    tvSearchButton.setBackgroundColor(Color.argb(255,204,102,51));
    tvSearchButton.setOnTouchListener(new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN: 
				    tvSearchButton.setBackgroundColor(Color.argb(255,164,62,11));
				    tvSearchButton.postInvalidate();
					break;
				case MotionEvent.ACTION_MOVE: break;
				case MotionEvent.ACTION_UP: 
				    tvSearchButton.setBackgroundColor(Color.argb(255,204,102,51));
				    tvSearchButton.postInvalidate();
				    
				    // Example http get request for regions: http://www.peacecorps.gov/api/v1/geography/regions/?region=easteurope&region=africa
				    // Example http get request for openings: http://www.peacecorps.gov/api/v1/openings/?region=asia&region=africa&sector=education
				    //http://www.peacecorps.gov/api/v1/openings/?region=asia&sector=youth%20in%20development
				    String htmlQueryBaseURL = "http://www.peacecorps.gov/api/v1/openings/?";
				    String htmlQueryURL = ""; 
				    int numRegionsSelected = 0;
				    for(int i = 1; i < 9; i++){
				    	if(arraySelectedRegions[0] == 1){//user selected "anywhere", combine all other regions
				    		if(i == 1){
				    			htmlQueryURL = htmlQueryURL + "region=" + arrayRegionSlugs[i];
				    		}
				    		else{
				    			htmlQueryURL = htmlQueryURL + "&region=" + arrayRegionSlugs[i];
				    		}
				    	}
				    	else{//user selected 1 or more regions, but did not select "anywhere"
					    	if(arraySelectedRegions[i] == 1){
					    		if(numRegionsSelected == 0){
					    			htmlQueryURL = htmlQueryURL + "region=" + arrayRegionSlugs[i];
					    		}
					    		else{
					    			htmlQueryURL = htmlQueryURL + "&region=" + arrayRegionSlugs[i];
					    		}
				    			numRegionsSelected += 1;
					    	}
				    	}
				    }
				    
				    for(int i = 1; i < 7; i++){
				    	if(arraySelectedSectors[0] == 1){//user selected "anywhere", combine all other regions
				    		htmlQueryURL = htmlQueryURL + "&sector=" + arraySectorSlugs[i];
				    	}
				    	else if(arraySelectedSectors[i] == 1){//user selected 1 or more regions, but did not select "anywhere"
					    	htmlQueryURL = htmlQueryURL + "&sector=" + arraySectorSlugs[i];
				    	}
				    }
				    
				    //http://www.peacecorps.gov/api/v1/geography/countries/?sector=health&region=asia

				    htmlQueryURL += "&current=true";
				    
				    /*
					try {
						htmlQueryURL = URLEncoder.encode(htmlQueryURL, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					*/
					
					htmlQueryURL = htmlQueryBaseURL + htmlQueryURL;
				
				    getHTML(htmlQueryURL);
				    break;
			}
			return true;
		}
	});
    
    llSearchRegionAndSector.addView(tvSearchButton, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/8);

		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				getSupportActionBar().show();
				flMainScreen.removeAllViews();
				flMainScreen.addView(llSearchRegionAndSector, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.y - supportUtility.getActionBarHeight());
				flMainScreen.postInvalidate();
			}
		}, 1500);
		
		tvHTML = new TextView(this);
		tvHTML.setBackgroundColor(Color.YELLOW);
		tvHTML.setTextColor(Color.BLACK);
		LinearLayout llHTML = new LinearLayout(this);
		llHTML.addView(tvHTML);//, supportUtility.pointScreenDimensions.x, (int) (supportUtility.pointScreenDimensions.y - supportUtility.getActionBarHeight()*2 - 3/16*supportUtility.pointScreenDimensions.x/4 - 2.5 * supportUtility.pointScreenDimensions.x/4  - 1/32 * supportUtility.pointScreenDimensions.x/4));
		ScrollView svHTML = new ScrollView(this);
		svHTML.addView(llHTML);
		svHTML.setFillViewport(true);
		llSearchRegionAndSector.addView(svHTML, supportUtility.pointScreenDimensions.x, (int) (supportUtility.pointScreenDimensions.y - supportUtility.getActionBarHeight()*2 - 3/16*supportUtility.pointScreenDimensions.x/4 - 2.5 * supportUtility.pointScreenDimensions.x/4  - 1/32 * supportUtility.pointScreenDimensions.x/4 - supportUtility.getSoftbuttonsbarHeight()));
		//setContentView(R.layout.activity_main);
        
        arrayMenuItems = getResources().getStringArray(R.array.menuitems);
                
        	mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){

				@Override
				public void onDrawerClosed(View drawerView) {
					super.onDrawerClosed(drawerView);
					Toast.makeText(MainActivity.this, "Drawer Closed", Toast.LENGTH_SHORT).show();

				}

				@Override
				public void onDrawerOpened(View drawerView) {
					super.onDrawerOpened(drawerView);
					Toast.makeText(MainActivity.this, "Drawer Opened", Toast.LENGTH_SHORT).show();
				}
        		
        	};
        	drawerLayout.setDrawerListener(mDrawerToggle);
        listView = (ListView)findViewById(R.id.drawerList);
        	listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayMenuItems));
        	listView.setOnItemClickListener(this);
        
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        
        //supportUtility = new AndroidApplicationSupportUtility(this);
        
        //supportUtility.setFullScreenPortrait();
        /*
        DrawerLayout dlSideMenu = new DrawerLayout(this);
        LinearLayout llContentScreen = new LinearLayout(this);
        	llContentScreen.setBackgroundColor(Color.RED);
    		llContentScreen.setOrientation(LinearLayout.VERTICAL);
        ListView lvSideMenu = new ListView(this);
        	
        	DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(240, LinearLayout.LayoutParams.MATCH_PARENT);
        		params.gravity=Gravity.START;
        		lvSideMenu.setLayoutParams(params);
            
        	String[] sideMenuItems= new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sideMenuItems);
            	lvSideMenu.setAdapter(adapter); 

        
        dlSideMenu.addView(llContentScreen, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        dlSideMenu.addView(lvSideMenu);
        
        
        
        
        mDrawerToggle = new ActionBarDrawerToggle(this, dlSideMenu, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        dlSideMenu.setDrawerListener(mDrawerToggle);
        
        setContentView(dlSideMenu);
        */
	}

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
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    //mDrawerToggle.onConfigurationChanged(newConfig);
	}

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
		getSupportActionBar().setTitle(title);
	}
	
	ArrayList<OpeningInformation>alOpeningsInformation = new ArrayList<OpeningInformation>();
	TextView tvHTML = null;
	public void getHTMLOnComplete(final String result){	
		
		alOpeningsInformation.clear();
		
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(result);
			JSONArray jArray = jObject.getJSONArray("results");
			//JSONArray jArray = new JSONArray(result);
			for (int i=0; i < jArray.length(); i++)
			{
			    try {
			    	OpeningInformation openingInformation = new OpeningInformation();
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
			    } catch (JSONException e) {
			    }
			}
		} 
		catch (JSONException e) {e.printStackTrace();}
		
		
		runOnUiThread(new Runnable() {		
			@Override
			public void run() {
				String openingInformation = "Total Number of Openings: " + alOpeningsInformation.size() + "\n";
				for(int i = 0; i < alOpeningsInformation.size(); i++){
					openingInformation =openingInformation + "Opening #: " + i + "\n";
					openingInformation = openingInformation + alOpeningsInformation.get(i).toString() + "\n";
					openingInformation += "\n\n-----------------\n\n";
				}
				tvHTML.setText(openingInformation);		
				tvHTML.postInvalidate();
			}
		});
		
		
		/*
		runOnUiThread(new Runnable() {		
			@Override
			public void run() {
				tvHTML.setText(result);		
				tvHTML.postInvalidate();
			}
		});
		*/
	}

	Thread threadGetHTML;
    public void getHTML(final String urlToRead) {	  
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
		    	
		    	getHTMLOnComplete(result);
		    	
		    	try {
					threadGetHTML.join();
				} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}    		
    	};
    	threadGetHTML.start();
   }
    

}
