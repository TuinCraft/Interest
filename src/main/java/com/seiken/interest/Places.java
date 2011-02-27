package com.seiken.interest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Places {
	
	private final Interest plugin;
	private ArrayList< Place > places = new ArrayList< Place >();
	
	public Places( final Interest plugin )
	{
		this.plugin = plugin;
		try {
		    BufferedReader reader = new BufferedReader( new FileReader( plugin.getDataFolder() + File.separator + Interest.DATA_FILE ) );
		    boolean b = true;
		    boolean v1_1 = false;
		    String s = reader.readLine();
		    if ( s.equals( Interest.VERSION_1_1 ) ) {
		    	v1_1 = true;
		    	s = reader.readLine();
		    }
		    while ( b ) {
		    	try {
		    		places.add( new Place( s, v1_1 ) );
		    	}
		    	catch ( Exception e ) {
		    	}
		    	s = reader.readLine();
		    	b = s != null;
		    }
		    reader.close();
		}
		catch ( IOException e ) {
		}
	}
	
	public ArrayList< Place > getPlaces()
	{
		return places;
	}
	
	void updateData()
	{
    	try {
    		plugin.getDataFolder().mkdir();
    	    BufferedWriter writer = new BufferedWriter( new FileWriter( plugin.getDataFolder() + File.separator + Interest.DATA_FILE ) );
    	    writer.write( Interest.VERSION_1_1 + "\n" );
    	    for ( Place p : places )
    	    	writer.write( p.saveString() + "\n" );
    	    writer.close();
    	}
    	catch ( IOException e ) {
    	}
	}
	
}
