package com.seiken.interest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Places {
	
	private final Interest plugin;
	private ArrayList< Place > places = new ArrayList< Place >();
	private static final Logger log = Logger.getLogger("Minecraft");
	
	public Places( final Interest plugin )
	{
		this.plugin = plugin;
		try {
		    BufferedReader reader = new BufferedReader( new FileReader( plugin.getDataFile() ) );
		    boolean b = true;
		    boolean v1_1 = false;
		    String s = reader.readLine();
		    if ( s != null && s.equals( Interest.VERSION_1_1 ) ) {
		    	v1_1 = true;
		    	s = reader.readLine();
		    }
		    while ( s != null ) {
		    	try {
		    		places.add( new Place( s, v1_1 ) );
		    	}
		    	catch ( Exception e ) {
		    		log.log(Level.INFO, "[Interest] Received exception: " + e.getMessage());
		    	}
		    	s = reader.readLine();
		    }
		    reader.close();
		}
		catch ( IOException e ) {
			log.log(Level.SEVERE, "[Interest] Error opening " + plugin.getDataFile().getPath() );
		}
	}
	
	public ArrayList< Place > getPlaces()
	{
		return places;
	}
	
	void updateData()
	{
    	try {
    		//plugin.getDataFolder().mkdir();
    	    BufferedWriter writer = new BufferedWriter( new FileWriter( plugin.getDataFile() ) );
    	    writer.write( Interest.VERSION_1_1 + "\n" );
    	    for ( Place p : places )
    	    	writer.write( p.saveString() + "\n" );
    	    writer.close();
    	}
    	catch ( IOException e ) {
    		log.log(Level.SEVERE, "[Interest] Error opening " + plugin.getDataFile().getPath() );
    	}
	}
	
}
