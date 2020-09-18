package com.smeup.mu.runtime.entry;

import java.math.BigDecimal;

public class NetDataField
{
    public final static int UNDEFINED = 0;      // Tipo indefinito.
	public final static	int	CHAR	  = 1;      // Tipo carattere.
	public final static	int	INTEGER	  = 2;      // Tipo numero intero.
	public final static	int	DOUBLE	  = 3;      // Tipo numero con decimali.
	public final static	int	BOOLEAN	  = 4;      // Tipo vero-falso.
	public final static	int	TIMESTAMP = 5;      // Tipo data con minuti, secondi e millesimi.
	public final static int DATESTAMP = 6;      // Tipo data con solo giorno, mese e anno.

	protected String iName = null;
	protected int iType = 0;
	protected int iLength = 0;
	protected int iDecimals = 0;
	protected Object iValue = null;


	public NetDataField(String	aName, int aType, int aLength, int aDecimals)
	{
		iName =	aName;
		iType =	aType;
		iLength = aLength;
		iDecimals = aDecimals;

		switch(iType)
    	{
			case NetDataField.CHAR:
				iValue = (Object)new String();
			break;

			case NetDataField.INTEGER:
				iValue = (Object) new Integer(0);
			break;

			case NetDataField.DOUBLE:
				iValue = (Object) new Double(0.0);
			break;

			case NetDataField.BOOLEAN:
				iValue = (Object) new Boolean(false);
			break;

			case NetDataField.TIMESTAMP:
				iValue = (Object) new NetTimeStamp();
			break;

			case NetDataField.DATESTAMP:
			    iValue = (Object) new NetDateStamp();
			break;
		}
	}

	public String getName()
	{
		return iName;
	}

	public int getType()
	{
		return iType;
	}

	public int getLength()
	{
		return iLength;
	}

	public int getDecimals()
	{
	    return iDecimals;
	}


	public String toString()
    {
        String vTemp = null;
		switch (iType)
		{
            case CHAR:
                vTemp = (String) iValue;
            break;

            case INTEGER:
                vTemp = ((Integer) iValue).toString();
            break;

            case DOUBLE:
                vTemp = convertToBigDecimal(((Double)iValue).toString());
            break;

            case BOOLEAN:
            	if (((Boolean) iValue).booleanValue())
	            	vTemp = new String("1");
	            else
	            	vTemp = new String("0");
            break;

            case NetDataField.TIMESTAMP:
                vTemp =((NetTimeStamp) iValue).toString();
			break;

            case NetDataField.DATESTAMP:
                vTemp =((NetDateStamp) iValue).toString();
            break;

			default:
				return new String();
        }
        return vTemp;
    }

    String convertToBigDecimal(String aValue)
    {
        int i;
        BigDecimal vBigDecimal = new BigDecimal(aValue);
        String vNumber = vBigDecimal.toString();
        for(i=0; i< vNumber.length(); i++)
        {
            if (vNumber.charAt(i) == '.')
                break;
        }

        String vDecimalPart = new String (vNumber.substring(i+1,vNumber.length()));
        if (vDecimalPart.length() <= iDecimals)
            return aValue;

        return aValue.substring(0,i+iDecimals+1);
    }

    /**
     * Set del valore associato al NetDataField con il trim degli spazi finali.
     * E' il comportamento "storico" di questa classe, nel senso che è nata
     * così. La possibilità di avere o meno il trim è nata dopo.
     * @param anObject
     * @throws NetDataFieldException
     */
    public void setValue(Object anObject) throws NetDataFieldException
    {
        setValue(anObject, true, true);
    }
    
    public void setValue(Object anObject, boolean aTrimValue) throws NetDataFieldException 
    {
    	setValue(anObject, aTrimValue, true);
    }

