package com.seiken.interest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Config {
	
	private boolean opsOnly        = false;
	private boolean disableWho     = false;
	private String entering        = "§3entering §f$1";
	private String leaving         = "§3entering wilderness";
	private String whoHead	       = "§e$1 player(s) online:";
	private String whoLinePlace    = "§e+ §f$1 §e- §f$2";
	private String whoLineNoPlace  = "§e+ §f$1 §e- §fwilderness";
	
	private static final String opsOnlyKey = "ops-only";
	private static final String disableWhoKey = "disable-who";
	private static final String enteringKey = "entering";
	private static final String leavingKey = "leaving";
	private static final String whoHeadKey = "who-head";
	private static final String whoLinePlaceKey = "who-line-place";
	private static final String whoLineNoPlaceKey = "who-line-no-place";
	
	public Config( final Interest plugin ) {
		try {
		    BufferedReader reader = new BufferedReader( new FileReader( plugin.getDataFolder() + File.separator + Interest.CONFIG_FILE ) );
		    
		    boolean b = true;
		    String s = reader.readLine();
		    while ( b ) {
		    	try {
			    	String[] args = s.split( "=", 2 );
			    	if ( args[ 0 ].equalsIgnoreCase( opsOnlyKey ) )
			    		opsOnly = Integer.parseInt( args[ 1 ] ) != 0;
			    	if ( args[ 0 ].equalsIgnoreCase( disableWhoKey ) )
			    		disableWho = Integer.parseInt( args[ 1 ] ) != 0;
			    	if ( args[ 0 ].equalsIgnoreCase( enteringKey ) )
			    		entering = Interest.safeMessage( args[ 1 ].replaceAll( "##", "§" ) );
			    	if ( args[ 0 ].equalsIgnoreCase( leavingKey ) )
			    		leaving = Interest.safeMessage( args[ 1 ].replaceAll( "##", "§" ) );
			    	if ( args[ 0 ].equalsIgnoreCase( whoHeadKey ) )
			    		whoHead = Interest.safeMessage( args[ 1 ].replaceAll( "##", "§" ) );
			    	if ( args[ 0 ].equalsIgnoreCase( whoLinePlaceKey ) )
			    		whoLinePlace = Interest.safeMessage( args[ 1 ].replaceAll( "##", "§" ) );
			    	if ( args[ 0 ].equalsIgnoreCase( whoLineNoPlaceKey ) )
			    		whoLineNoPlace = Interest.safeMessage( args[ 1 ].replaceAll( "##", "§" ) );
		    	}
		    	catch ( NumberFormatException e )
		    	{
		    	}
		    	s = reader.readLine();
		    	b = s != null;
		    }
		    reader.close();
		}
		catch ( IOException e ) {
		}
	}
	
	public boolean opsOnly()
	{
		return opsOnly;
	}
	
	public boolean disableWho()
	{
		return disableWho;
	}
	
	public String entering( String arg1 )
	{
		return entering.replaceAll( "\\$1", arg1 );
	}
	
	public String leaving( String arg1 )
	{
		return leaving.replaceAll( "\\$1", arg1 );
	}
	
	public boolean leavingUsesArg()
	{
		return leaving.contains( "$1" );	
	}

	public String whoHead( String arg1 )
	{
		return whoHead.replaceAll( "\\$1", arg1 );
	}
	
	public String whoLinePlace( String arg1, String arg2 )
	{
		return whoLinePlace.replaceAll( "\\$1", arg1 ).replaceAll( "\\$2", arg2 );
	}
	
	public String whoLineNoPlace( String arg1 )
	{
		return whoLineNoPlace.replaceAll( "\\$1", arg1 );
	}
	
}
