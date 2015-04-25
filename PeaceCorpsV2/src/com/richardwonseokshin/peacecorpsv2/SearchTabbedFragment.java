package com.richardwonseokshin.peacecorpsv2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchTabbedFragment extends Fragment {

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
 
 public static final String TAG = SearchTabbedFragment.class.getSimpleName();
 
 /**
  * The {@link ViewPager} that will host the section contents.
  */
 ViewPager mViewPager;

 
 public static SearchTabbedFragment newInstance() {
  return new SearchTabbedFragment();
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
    return "SEARCH";
   case 1:
    return "RESULTS";
   case 2:
    return "DETAILS";
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
   
   
   
   
   return SearchTabbedFragment.this.searchScreen();
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
   dummyTextView.append("TAB THREE TEXT");
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

 public View searchScreen(){
  supportUtility = new AndroidApplicationSupportUtility(this.getActivity());
  
 LinearLayout llSearchRegionAndSector = new LinearLayout(this.getActivity());
  llSearchRegionAndSector.setOrientation(LinearLayout.VERTICAL);
  llSearchRegionAndSector.setBackgroundResource(R.drawable.blackgradient);  
  
    final LinearLayout llRegions = new LinearLayout(this.getActivity());
     llRegions.setOrientation(LinearLayout.HORIZONTAL);
     llRegions.setPadding(0, 8, 0, 8);
     View vPadding = new View(this.getActivity());
  vPadding.setBackgroundColor(Color.argb(0,0,0,0));
  llRegions.addView(vPadding,8,supportUtility.pointScreenDimensions.x/4);
    for(int i = 0; i < 9; i++){
     LinearLayout llRegionItem = new LinearLayout(this.getActivity());
      llRegionItem.setBackgroundResource(mRegionsThumIds[i]);
     TextView tvRegionItemCaption = new TextView(this.getActivity());
      tvRegionItemCaption.setText(mRegionsCaptions[i]);
      tvRegionItemCaption.setTextColor(Color.WHITE);
      tvRegionItemCaption.setTextSize(10);
      tvRegionItemCaption.setGravity(Gravity.CENTER | Gravity.BOTTOM);
     final ImageView ivRegionItemHighlight = new ImageView(this.getActivity());
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
     vPadding = new View(this.getActivity());
      vPadding.setBackgroundColor(Color.argb(0,0,0,0));
     llRegions.addView(vPadding,8,supportUtility.pointScreenDimensions.x/4);
    }
    final HorizontalScrollView hsvRegions = new HorizontalScrollView(this.getActivity());
    hsvRegions.addView(llRegions);//, 9*16 + 9*supportUtility.pointScreenDimensions.x/4, 16 + supportUtility.pointScreenDimensions.x/4);
    

final LinearLayout llSectors = new LinearLayout(this.getActivity());
 llSectors.setOrientation(LinearLayout.HORIZONTAL);
 //llSectors.setPadding(0, 8, 0, 8);
 View vPadding2 = new View(this.getActivity());
 vPadding2.setBackgroundColor(Color.argb(0,0,0,0));
 llSectors.addView(vPadding2,8,supportUtility.pointScreenDimensions.x/4);
for(int i = 0; i < 7; i++){
 LinearLayout llSectorItem = new LinearLayout(this.getActivity());
  llSectorItem.setBackgroundResource(mSectorThumIds[i]);
 TextView tvSectorItemCaption = new TextView(this.getActivity());
  tvSectorItemCaption.setText(mSectorCaptions[i]);
  tvSectorItemCaption.setTextSize(10);
  tvSectorItemCaption.setTextColor(Color.WHITE); 
  tvSectorItemCaption.setGravity(Gravity.CENTER | Gravity.BOTTOM);
 final ImageView ivSectorItemHighlight = new ImageView(this.getActivity());
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
 vPadding2 = new View(this.getActivity());
  vPadding2.setBackgroundColor(Color.argb(0,0,0,0));
 llSectors.addView(vPadding2,8,supportUtility.pointScreenDimensions.x/4);
}
final HorizontalScrollView hsvSectors = new HorizontalScrollView(this.getActivity());
hsvSectors.addView(llSectors);//, 9*16 + 9*supportUtility.pointScreenDimensions.x/4, 16 + supportUtility.pointScreenDimensions.x/4);

hsvSectors.setScrollbarFadingEnabled(false);
hsvRegions.setScrollbarFadingEnabled(false);

 TextView tvHeader = new TextView(this.getActivity());
 tvHeader.setText("Volunteer Openings");
 tvHeader.setGravity(Gravity.CENTER);
 tvHeader.setBackgroundColor(Color.argb(0,0,0,0));
 tvHeader.setTextSize(16);
 tvHeader.setTextColor(Color.WHITE);
llSearchRegionAndSector.addView(tvHeader, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/16);
 TextView tvRegionHeader = new TextView(this.getActivity());
 tvRegionHeader.setText("Select Region(s)");
 tvRegionHeader.setGravity(Gravity.LEFT | Gravity.BOTTOM);
 tvRegionHeader.setBackgroundColor(Color.argb(0,0,0,0));
 tvRegionHeader.setTextSize(12);
 tvRegionHeader.setTextColor(Color.CYAN);
llSearchRegionAndSector.addView(tvRegionHeader, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/16);
llSearchRegionAndSector.addView(hsvRegions, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4);//, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4+16);
 TextView tvSectorHeader = new TextView(this.getActivity());
 tvSectorHeader.setText("Select Sector(s)");
 tvSectorHeader.setGravity(Gravity.LEFT | Gravity.BOTTOM);
 tvSectorHeader.setBackgroundColor(Color.argb(0,0,0,0));
 tvSectorHeader.setTextSize(12);
 tvSectorHeader.setTextColor(Color.GREEN);
llSearchRegionAndSector.addView(tvSectorHeader, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/16);
llSearchRegionAndSector.addView(hsvSectors, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4);//, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/4+16);

View vPaddingSearchButtong = new View(this.getActivity());
vPaddingSearchButtong.setBackgroundColor(Color.argb(0,0,0,0));
llSearchRegionAndSector.addView(vPaddingSearchButtong, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/32);

final TextView tvSearchButton = new TextView(this.getActivity());
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
       
       progress = ProgressDialog.show(getActivity(), "Loading",
            "Please Wait One Moment.", true);
       
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
   
       getHTML(htmlQueryURL, false);  
       
       break;
  }
  return true;
 }
});