    /**
     * Set del valore associato con indicazione se effettuare o meno il trim.
     * Se si deve associare al NetDataField un nuovo valore che copra quello
     * vecchio usare questo metodo con il parametro aTrimValue posto a false:
     * in caso contrario, se il nuovo valore è più corto del vecchio il campo
     * rimane in parte sporco con la parte residua del vecchio valore (quella
     * che eccede la lunghezza del valore nuovo)
     * 
     * Se Truncate = true, taglia il valore se eccede la dimensione del campo.
     * 
     * @param anObject
     * @param aTrimValue
     * @patam aTruncate
     * @throws NetDataFieldException
     */
    public void setValue(Object anObject, boolean aTrimValue, boolean aTruncate) throws NetDataFieldException
    {
		String vType = anObject.getClass().getName();
		double vRangeValue = 0;
		switch (iType)
		{
			case NetDataField.CHAR:
				if (!vType.equals("java.lang.String"))
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
				String vObject = (String) anObject;

				// Compatta la stringa eliminando i caratteri vuoti alla fine
                if (aTrimValue) vObject = stripTrailingBlanks(vObject);
            
                /*
                 * Modificata la gestione dei campi eccedenti la lunghezza max. Prima venivano lasciati
                 * bianchi, ora vengono valorizzati con il valore tronzato alla max lunghezza concessa
                 * se aTruncate vale true
                 * 
                 */
                String vValue = new String(vObject);
                if (vValue.length() > iLength && aTruncate)
                {                    
                    vValue = vValue.substring(0, iLength);                    
                }
                              
                iValue = (Object)vValue;
                
				break;

			case NetDataField.INTEGER:
				if (!vType.equals("java.lang.Integer"))
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
				Integer vIntegerObject = (Integer) anObject;
				vRangeValue = Math.pow(10, (double)iLength);
                vRangeValue = vRangeValue - 1;
				if (vIntegerObject.intValue() > vRangeValue ||
				    vIntegerObject.intValue() < (vRangeValue)*(-1))
                    throw (new NetDataFieldException(this,NetDataFieldException.TOOMANYDIGITS));
				iValue = (Object) anObject;
				break;

			case NetDataField.DOUBLE:
				if (!vType.equals("java.lang.Double"))
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
				Double vDoubleObject = (Double) anObject;
        		vRangeValue = Math.pow(10, (double)(iLength-iDecimals));
                vRangeValue = vRangeValue - 1;
				if (vDoubleObject.doubleValue() > vRangeValue ||
				    vDoubleObject.doubleValue() < (vRangeValue)*(-1))
                    throw (new NetDataFieldException(this,NetDataFieldException.TOOMANYDIGITS));

        		vRangeValue = Math.pow(10, (double)iDecimals);
                double vValue1 = Math.rint(vDoubleObject.doubleValue() * vRangeValue);
                if ((vValue1/vRangeValue) != vDoubleObject.doubleValue())
                {
					BigDecimal vBigDecimal = new BigDecimal(vDoubleObject.doubleValue());
                    String vNumber = vBigDecimal.toString();
                    String vDecimalPart = new String("");
                    int j=0;
                    for(int i=0; i< vNumber.length(); i++)
                    {
                        if(j==0)
                        {
                            if (vNumber.charAt(i) == '.')
                            {
                                j++;
                                continue;
                            }
                            continue;
                        }

                        vDecimalPart += new Character (vNumber.charAt(i)).toString();
                        if (j == iDecimals + 3)
                        {
                            String s1 = vDecimalPart.substring(iDecimals,vDecimalPart.length());
                            if (vDecimalPart.substring(iDecimals,vDecimalPart.length()).equals("999") ||
                                vDecimalPart.substring(iDecimals,vDecimalPart.length()).equals("000"))
								break;
                            else
                                throw (new NetDataFieldException(this,NetDataFieldException.TOOMANYDECIMALS));
                        }
                        j++;
                    }

                    //throw (new NetDataFieldException(this,NetDataFieldException.TOOMANYDECIMALS));
                }

				iValue = (Object) anObject;
				break;

			case NetDataField.BOOLEAN:
				if (!vType.equals("java.lang.Boolean"))
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
				iValue = anObject;
				break;

			case NetDataField.TIMESTAMP:
				if (!vType.equals("Smeup.smec_s.net.NetTimeStamp"))
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
				iValue = (Object) anObject;
				break;

			case NetDataField.DATESTAMP:
				if (!vType.equals("Smeup.smec_s.net.NetDateStamp"))
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
				iValue = (Object) anObject;
				break;

			default:
					throw (new NetDataFieldException(this,NetDataFieldException.WRONGDATATYPE));
		}
	}


    public String toSizedString()
	{
        StringBuffer vBuffer = new StringBuffer();
        switch(getType())
		{
		    case CHAR:
			    if (getValue() != null)
				    vBuffer.append(NetUtil.toSizedString((String)getValue(),getLength()));
                else
					vBuffer.append(NetUtil.toSizedString(new String(),getLength()));
			break;

            case INTEGER:
			    if (getValue() != null)
                    vBuffer.append(NetUtil.toSizedString(((Integer)getValue()).intValue(),getLength()));
    			else
					vBuffer.append(NetUtil.toSizedString(new Integer(0).intValue(),getLength()));
			break;

			case DOUBLE:
                if (getValue() != null)
				    vBuffer.append(NetUtil.toSizedString(((Double)getValue()),getLength(),getDecimals()));
				else
					vBuffer.append(NetUtil.toSizedString(new Double(0),getLength(),getDecimals()));
			break;

			case BOOLEAN:
                if (getValue() != null)
                    vBuffer.append(NetUtil.toSizedString(((Boolean)getValue()).booleanValue()));
                else
				    vBuffer.append("0");
            break;

            case TIMESTAMP:
    			if (getValue() != null)
                    vBuffer.append(NetUtil.toSizedString((NetTimeStamp)getValue()));
		    	else
					vBuffer.append("00000000000000");
			break;

			case DATESTAMP:
				if (getValue() != null)
                    vBuffer.append(NetUtil.toSizedString((NetDateStamp)getValue()));
				else
					vBuffer.append("00000000");
			break;

			default:
			throw (new RuntimeException("Errore di programmazione: Tipo dati sconosciuto in NetDataField"));
		}
		return vBuffer.toString();
	}


	public Object getValue()
    {
    	return iValue;
	}

	public void setName(String aName)
	{
		iName=aName;
	}
	
	private String stripTrailingBlanks(String s)
    {
        if(s == null)
        {
            return null;
        }

        int pos = s.length() - 1;

        while((pos >= 0) && Character.isWhitespace(s.charAt(pos)))
        {
            pos--;
        }

        return s.substring(0, pos + 1);
    }
}