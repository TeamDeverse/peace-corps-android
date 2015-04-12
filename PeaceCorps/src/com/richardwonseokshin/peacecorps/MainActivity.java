package com.richardwonseokshin.peacecorps;

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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnItemClickListener{
	private DrawerLayout drawerLayout;
	private ListView listView;
	private String [] arrayMenuItems;
	private ActionBarDrawerToggle drawerListener;
	
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
	    	ImageView ivRegionItemHighlight = new ImageView(this);
	    		ivRegionItemHighlight.setBackgroundColor(Color.argb(0,0,0,0));
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
    	ImageView ivSectorItemHighlight = new ImageView(this);
    		ivSectorItemHighlight.setBackgroundColor(Color.argb(0,0,0,0));
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
	llSearchRegionAndSector.addView(tvHeader);
		TextView tvRegionHeader = new TextView(this);
		tvRegionHeader.setText("Select Region(s)");
		tvRegionHeader.setGravity(Gravity.LEFT);
		tvRegionHeader.setBackgroundColor(Color.argb(0,0,0,0));
		tvRegionHeader.setTextSize(12);
		tvRegionHeader.setTextColor(Color.CYAN);
	llSearchRegionAndSector.addView(tvRegionHeader);
    llSearchRegionAndSector.addView(hsvRegions);//, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4+16);
		TextView tvSectorHeader = new TextView(this);
		tvSectorHeader.setText("Select Sector(s)");
		tvSectorHeader.setGravity(Gravity.LEFT);
		tvSectorHeader.setBackgroundColor(Color.argb(0,0,0,0));
		tvSectorHeader.setTextSize(12);
		tvSectorHeader.setTextColor(Color.GREEN);
	llSearchRegionAndSector.addView(tvSectorHeader);
    llSearchRegionAndSector.addView(hsvSectors);//, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4+16);

		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				getSupportActionBar().show();
				flMainScreen.removeAllViews();
				flMainScreen.addView(llSearchRegionAndSector, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.y - supportUtility.getActionBarHeight());
				flMainScreen.postInvalidate();
			}
		}, 1500);
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
        	listView.setAdapter(new ArrayAdapter<String>(this, R.layout.menuitem, arrayMenuItems));
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
	
	

}
