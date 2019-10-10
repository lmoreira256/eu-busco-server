package br.com.eubusco.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String FORMATO_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String FORMATO_DD_MM_YYYY_HH_MM_SS = "dd/MM/YYYY HH:mm:ss";

	public static Date addDays(Date data, Integer dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, dias);
		return c.getTime();
	}

	public static String format(Date data, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.format(data);
	}

	public static Date initialDay(Date date) {
		if (date == null) {
			return null;
		}
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		localDateTime = localDateTime.withHour(LongUtil.ZERO.intValue()).withMinute(LongUtil.ZERO.intValue())
				.withSecond(LongUtil.ZERO.intValue());

		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date parse(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DD_MM_YYYY);
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DD_MM_YYYY);
		return sdf.format(date);
	}
}