llSearchRegionAndSector.addView(tvSearchButton, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/8);


//  if(isOnline()){
//getHTML("http://www.peacecorps.gov/api/v1/openings/", true);   
//}


View vPaddingCache = new View(this.getActivity());
vPaddingCache.setBackgroundColor(Color.argb(0,0,0,0));
llSearchRegionAndSector.addView(vPaddingCache, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/32);

final TextView tvCacheButton = new TextView(this.getActivity());
tvCacheButton.setText("Save Openings To Phone >");
tvCacheButton.setTextColor(Color.WHITE);
tvCacheButton.setTextSize(16);
tvCacheButton.setGravity(Gravity.CENTER);
tvCacheButton.setBackgroundColor(Color.argb(255,204,102,51));
tvCacheButton.setOnTouchListener(new OnTouchListener() {
 
 @Override
 public boolean onTouch(View v, MotionEvent event) {
  switch(event.getAction()){
   case MotionEvent.ACTION_DOWN: 
    tvCacheButton.setBackgroundColor(Color.argb(255,164,62,11));
    tvCacheButton.postInvalidate();
    break;
   case MotionEvent.ACTION_MOVE: break;
   case MotionEvent.ACTION_UP: 
    tvCacheButton.setBackgroundColor(Color.argb(255,204,102,51));
    tvCacheButton.postInvalidate();
       

       
    if(isOnline()){
        progress = ProgressDialog.show(getActivity(), "Loading",
             "Please Wait One Moment.", true);
        
     getHTML("http://www.peacecorps.gov/api/v1/openings/", true); 
     
    } 
    else{
     Toast.makeText(getActivity().getApplicationContext(), "Pleace Check Internet Connection",
          Toast.LENGTH_LONG).show();
    }
    
    
       break;
  }
  return true;
 }
});

llSearchRegionAndSector.addView(tvCacheButton, supportUtility.pointScreenDimensions.x, supportUtility.pointScreenDimensions.x/8);








