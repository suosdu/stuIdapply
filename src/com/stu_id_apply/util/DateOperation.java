package com.stu_id_apply.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOperation {
	public static String getYYYYMMDD() {
		// TODO Auto-generated method stub
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

}
