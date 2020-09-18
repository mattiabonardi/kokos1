package com.smeup.mu.runtime.entry;

import java.text.DateFormat;
import java.util.GregorianCalendar;

public class NetTimeStamp extends GregorianCalendar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NetTimeStamp() {
		super();
	}

	public NetTimeStamp(int aYear, int aMonth, int aDay) {
		super(aYear, aMonth, aDay);
	}

	public NetTimeStamp(int aYear, int aMonth, int aDay, int anHour, int aMinute, int aSecond) {
		super(aYear, aMonth, aDay, anHour, aMinute, aSecond);
	}

	public String toString() {
		return DateFormat.getDateTimeInstance().format(getTime());
	}

	public long difference(NetTimeStamp aTime) {
		long vDelta = aTime.get(HOUR_OF_DAY) - get(HOUR_OF_DAY);
		vDelta *= 60;
		vDelta += (aTime.get(MINUTE) - get(MINUTE));
		vDelta *= 60;
		vDelta += (aTime.get(SECOND) - get(SECOND));
		vDelta *= 1000;
		vDelta += (aTime.get(MILLISECOND) - get(MILLISECOND));
		return vDelta;
	}
}