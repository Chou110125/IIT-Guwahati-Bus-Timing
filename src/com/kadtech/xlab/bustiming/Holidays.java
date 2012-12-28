package com.kadtech.xlab.bustiming;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Holidays extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_holidays);
		// Show the Up button in the action bar.
		Database db = new Database(getBaseContext());
        Cursor holiday = db.holiday();
        List<TextView> bus = new ArrayList<TextView>(); 
        if(holiday.getCount() > 0)
        {
       	 holiday.moveToFirst();
       	 LinearLayout ll = (LinearLayout)findViewById(R.id.layout_holidays);
       	 TextView tv = new TextView(this);
       	 ll.addView(tv);
       	 tv.setPadding(10, 10, 10, 10);
       	 tv.setBackgroundColor(getResources().getColor(R.color.LightBLACK));
       	 tv.setTextColor(getResources().getColor(R.color.LIGHT));
       	 tv.setText("List of Holidays");
       	 for(int i = 0; i < holiday.getCount(); ++i)
       	 {
       		 bus.add(new TextView(this));
       		 ll.addView(bus.get(i));
       		 bus.get(i).setPadding(4, 4, 4, 4);
       		 int color = R.color.WHITE;
       		 if(i%2==1)
       			 color = R.color.GREY;
       		 bus.get(i).setBackgroundColor(getResources().getColor(color));
       		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM d (E)", Locale.US);
       		 sdf.setTimeZone(TimeZone.getDefault());
       		 String[] date = holiday.getString(holiday.getColumnIndex(Database.holiday)).split("-");
       		 Calendar c = new GregorianCalendar(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]));       		 
       		 bus.get(i).setText(sdf.format(c.getTime())+" "+holiday.getString(holiday.getColumnIndex(Database.holidayReason)));
       		 holiday.moveToNext();
       	 }
        }
        else
       	 bus.get(0).setText("No holidays in database.");
        db.close();
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        ActionBar actionBar = getActionBar();
		        actionBar.setDisplayHomeAsUpEnabled(true);
		    }
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
