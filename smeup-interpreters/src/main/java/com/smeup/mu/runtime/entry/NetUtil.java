package	com.smeup.mu.runtime.entry;

import java.util.Calendar;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class NetUtil
{

	public static String[] retrieveResourceStrings(String aResourceName,String[] aKey)
    {
    	ResourceBundle vR = null;
    	try
    	{
			vR = ResourceBundle.getBundle(aResourceName);
    	}
    	catch(MissingResourceException ex)
    	{
    		return aKey;
    	}
		String[] vKey = new String[aKey.length];
		for (int i = 0 ; i < aKey.length ; i++)
		{
			try
			{
				vKey[i] = vR.getString(aKey[i]);
			}
			catch(MissingResourceException ex)
			{
				vKey[i] = aKey[i];
			}
		}
        return vKey;
    }

	public static String[] buildParamList(String aString1,String aString2,String aString3,String aString4,String aString5)
	{
		String[] vTemp = new String[5];
		vTemp[0] = aString1;
		vTemp[1] = aString2;
		vTemp[2] = aString3;
		vTemp[3] = aString4;
		vTemp[4] = aString5;
		return vTemp;
	}

	public static String[] buildParamList(String aString1,String aString2,String aString3,String aString4)
	{
		String[] vTemp = new String[4];
		vTemp[0] = aString1;
		vTemp[1] = aString2;
		vTemp[2] = aString3;
		vTemp[3] = aString4;
		return vTemp;
	}

	public static String[] buildParamList(String aString1,String aString2,String aString3)
	{
		String[] vTemp = new String[3];
		vTemp[0] = aString1;
		vTemp[1] = aString2;
		vTemp[2] = aString3;
		return vTemp;
	}

	public static String[] buildParamList(String aString1,String aString2)
	{
		String[] vTemp = new String[2];
		vTemp[0] = aString1;
		vTemp[1] = aString2;
		return vTemp;
	}

	public static String[] buildParamList(String aString)
	{
		String[] vTemp = new String[1];
		vTemp[0] = aString;
		return vTemp;
	}


	public static String toSizedString(String aString, int aLen)
	{
		StringBuffer vTemp = new StringBuffer((aString==null)?"":aString);
		
		if (vTemp.length() < aLen)
		{
			// Se corto allunga fino a aLen
    		while (vTemp.length() <	aLen)
    		{
    			vTemp.append(" ");
    			//vTemp.insert(0," ");
    		}
		}
		else if (vTemp.length() > aLen)
		{
			// Se lungo tronca a aLen
			vTemp = vTemp.delete(aLen, vTemp.length());
		}
		return new String(vTemp);
	}

	public static String toSizedString(int aValue, int aLen)
	{
		StringBuffer vTemp = new StringBuffer();
		while (aLen	> 0)
		{
			vTemp.append("0");
			aLen--;
		}
		String vS =	String.valueOf(aValue);
		for	(int i = 0 ; i < vS.length(); i++)
			vTemp.setCharAt(i+vTemp.length()-vS.length(),vS.charAt(i));
		return new String(vTemp);
	}

    public static String toSizedString (Double vDouble, int aMaxDigit, int aMaxDecimalDigit)
    {
        StringBuffer vStr = new StringBuffer ();
        StringBuffer vFinalStr = new StringBuffer ();
        String vTemp = vDouble.toString ();
        int vIntegerDigit;
        int vDecimalDigit;
        int i;

        vIntegerDigit = vTemp.indexOf (".");
        vDecimalDigit = vTemp.length ()-vIntegerDigit-1;
        if (aMaxDigit-aMaxDecimalDigit > 0)
            vStr.append (vTemp.substring (0, vIntegerDigit));

        if (aMaxDecimalDigit > 0)
        {
            String aDecimalPart = vTemp.substring(vIntegerDigit+1, vTemp.length());
            if (aDecimalPart.length() > aMaxDecimalDigit)
				aDecimalPart = aDecimalPart.substring(0,aMaxDecimalDigit);

            vStr.append(aDecimalPart);
        }

        for (i=vStr.length (); i<aMaxDigit&& vDecimalDigit<aMaxDecimalDigit; i++, vDecimalDigit++)
            vStr.append ('0');
        for (i=vStr.length (); i<aMaxDigit; i++)
            vFinalStr.append ('0');
        vFinalStr.append (vStr.toString());
        return vFinalStr.toString ();
    }

    public static String toSizedString (NetTimeStamp aTime)
    {
    	StringBuffer vStr = new StringBuffer(14);
		vStr.append(NetUtil.toSizedString(aTime.get(Calendar.YEAR),4));
		vStr.append(NetUtil.toSizedString(aTime.get(Calendar.MONTH)+1,2));
		vStr.append(NetUtil.toSizedString(aTime.get(Calendar.DAY_OF_MONTH),2));
		vStr.append(NetUtil.toSizedString(aTime.get(Calendar.HOUR_OF_DAY),2));
		vStr.append(NetUtil.toSizedString(aTime.get(Calendar.MINUTE),2));
		vStr.append(NetUtil.toSizedString(aTime.get(Calendar.SECOND),2));
		return vStr.toString();
    }

    public static String toSizedString (NetDateStamp aDate)
    {
    	StringBuffer vStr = new StringBuffer(8);
		vStr.append(NetUtil.toSizedString(aDate.get(Calendar.YEAR),4));
		vStr.append(NetUtil.toSizedString(aDate.get(Calendar.MONTH)+1,2));
		vStr.append(NetUtil.toSizedString(aDate.get(Calendar.DAY_OF_MONTH),2));
		return vStr.toString();
    }

    public static String toSizedString (boolean aBoolean)
    {
		if (aBoolean)
			return new String("1");
		return new String("0");
	}

	public static String composeString(String aBaseString,String[] aParam)
	{
		StringBuffer iResult = new StringBuffer();
		int startPositionSubstring = 0;
		int i;
		for (i=0; i < aBaseString.length(); i++)
		{
		    if (aBaseString.charAt(i) == 37) // 37 è il '%'
			{
				if (aBaseString.charAt(i+1) == 37)
				{
					i++;
					continue;
				}
				int posParam = aBaseString.charAt(i+1)-65; // vedi tabella ASCII.
				iResult.append(new String(aBaseString.substring(startPositionSubstring, i)));
				iResult.append(aParam[posParam]);
				i++;
				startPositionSubstring = i + 1;
			}
		}
		iResult.append(new String(aBaseString.substring(startPositionSubstring, i)));
		return new String(iResult);
	}

	public static boolean isEmptyString(String aString)
	// Torna true se la stringa passata contiene solo spazi (quindi è vuota)
	{
	    boolean vEmpty = true;
		char[] vCharArray = aString.toCharArray();
		for (int i = 0; i<vCharArray.length; i++)
		{
			if (vCharArray[i] != ' ') vEmpty = false;
		}
		return vEmpty;


	}

    public static String compattaStringa(String aStringa)
    // Compatta la stringa eliminando i caratteri vuoti alla fine
    {
		String vStringa = aStringa;
		int vPosition = vStringa.length() -1;
		if (vPosition >= 0)
		{
			while (vPosition >= 0)
			{
				if (vStringa.charAt(vPosition) == ' ')
				{
					vStringa = vStringa.substring(0,vPosition);
					vPosition--;
				}
				else break;
			}
		}
		return vStringa;
    }
}

