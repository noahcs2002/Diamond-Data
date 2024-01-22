package com.dd.api.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class PropertyFileReader {
    
    private final HashMap<String, String> contents;
    
    public PropertyFileReader(String filePath) {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
	    this.contents = new HashMap<>();
	    
	    String current = reader.readLine();
	    
	    while(current != null) {
		if ('/' == current.charAt(0)) {
		    current = reader.readLine();
		    continue;
		}
		
		String[] split = current.split("\\s+:\\s+");
		String formed = "";
		
		for (int i = 1; i < split.length; i += 1) {
		    formed += split[i];
		}
		this.contents.put(split[0], formed);
		
		current = reader.readLine();
	    }
	}
	catch(Exception ex) {
	    throw new RuntimeException(ex);
	}
    }
    
    public String generateContents() {
	return this.contents.toString();
    }
    
    public HashMap<String, String> getContents() {
	return contents;
    }
}
