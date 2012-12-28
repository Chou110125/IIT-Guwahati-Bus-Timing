package com.kadtech.xlab.bustiming;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{
	static final String dbName = "iitgBus";
	static final String busTable = "bus";
	static final String holidayTable = "holiday";
	
	static final String id = "id";
	
	static final String busTime = "time";
	static final String busNo = "busno";
	static final String busStart = "start";
	static final String busHaults = "haults";
	static final String busEnd = "end";
	static final String busDays = "workingdays";
	static final String busType = "type";
	
	static final String holiday = "holiday";
	static final String holidayReason = "reason";
	
	public Database(Context context)
	{
		super(context, dbName, null, 12);//replace database version with one from file		
	}
	
	@Override
	public void onCreate( SQLiteDatabase db )
	{
		db.execSQL("CREATE TABLE "+busTable+"( "+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+busTime+" TIME, "+busNo+" INTEGER, "+busStart+" TEXT, "+busHaults+" TEXT, "+busEnd+" TEXT, "+busDays+" TEXT, "+busType+" INTEGER );");
		db.execSQL( "CREATE TABLE "+holidayTable+"( "+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+holiday+" DATE, "+holidayReason+" TEXT);");
		insertDefault(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
	{
		db.execSQL("DROP TABLE IF EXISTS "+busTable);
		db.execSQL("DROP TABLE IF EXISTS "+holidayTable);
		onCreate(db);
	}

	private void insertDefault(SQLiteDatabase db) {
		// To Campus mon to fri
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '06:45:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '08:15:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '10:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '12:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '13:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '14:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '15:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '16:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '17:15:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '18:45:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 7, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '19:30:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:00:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:30:00', '#1#2#3#4#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:55:00', '#1#2#3#4#5#', 1);");
		// To Campus sat sun
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '06:45:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '07:45:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '08:15:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '10:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '11:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '13:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '14:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '15:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '16:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '17:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 7, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '18:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '19:15:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:00:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:30:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:55:00', '#6#0#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'PB', 'JB', 'PBWT-BHM-KM-MG-AT', '21:00:00', '#6#0#', 1);");
		
		// To Campus friday only
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '09:00:00', '#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'PB', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '14:30:00', '#5#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'PB', 'KH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '20:45:00', '#5#', 1);");
		// To Campus not on sat sun and holidays
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'Sixmile', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '07:45:00', '#1#2#3#4#5#7#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'Beltola', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '07:45:00', '#1#2#3#4#5#7#', 1);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'Noonmati', 'GH', 'PBWT-BHM-KM-MG-AT-JB-AG-JG-ComC-ADMN-SH', '07:45:00', '#1#2#3#4#5#7#', 1);");
		// From Campus mon to fri
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '08:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '09:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '10:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '12:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '13:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '14:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '15:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '17:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 7, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '17:40:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '18:45:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '20:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 7, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '20:40:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '21:00:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:15:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:30:00', '#1#2#3#4#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:35:00', '#1#2#3#4#5#', 0);");
		// From Campus sat sun
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '08:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '09:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '10:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '12:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '13:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '14:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '15:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 7, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '16:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '17:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '17:40:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '18:45:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '20:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 3, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '20:40:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 7, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:00:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:15:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:15:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 4, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:15:00', '#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'GH', 'JB', 'SH-ADMN-ComC-JG-AG', '21:15:00', '#6#0#', 0);");
		// From Campus friday only
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'GH', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '11:00:00', '#5#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'GHk', 'PB', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '17:15:00', '#5#', 0);");
		// From Campus fri sat sun
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'GHk', 'KH', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '10:30:00', '#5#6#0#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 5, 'GHk', 'KH', 'SH-ADMN-ComC-JG-AG-JB-AT-MG-KM-BHM-PWBT', '15:30:00', '#5#6#0#', 0);");
		// From Campus mon to fri except holidays
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 6, 'GH', 'NM', 'AC-KV-SAC-ComC-JG-AG-JB-AT-MG-KM-BHM-PBWT-UBL-SGL-CAS-ARC-BM-GC', '11:00:00', '#1#2#3#4#5#7#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 1, 'ADMN', 'SM', 'AC-KV-SAC-ComC-JG-AG-JB-AT-MG-KM-BHM-PBWT-DP-AM-SGL-CPP-CC-SZ-SP-GM-HAH-SUM-DT', '11:00:00', '#1#2#3#4#5#7#', 0);");
		db.execSQL("INSERT INTO `bus` (`id`, `busno`, `start`, `end`, `haults`, `time`, `workingdays`, `type`) VALUES(null, 2, 'ADMN', 'Beltola', 'AC-KV-SAC-ComC-JG-AG-JB-AT-MG-KM-BHM-PBWT-PBOB-PLB-UB-LN-BG-ABC-GG-WL-SV', '11:00:00', '#1#2#3#4#5#7#', 0);");
		
	    // Holidays 2012
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-01-14', 'Magh Bihu');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-01-25', 'Prophet Mohammad''s Birthday');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-01-26', 'Republic Day');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-03-27', 'Holi');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-03-29', 'Good Friday');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-04-15', 'Bohag Bihu');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-05-25', 'Buddha Purnima');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-08-15', 'Independence Day');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-10-11', 'Maha Saptami (Dussehra)');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-10-13', 'Dussehra');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-10-14', 'Dussehra(Additional)');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-10-16', 'Idu''l Zuha');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-11-03', 'Diwali (Deepawali)');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-11-14', 'Muharram');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-11-17', 'Guru Nanak''s Birthday');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2013-12-25', 'Christmas Day');");
		db.execSQL("INSERT INTO `holiday` (`id`, `holiday`, `reason`) VALUES(null, '2012-12-25', 'Christmas Day');");

	}

	public Cursor checkHoliday() {
		SQLiteDatabase dr = this.getReadableDatabase();
		String sql = "SELECT * FROM "+busTable+" WHERE "+busDays+" LIKE '%7%'";
		return dr.rawQuery(sql, null);
	}

	public Cursor getFrom(String time, Today t, int limit) {
		SQLiteDatabase dr = this.getReadableDatabase();
		String sql = "SELECT * FROM "+busTable+" WHERE "+busTime+">'"+time+"' AND "+busType+"=0 AND "+busDays+" LIKE '%"+t.dayType+"%' ORDER BY "+busTime+" ASC LIMIT "+limit;
		if( t.holiday != 0 )
			sql = "SELECT * FROM "+busTable+" WHERE "+busTime+">'"+time+"' AND "+busType+"=0 AND "+busDays+" LIKE '%"+t.dayType+"%' AND "+busDays+" NOT LIKE '%7%' ORDER BY "+busTime+" ASC LIMIT "+limit;;
		return dr.rawQuery(sql, null);
	}

	public Cursor getTo(String time, Today t, int limit) {
		SQLiteDatabase dr = this.getReadableDatabase();
		String sql = "SELECT * FROM "+busTable+" WHERE "+busTime+">'"+time+"' AND "+busType+"=1 AND "+busDays+" LIKE '%"+t.dayType+"%' ORDER BY "+busTime+" ASC LIMIT "+limit;
		if( t.holiday != 0)
			sql = "SELECT * FROM "+busTable+" WHERE "+busTime+">'"+time+"' AND "+busType+"=1 AND "+busDays+" LIKE '%"+t.dayType+"%' AND "+busDays+" NOT LIKE '%7%' ORDER BY "+busTime+" ASC LIMIT "+limit;
		return dr.rawQuery(sql, null);
	}

	public int holiday(Date date) {
		SQLiteDatabase dr = this.getReadableDatabase();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		sdf.setTimeZone(TimeZone.getDefault());
		String sql = "SELECT * FROM "+holidayTable+" WHERE "+holiday+"='"+sdf.format(date)+"'";		
 		Cursor cur = dr.rawQuery(sql, null);
		if(cur.getCount() > 0)
		{	       	
			return 1;
		}
		else
		return 0;
	}
	public Cursor holiday() {		
		SQLiteDatabase dr = this.getReadableDatabase();
		String sql = "SELECT * FROM "+holidayTable+" ORDER BY "+holiday+" ASC";
		return dr.rawQuery(sql, null);
		
	}
	public String reason(Date date) {
		SQLiteDatabase dr = this.getReadableDatabase();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		sdf.setTimeZone(TimeZone.getDefault());
		String sql = "SELECT * FROM "+holidayTable+" WHERE "+holiday+"='"+sdf.format(date)+"'";
		Cursor c = dr.rawQuery(sql, null);
		if(c.getCount() > 0)
		{
			c.moveToFirst();
			return c.getString(c.getColumnIndex(holidayReason));
		}
		return null;
	}
}
