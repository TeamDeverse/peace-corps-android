package com.richardwonseokshin.peacecorpsv2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ItemOne extends Fragment {

	public static final String TAG = ItemOne.class.getSimpleName();
	
	private ItemOnePagerAdapter mPagerAdapter;
	private ViewPager mViewPager;
	
	public static ItemOne newInstance() {
		return new ItemOne();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		mPagerAdapter = new ItemOnePagerAdapter(getFragmentManager());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_item_one, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		//PagerTitleStrip tabs = (PagerTitleStrip) view.findViewById(R.id.pager_title_strip);
		PagerTitleStrip tabs = new PagerTitleStrip(view.getContext());
		//ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		ViewPager pager = new ViewPager(view.getContext());
		pager.setAdapter(mPagerAdapter);

	}
	
	public class ItemOnePagerAdapter extends FragmentPagerAdapter {

		private final String[] TAB_TITLES = { "asdfSearch Opportunities", "Results", "Tab 3" };
		
		public ItemOnePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TAB_TITLES[position];
		}
		
		@Override
		public Fragment getItem(int position) {
			return ItemOneContentFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return 3;
			
		}

		@Override
		public Object instantiateItem(View container, int position) {
		    Context context = container.getContext();
		    LinearLayout layout = new LinearLayout(context);
		    TextView view = new TextView(context);

		    switch (position) {

		    case 0:
		       view.setText("some text 123");
		       view.setPadding(5, 5, 5, 5);
		       view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		       break;
		    case 1:
		       view.setText("some text 456");
		       view.setPadding(5, 5, 5, 5);
		       view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		       break;
		    case 2:
		       view.setText("some text 789");
		       view.setPadding(5, 5, 5, 5);
		       view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		       break;
		    }
		    layout.addView(view);
		    ((ViewPager) container).addView(layout, 0); // This is the line I added
		    return layout;
		}
		
		
		
	}
}
