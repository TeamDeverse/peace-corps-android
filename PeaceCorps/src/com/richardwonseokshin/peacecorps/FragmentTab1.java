package com.richardwonseokshin.peacecorps;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentTab1 extends android.support.v4.app.Fragment {
	
	float width = 0;
	float height = 0;
	
	public FragmentTab1(float width, float height){
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);	
		
		TextView tvTab = new TextView(container.getContext());
		tvTab.setBackgroundColor(Color.YELLOW);
		tvTab.setGravity(Gravity.CENTER);
		tvTab.setText("Tab Number 1");
		
		return null;
	}

	@Override
	public void onPause() {
		
		super.onPause();		
	}
	
}

