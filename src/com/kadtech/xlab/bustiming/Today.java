package com.kadtech.xlab.bustiming;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Today {
	public int dayType;
	public int holiday;
	public String reason;
	public int day;
	public Date date;
	public String time;
	SimpleDateFormat format;
	public Today(){
		date = new Date(System.currentTimeMillis());
		refresh();
	}
	public Today(Date da)
	{
		date = da;
		refresh();
	}
	private void refresh() {
		format = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
		time = (String)format.format(date);
		day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		switch(day)
		{
			case Calendar.MONDAY: dayType = 1; break;
			case Calendar.TUESDAY: dayType = 2; break;
			case Calendar.WEDNESDAY: dayType = 3; break;
			case Calendar.THURSDAY: dayType = 4; break;
			case Calendar.FRIDAY:	 dayType = 5; break;				
			case Calendar.SATURDAY: dayType = 6; break;
			case Calendar.SUNDAY: dayType = 0; break;
		}
	}
	public int daytype()
	{
		if(holiday != 0)
		{
			if( day == Calendar.FRIDAY )
				return 4;
		}
		return dayType;
	}
}
