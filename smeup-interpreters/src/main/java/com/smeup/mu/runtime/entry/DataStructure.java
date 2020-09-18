package com.smeup.mu.runtime.entry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DataStructure {

	private HashMap<String, NetDataField> fieldList = new HashMap<>();
	
	public void addFieldDescription(NetDataField fieldDescription) {
		
		fieldList.put(fieldDescription.getName(), fieldDescription);
	}
	
	
	public boolean setField(String name, Object value) {
		boolean result = false;
		
		NetDataField netDataField = fieldList.get(name);
		
		if (netDataField != null) {
			try {
				netDataField.setValue(value, true);
				result = true;
				
			} catch (NetDataFieldException e) {
				result = false;
			}
		}
		return result;
	}
	
	public String getContents() {
		
		StringBuffer result = new StringBuffer();
		
		
		for (Map.Entry<String, NetDataField> entry : fieldList.entrySet()) {
		    
		    NetDataField field = entry.getValue();
		    
		    result.append(field.toSizedString());		    
		}
		
		return result.toString();
	}
			
}
