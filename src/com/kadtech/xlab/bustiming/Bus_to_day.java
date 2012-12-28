package com.kadtech.xlab.bustiming;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class Bus_to_day extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_to_day);
		// Show the Up button in the action bar.
		Today today = new Today();
		Database db = new Database(this);
        Cursor busFrom = db.getTo("00:00", today,50);
        List<Button> bus = new ArrayList<Button>();
        if(busFrom.getCount() > 0)
        {
       	 busFrom.moveToFirst();
       	 LinearLayout ll = (LinearLayout)findViewById(R.id.layout_bus_to_day);
       	 LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
       	 for(int i = 0; i < busFrom.getCount(); ++i)
       	 {
       		 bus.add(new Button(this));
       		 ll.addView(bus.get(i),lp);
       		 bus.get(i).setPadding(0, 0, 0, 0);
       		 int color = R.color.WHITE;
       		 if(i%2==0)
       			 color = R.color.GREY;
       		 bus.get(i).setBackgroundColor(getResources().getColor(color));
       		 Time time = new Time();
       		 String[] t = busFrom.getString(busFrom.getColumnIndex(Database.busTime)).split(":");
       		 int hr = Integer.parseInt(t[0]);
       		 int min = Integer.parseInt(t[1]);        		 
       		 time.set(0,min, hr, 0, 0 ,0);
       		 String busTime = time.format("%I:%M%p");       		 
       		 bus.get(i).setText(busTime+"  "+getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busStart)))+" to "+getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busEnd))));
       		 busFrom.moveToNext();
       	 }
        }
        else
       	 bus.get(0).setText("No buses available");
        db.close();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
	}
	public String getStringByName(String astr)
    {
    	String pakage = "com.kadtech.xlab.bustiming";
    	int resId = getResources().getIdentifier(astr, "string", pakage);
    	return getString(resId);
    }

	@Override
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
