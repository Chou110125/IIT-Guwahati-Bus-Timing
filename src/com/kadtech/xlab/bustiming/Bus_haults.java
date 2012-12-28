package com.kadtech.xlab.bustiming;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Bus_haults extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_haults);
		// Show the Up button in the action bar.
		Bus b = (Bus)getIntent().getSerializableExtra("bus");
		LinearLayout ll = (LinearLayout)findViewById(R.id.layout_bus_haults);
		List<TextView> t = new ArrayList<TextView>();
		t.add( new TextView(this));
		ll.addView(t.get(0));
		t.get(0).setText("Bus Number       : #"+b.busNo);
		t.add( new TextView(this));
		ll.addView(t.get(1));
		t.get(1).setText("Starting Point     : "+b.start);
		t.add( new TextView(this));
		ll.addView(t.get(2));
		t.get(2).setText("Destination         : "+b.end);
		t.add( new TextView(this));
		ll.addView(t.get(3));
		t.get(3).setText("Scheduled Start : "+b.busTime);
		t.add( new TextView(this));
		ll.addView(t.get(4));
		String[] days = b.days.split("#");
		String temp = "";
		for( int i = 0; i<days.length; ++i)
		{
			if(days[i].length()<1)
				continue;
			int daynum = (int)Integer.parseInt(days[i]);
			switch(daynum)
			{
				case 0: temp = temp + "Sunday; "; break;
				case 1: temp = temp + "Monday; "; break;
				case 2: temp = temp + "Tueday; "; break;
				case 3: temp = temp + "Wednesday; "; break;
				case 4: temp = temp + "Thursday; "; break;
				case 5: temp = temp + "Friday; "; break;
				case 6: temp = temp + "Saturday; "; break;
				case 7: temp = temp + "But not on intitute holidays"; break;
			}
		}
		t.get(4).setText("Working Days     : "+temp);
		t.add( new TextView(this));
		ll.addView(t.get(5));
		t.get(5).setText("Bus stops            : ");
		t.add( new TextView(this));
		ll.addView(t.get(6));
		t.get(6).setPadding(30, 0, 0, 0);
		t.get(6).setTextColor(getResources().getColor(R.color.GREY));
		String[] stops = b.haults.split("-");
		temp = "";
		for(int i = 0; i < stops.length; ++i)
		{
			temp = temp + getStringByName("loc_"+stops[i]) + "\n";
		}
		t.get(6).setText(temp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
	}

    public String getStringByName(String astr)
    {
    	String pakage = "com.kadtech.xlab.bustiming";
    	int resId = getResources().getIdentifier(astr, "string", pakage);
    	if(resId > 0)
    		return getString(resId);
    	return "";
    }@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
