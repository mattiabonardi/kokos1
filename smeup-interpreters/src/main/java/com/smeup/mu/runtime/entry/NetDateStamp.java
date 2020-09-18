package	com.smeup.mu.runtime.entry;

import java.text.DateFormat;
import java.util.GregorianCalendar;

public class NetDateStamp extends GregorianCalendar
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NetDateStamp()
	{
		super();
	}
	public NetDateStamp(int	aYear, int aMonth, int aDay)
	{
		super(aYear,aMonth,aDay);
	}

	public String toString()
	{
		return DateFormat.getDateInstance().format(getTime());
	}
}