//

for(int i = 0; i < 9; i++){
 if(arraySelectedRegions[i] == 1){
  arrayImageViewsRegions[i].setBackgroundColor(Color.argb(150,120,0,0));
 }
 else{
  arrayImageViewsRegions[i].setBackgroundColor(Color.argb(0,0,0,0));
 }
}

for(int i = 0; i < 7; i++){
 if(arraySelectedSectors[i] == 1){
  arrayImageViewsSectors[i].setBackgroundColor(Color.argb(150,120,0,0));
 }
 else{
  arrayImageViewsSectors[i].setBackgroundColor(Color.argb(0,0,0,0));
 }
}




return llSearchRegionAndSector;

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
      
      progress.dismiss();

     }
    });
          
          
          

    
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
         //    Toast.LENGTH_LONG).show();       
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
     
     progress.dismiss();
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
            llCard.setOrientation(LinearLayout.HORIZONTAL);
            llCard.setGravity(Gravity.LEFT);
            llCard.setBackgroundColor(Color.argb(225, 225, 222, 199));
            llCard.setPadding(10, 10, 10, 10);

            LinearLayout llImage = new LinearLayout(getActivity());
            llImage.setOrientation(LinearLayout.VERTICAL);
            llImage.setGravity(Gravity.TOP);
            llImage.setPadding(5, 5, 5, 5);
            llImage.setBackgroundColor(Color.argb(0, 225, 222, 199));

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
            llImage.addView(ivSector, (int)(supportUtility.pointScreenDimensions.x*.15),  (int)(supportUtility.pointScreenDimensions.x*.15));

            TextView tvOpeningTitle = new TextView(getActivity());
            tvOpeningTitle.setGravity(Gravity.LEFT);
            tvOpeningTitle.setTextSize(14);
            tvOpeningTitle.setText(Html.fromHtml("<b>" + alOpeningsInformation.get(i).title + "</b><br>"));
            tvOpeningTitle.append(Html.fromHtml("<i>" + alOpeningsInformation.get(i).sector + "</i><br><br><br>"));
            String country = alOpeningsInformation.get(i).country.substring(0, 1).toUpperCase() + alOpeningsInformation.get(i).country.substring(1);
            tvOpeningTitle.append(Html.fromHtml("Country: <b>" + country + "</b><br><br>"));
            tvOpeningTitle.append(Html.fromHtml("Region: <b>" + formatRegionResult(alOpeningsInformation.get(i).region) + "</b><br><br>"));
            tvOpeningTitle.append(Html.fromHtml("Departs By: <b>" + alOpeningsInformation.get(i).staging_start_date + "</b><br>"));
            tvOpeningTitle.setTextColor(Color.argb(255, 0, 128, 141));
            tvOpeningTitle.setBackgroundColor(Color.argb(0, 225, 222, 199));

            View vP = new View(getActivity());
            vP.setBackgroundColor(Color.argb(0,0,0,0));
            llCard.addView(vP, 10, 10);
            llCard.addView(llImage,  (int)(supportUtility.pointScreenDimensions.x*.2),  (int)(supportUtility.pointScreenDimensions.x*.2));

            vP = new View(getActivity());
            vP.setBackgroundColor(Color.argb(0,0,0,0));
            llResults.addView(vP, 10, 10);
            llCard.addView(tvOpeningTitle, (int)(supportUtility.pointScreenDimensions.x*.75), (int)(supportUtility.pointScreenDimensions.y/3.5));

            vP = new View(getActivity());
            vP.setBackgroundColor(Color.argb(0,0,0,0));
            llCard.addView(vP, 10, 10);
            //llCard.addView(tvOpeningTitle);

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

    public String formatRegionResult(String region) {
        String output = "";
        if (region.equals("easteurope")) {
            output = "East Europe";
        } else if (region.equals("centralamerica")) {
            output = "Central America";
        } else if (region.equals("pacificislands")) {
            output = "Pacific Islands";
        } else if (region.equals("africa")) {
            output = "Africa";
        } else if (region.equals("southamerica")) {
            output = "South America";
        } else if (region.equals("asia")) {
            output = "Asia";
        } else if (region.equals("caribbean")) {
            output = "Caribbean";
        }
        return output;
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
