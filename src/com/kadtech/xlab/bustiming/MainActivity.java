package com.kadtech.xlab.bustiming;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	public Cursor busTo;
	public Cursor busFrom;
	public Today today;
	public Database db;
	public List<Button> bus;
	public List<Bus> bus_info;
	private int remYear;
	private int remMon;
	private int remDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   	 	db = new Database(this);
   	 	bus = new ArrayList<Button>();
   	 	bus_info = new ArrayList<Bus>();
   	 	for(int i = 0; i<6; ++i)
   	 		bus_info.add(new Bus());
   	 	bus.add((Button)findViewById(R.id.down_bus1));
   	 	bus.add((Button)findViewById(R.id.down_bus2));
   	 	bus.add((Button)findViewById(R.id.down_bus3));
   	 	bus.add((Button)findViewById(R.id.up_bus1));
   	 	bus.add((Button)findViewById(R.id.up_bus2));
   	 	bus.add((Button)findViewById(R.id.up_bus3));
        refresh();
    }
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	db.close();
    }
    public void onClick( View view )
    {   Intent intent;
    	switch(view.getId())
    	{
    		case R.id.from: intent = new Intent(this, Bus_for_day.class);
    						startActivity(intent); break;
    		case R.id.to:   intent = new Intent(this, Bus_to_day.class);
							startActivity(intent); break;
    		case R.id.down_bus1: 	if(bus_info.get(0).busNo !=null){
    								intent = new Intent(this, Bus_haults.class);
    								intent.putExtra("bus", bus_info.get(0));
    								startActivity(intent);} break;
    		case R.id.down_bus2: 	if(bus_info.get(1).busNo !=null){
    								intent = new Intent(this, Bus_haults.class);
									intent.putExtra("bus", bus_info.get(1));
									startActivity(intent);} break;
    		case R.id.down_bus3:  	if(bus_info.get(2).busNo !=null){
									intent = new Intent(this, Bus_haults.class);
									intent.putExtra("bus", bus_info.get(2));
									startActivity(intent);} break;
    		case R.id.up_bus1:  	if(bus_info.get(3).busNo !=null){
									intent = new Intent(this, Bus_haults.class);
									intent.putExtra("bus", bus_info.get(3));
									startActivity(intent);} break;
    		case R.id.up_bus2: 		if(bus_info.get(4).busNo !=null){
									intent = new Intent(this, Bus_haults.class);									
									intent.putExtra("bus", bus_info.get(4));
									startActivity(intent);} break;
    		case R.id.up_bus3:  	if(bus_info.get(4).busNo !=null){
									intent = new Intent(this, Bus_haults.class);
									intent.putExtra("bus", bus_info.get(5));
									startActivity(intent);} break;
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    		case R.id.refresh: refresh(); return true;
    		case R.id.about: Intent intent = new Intent(this, About.class);
    			startActivity(intent);
    			return true;
    		case R.id.specBus: Intent intent2 = new Intent(this, SpecialBuses.class);
    			startActivity(intent2);
    			return true;
    		case R.id.FindBus: showDatePickerDialog(); return true;
    		case R.id.holidays: Intent intent3 = new Intent(this, Holidays.class);
    				startActivity(intent3);
    				return true;
    		default:
                return super.onOptionsItemSelected(item);
    	}
    }
    public void CleanButton()
    {
    	for(int i=0; i<6; i++)
    		bus.get(i).setText(" ");
    }
    public void showDatePickerDialog() {
    	final Calendar c = Calendar.getInstance();
    	int year = c.get(Calendar.YEAR);
    	int month = c.get(Calendar.MONTH);
    	int day = c.get(Calendar.DAY_OF_MONTH);
    	OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){
        	

    		@Override
    		public void onDateSet(DatePicker view, int year, int monthOfYear,
    				int dayOfMonth) {
    			    		remYear = year; remMon = monthOfYear; remDay = dayOfMonth;
    			    		showTimePickerDialog();
    		}
        };
        DatePickerDialog newFragment = new DatePickerDialog(this, d, year, month, day);
        newFragment.show();
        
    }
    public void showTimePickerDialog()
    {
    	OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener()
		{
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Calendar c = new GregorianCalendar(remYear,remMon,remDay,hourOfDay,minute);
				loadDay( c.getTime() );
				
			}
		};
		TimePickerDialog newTime = new TimePickerDialog(this,t,Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),false);
		newTime.show();
    }
    public String getStringByName(String astr)
    {
    	String pakage = "com.kadtech.xlab.bustiming";
    	int resId = getResources().getIdentifier(astr, "string", pakage);
    	return getString(resId);
    }
    
    public void refresh()
    {   	 	 
         today = new Today();
         today.holiday = db.holiday(today.date);
        
        
         	CleanButton();
        	 SimpleDateFormat sdf = new SimpleDateFormat("E [h:mma] MMM d", Locale.US);
     		 sdf.setTimeZone(TimeZone.getDefault());
        	 TextView tv = (TextView)findViewById(R.id.bottomTV);
        	 tv.setPadding(30, 20, 30, 20);
        	 tv.setGravity(Gravity.BOTTOM);
        	 tv.setBackgroundColor(getResources().getColor(R.color.LightBLACK));
        	 tv.setTextColor(getResources().getColor(R.color.LIGHT));
        	 tv.setText(sdf.format(today.date));
        	 if( today.holiday == 1 )
             {
        		 today.reason = db.reason(today.date);
            	 tv.setText(sdf.format(today.date)+" " +today.reason);
             }
         busFrom = db.getFrom(today.time, today ,3);
         if(busFrom.getCount() > 0)
         {
        	 busFrom.moveToFirst();
        	 for(int i = 0; i < busFrom.getCount(); ++i)
        	 {
        		 Time time = new Time();
        		 String[] t = busFrom.getString(busFrom.getColumnIndex(Database.busTime)).split(":");
        		 int hr = Integer.parseInt(t[0]);
        		 int min = Integer.parseInt(t[1]);        		 
        		 time.set(0,min, hr, 0, 0 ,0);
        		 String busTime = time.format("%I:%M%p");
        		 bus_info.get(i).busNo = busFrom.getString(busFrom.getColumnIndex(Database.busNo));
        		 bus_info.get(i).haults = busFrom.getString(busFrom.getColumnIndex(Database.busHaults));
        		 bus_info.get(i).days = busFrom.getString(busFrom.getColumnIndex(Database.busDays));
        		 bus_info.get(i).start = getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busStart)));
        		 bus_info.get(i).end = getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busEnd)));
        		 bus_info.get(i).busTime = busTime;
        		 bus.get(i).setText(busTime+"  "+getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busStart)))+" to "+getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busEnd))));
        		 busFrom.moveToNext();
        	 }
        	 busFrom.close();
         }
         else
        	 bus.get(0).setText("No buses available");
         busTo = db.getTo(today.time, today,3);
         if(busTo.getCount() > 0)
         {
        	 busTo.moveToFirst();
        	 for(int i = 0; i < busTo.getCount(); ++i)
        	 {
        		 Time time = new Time();
        		 String[] t = busTo.getString(busTo.getColumnIndex(Database.busTime)).split(":");
        		 int hr = Integer.parseInt(t[0]);
        		 int min = Integer.parseInt(t[1]);
        		 time.set(0,min, hr, 0, 0 ,0);
        		 String busTime = time.format("%I:%M%p");
        		 bus_info.get(i+3).busNo = busTo.getString(busTo.getColumnIndex(Database.busNo));
        		 bus_info.get(i+3).haults = busTo.getString(busTo.getColumnIndex(Database.busHaults));
        		 bus_info.get(i+3).days = busTo.getString(busTo.getColumnIndex(Database.busDays));
        		 bus_info.get(i+3).start = getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busStart)));
        		 bus_info.get(i+3).end = getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busEnd)));
        		 bus_info.get(i+3).busTime = busTime;
        		 bus.get(i+3).setText(busTime+"  "+getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busStart)))+" to "+getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busEnd))));        		 
        		 busTo.moveToNext();
        	 }
        	 busTo.close();
         }
         else
        	 bus.get(3).setText("No buses available");
    }
    public void loadDay(Date date)
    {   	 	 
        today = new Today(date);
        today.holiday = db.holiday(today.date); 
        CleanButton();
       	 SimpleDateFormat sdf = new SimpleDateFormat("E [h:mma] MMM d", Locale.US);
    		 sdf.setTimeZone(TimeZone.getDefault());
       	 TextView tv = (TextView)findViewById(R.id.bottomTV);
       	 tv.setPadding(30, 20, 30, 20);
       	 tv.setGravity(Gravity.BOTTOM);
       	 tv.setBackgroundColor(getResources().getColor(R.color.LightBLACK));
       	 tv.setTextColor(getResources().getColor(R.color.LIGHT));
       	 tv.setText(sdf.format(today.date));
       	 if( today.holiday == 1 )
            {       		 
       		 today.reason = db.reason(today.date);
           	 tv.setText(sdf.format(today.date)+" " +today.reason);
            }
        busFrom = db.getFrom(today.time, today ,3);
        if(busFrom.getCount() > 0)
        {
       	 busFrom.moveToFirst();
       	 for(int i = 0; i < busFrom.getCount(); ++i)
       	 {
       		 Time time = new Time();
       		 String[] t = busFrom.getString(busFrom.getColumnIndex(Database.busTime)).split(":");
       		 int hr = Integer.parseInt(t[0]);
       		 int min = Integer.parseInt(t[1]);        		 
       		 time.set(0,min, hr, 0, 0 ,0);
       		 String busTime = time.format("%I:%M%p");
       		 bus_info.get(i).busNo = busFrom.getString(busFrom.getColumnIndex(Database.busNo));
       		 bus_info.get(i).haults = busFrom.getString(busFrom.getColumnIndex(Database.busHaults));
       		 bus_info.get(i).days = busFrom.getString(busFrom.getColumnIndex(Database.busDays));
       		 bus_info.get(i).start = getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busStart)));
       		 bus_info.get(i).end = getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busEnd)));
       		 bus_info.get(i).busTime = busTime;
       		 bus.get(i).setText(busTime+"  "+getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busStart)))+" to "+getStringByName("loc_"+busFrom.getString(busFrom.getColumnIndex(Database.busEnd))));
       		 busFrom.moveToNext();
       	 }
       	 busFrom.close();
        }
        else
       	 bus.get(0).setText("No buses available");
        busTo = db.getTo(today.time, today,3);
        if(busTo.getCount() > 0)
        {
       	 busTo.moveToFirst();
       	 for(int i = 0; i < busTo.getCount(); ++i)
       	 {
       		 Time time = new Time();
       		 String[] t = busTo.getString(busTo.getColumnIndex(Database.busTime)).split(":");
       		 int hr = Integer.parseInt(t[0]);
       		 int min = Integer.parseInt(t[1]);
       		 time.set(0,min, hr, 0, 0 ,0);
       		 String busTime = time.format("%I:%M%p");
       		 bus_info.get(i+3).busNo = busTo.getString(busTo.getColumnIndex(Database.busNo));
       		 bus_info.get(i+3).haults = busTo.getString(busTo.getColumnIndex(Database.busHaults));
       		 bus_info.get(i+3).days = busTo.getString(busTo.getColumnIndex(Database.busDays));
       		 bus_info.get(i+3).start = getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busStart)));
       		 bus_info.get(i+3).end = getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busEnd)));
       		 bus_info.get(i+3).busTime = busTime;
       		 bus.get(i+3).setText(busTime+"  "+getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busStart)))+" to "+getStringByName("loc_"+busTo.getString(busTo.getColumnIndex(Database.busEnd))));        		 
       		 busTo.moveToNext();
       	 }
       	 busTo.close();
        }
        else
       	 bus.get(3).setText("No buses available");
   }
}
