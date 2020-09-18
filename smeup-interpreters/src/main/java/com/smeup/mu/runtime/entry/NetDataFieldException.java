package com.smeup.mu.runtime.entry;

public class NetDataFieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int WRONGDATATYPE = 4;
	public final static int DATANOTFOUND = 3;
	public final static int TOOMANYDECIMALS = 2;
	public final static int TOOMANYDIGITS = 1;

	protected int iErrorCode;
	protected NetDataField iDataField;

	public NetDataFieldException(NetDataField aDataField, int aErrorCode) {
		iDataField = aDataField;
		iErrorCode = aErrorCode;
	}

	public int getErrorCode() {
		return iErrorCode;
	}

	public NetDataField getDataField() {
		return iDataField;
	}

	public String getMessage() {
		String vErrorMsg = "Errore nel campo " + iDataField.getName() + "\n";

		switch (iErrorCode) {
		case WRONGDATATYPE:
			vErrorMsg += "Tipo dati errato";
			break;

		case DATANOTFOUND:
			vErrorMsg += "Dati non trovati";
			break;

		case TOOMANYDECIMALS:
			vErrorMsg += "Numero decimali eccessivo";
			break;

		case TOOMANYDIGITS:
			vErrorMsg += "Overflow dei dati (dati più lunghi della lunghezza max del campo)";
			break;

		}
		return vErrorMsg;
	}
}
