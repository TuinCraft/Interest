package com.seiken.interest;

import org.bukkit.Location;

public class Place {
	
	private int x;
	private int y;
	private int z;
	private int radius;
	private int xDist;
	private int yDist;
	private int zDist;
	private String name;
	
	public Place( Location loc, int radius, String name )
	{
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
		this.radius = radius <= 0 ? -1 : radius;
		xDist = -1;
		yDist = -1;
		zDist = -1;
		this.name = name;
	}
	
	public Place( Location loc, int xDist, int yDist, int zDist, String name )
	{
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
		radius = -1;
		this.xDist = xDist <= 0 ? -1 : xDist;
		this.yDist = yDist <= 0 ? -1 : yDist;
		this.zDist = zDist <= 0 ? -1 : zDist;
		this.name = name;
	}
	
	public Place( String s, boolean v1_1 )
	{
		radius = -1;
		xDist = -1;
		yDist = -1;
		zDist = -1;
		if ( v1_1 ) {
			String[] xyz = s.split( " ", 2 );
			if ( xyz[ 0 ].equalsIgnoreCase( "xyz" ) ) {
				String[] args = xyz[ 1 ].split( " ", 7 );
				x = Integer.parseInt( args[ 0 ] );
				y = Integer.parseInt( args[ 1 ] );
				z = Integer.parseInt( args[ 2 ] );
				xDist = Integer.parseInt( args[ 3 ] );
				yDist = Integer.parseInt( args[ 4 ] );
				zDist = Integer.parseInt( args[ 5 ] );
				name = args[ 6 ].replaceAll( "##", "§" );
				return;
			}
			String[] args = s.split( " ", 5 );
			x = Integer.parseInt( args[ 0 ] );
			y = Integer.parseInt( args[ 1 ] );
			z = Integer.parseInt( args[ 2 ] );
			radius = Integer.parseInt( args[ 3 ] );
			name = args[ 4 ].replaceAll( "##", "§" );
			return;
		}
		String[] args = s.split( " ", 4 );
		x = Integer.parseInt( args[ 0 ] );
		y = Integer.parseInt( args[ 1 ] );
		z = Integer.parseInt( args[ 2 ] );
		name = args[ 3 ].replaceAll( "##", "§" );
	}
	
	public String saveString()
	{
		if ( hasRadius() || ( !hasXDist() && !hasYDist() && !hasZDist() ) )
			return Integer.toString( getX() ) + " " + Integer.toString( getY() ) + " " + Integer.toString( getZ() ) + " " + Integer.toString( radius ) + " " + getName().replaceAll( "§", "##" );
		return "xyz " + Integer.toString( getX() ) + " " + Integer.toString( getY() ) + " " + Integer.toString( getZ() ) + " " + Integer.toString( xDist ) + " " + Integer.toString( yDist ) + " " + Integer.toString( zDist ) + " " + getName().replaceAll( "§", "##" );
	}

	public float distance( Location loc )
	{
		float r = 0;
		r += ( float )( x - loc.getBlockX() ) * ( float )( x - loc.getBlockX() );
		r += ( float )( y - loc.getBlockY() ) * ( float )( y - loc.getBlockY() );
		r += ( float )( z - loc.getBlockZ() ) * ( float )( z - loc.getBlockZ() );
		return r;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	
	public float getRadius()
	{
		return radius * radius;
	}
	
	public boolean hasRadius()
	{
		return radius > 0; 
	}
	
	public int getXDist()
	{
		return xDist;
	}
	
	public boolean hasXDist()
	{
		return xDist > 0;
	}
	
	public int getYDist()
	{
		return yDist;
	}
	
	public boolean hasYDist()
	{
		return yDist > 0;
	}
	
	public int getZDist()
	{
		return zDist;
	}
	
	public boolean hasZDist()
	{
		return zDist > 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDesc()
	{
		if ( hasRadius() )
			return "§f" + name + " §f[" + Integer.toString( x ) + ", " + Integer.toString( y ) + ", " + Integer.toString( z ) + ", r:" + Integer.toString( radius ) + "]";
		String s = "§f" + name + " §f[" + Integer.toString( x ) + ", " + Integer.toString( y ) + ", " + Integer.toString( z );
		if ( hasXDist() )
			s += ", x:" + Integer.toString( xDist );
		if ( hasYDist() )
			s += ", y:" + Integer.toString( yDist );
		if ( hasZDist() )
			s += ", z:" + Integer.toString( zDist );
		return s + "]";
	}
	
}