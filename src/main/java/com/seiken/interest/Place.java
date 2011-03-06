package com.seiken.interest;

import org.bukkit.Location;
import org.bukkit.World;

public class Place {
	
//	private int x;
//	private int y;
//	private int z;
	private Location loc;
	private int radius;
	private int xDist;
	private int yDist;
	private int zDist;
	private String name;
	
	public Place( Location loc, int radius, String name )
	{
		this.loc = loc;
		this.radius = radius <= 0 ? -1 : radius;
		xDist = -1;
		yDist = -1;
		zDist = -1;
		this.name = name;
	}
	
	public Place(Location loc, String name)
	{
		this.loc = loc;
		this.radius = -1;
		xDist = -1;
		yDist = -1;
		zDist = -1;
		this.name = name;
	}
	
	public Place( Location loc, int xDist, int yDist, int zDist, String name )
	{
		this.loc = loc;
		radius = -1;
		this.xDist = xDist <= 0 ? -1 : xDist;
		this.yDist = yDist <= 0 ? -1 : yDist;
		this.zDist = zDist <= 0 ? -1 : zDist;
		this.name = name;
	}
	
//	public Place( String s, boolean v1_1 )
//	{
//		radius = -1;
//		xDist = -1;
//		yDist = -1;
//		zDist = -1;
//		if ( v1_1 ) {
//			
//		}
//		String[] args = s.split( " ", 4 );
//		x = Integer.parseInt( args[ 0 ] );
//		y = Integer.parseInt( args[ 1 ] );
//		z = Integer.parseInt( args[ 2 ] );
//		name = args[ 3 ].replaceAll( "##", "§" );
//	}
	


	public float distance( Location loc )
	{
		if(loc.getWorld() != this.loc.getWorld())
			return Float.MAX_VALUE;
		float r = 0;
		r += ( float )( getX() - loc.getBlockX() ) * ( float )( getX() - loc.getBlockX() );
		r += ( float )( getY() - loc.getBlockY() ) * ( float )( getY() - loc.getBlockY() );
		r += ( float )( getZ() - loc.getBlockZ() ) * ( float )( getZ() - loc.getBlockZ() );
		return r;
	}
	
	public int getX()
	{
		return loc.getBlockX();
	}
	
	public int getY()
	{
		return loc.getBlockY();
	}
	
	public int getZ()
	{
		return loc.getBlockZ();
	}
	
	public World getWorld()
	{
		return loc.getWorld();
	}
	
	public String getWorldName()
	{
		return this.getWorld().getName();
	}
	
	public int getRadiusValue()
	{
		return this.radius;
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
			return "§f" + name + " §f[" + Integer.toString( getX() ) + ", " + Integer.toString( getY() ) + ", " + Integer.toString( getZ() ) + ", r:" + Integer.toString( radius ) + "]";
		String s = "§f" + name + " §f[" + Integer.toString( getX() ) + ", " + Integer.toString( getY() ) + ", " + Integer.toString( getZ() );
		if ( hasXDist() )
			s += ", x:" + Integer.toString( xDist );
		if ( hasYDist() )
			s += ", y:" + Integer.toString( yDist );
		if ( hasZDist() )
			s += ", z:" + Integer.toString( zDist );
		return s + "]";
	}
	
}