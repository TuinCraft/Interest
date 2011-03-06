package com.seiken.interest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bukkit.Location;

public class PlaceTree {
	
	private Comparator< Place > xSort = new Comparator< Place >() {

		public int compare( Place a, Place b )
		{
			if ( a.getX() < b.getX() )
				return -1;
			if ( a.getX() > b.getX() )
				return 1;
			return 0;
		}
	};
	
	private Comparator< Place > ySort = new Comparator< Place >() {

		public int compare( Place a, Place b )
		{
			if ( a.getY() < b.getY() )
				return -1;
			if ( a.getY() > b.getY() )
				return 1;
			return 0;
		}
	};
	
	private Comparator< Place > zSort = new Comparator< Place >() {

		public int compare( Place a, Place b )
		{
			if ( a.getZ() < b.getZ() )
				return -1;
			if ( a.getZ() > b.getZ() )
				return 1;
			return 0;
		}
	};
	
	private Place best;
	private float distance;
	
	private class Node {
		
		private Place place = null;
		private Node left = null;
		private Node right = null;
		private int depth = 0;
		
		public Node( List<Place> list, int depth )
		{
			this.depth = depth;
			if ( depth % 3 == 0 )
				Collections.sort( list, xSort );
			if ( depth % 3 == 1 )
				Collections.sort( list, ySort );
			if ( depth % 3 == 2 )
				Collections.sort( list, zSort );
			
			int median = list.size() / 2;
			place = list.get( median );
			
			if ( median > 0 ) {
				ArrayList< Place > a = new ArrayList< Place >();
				for ( int i = 0; i < median; i++ )
					a.add( list.get( i ) );
				left = new Node( a, depth + 1 );
			}
			
			if ( median + 1 < list.size() ) {
				ArrayList< Place > a = new ArrayList< Place >();
				for ( int i = median + 1; i < list.size(); i++ )
					a.add( list.get( i ) );
				right = new Node( a, depth + 1 );
			}
		}
		
		public void nearest( Location loc )
		{
			float d = place.distance( loc );
			if ( best == null || d < distance ) {
				boolean constraint = true; 
				if ( place.hasRadius() )
					constraint = d <= place.getRadius();
				else {
					if ( place.hasXDist() )
						constraint = constraint && Math.abs( place.getX() - loc.getBlockX() ) <= place.getXDist();
					if ( place.hasYDist() )
						constraint = constraint && Math.abs( place.getY() - loc.getBlockY() ) <= place.getYDist();
					if ( place.hasZDist() )
						constraint = constraint && Math.abs( place.getZ() - loc.getBlockZ() ) <= place.getZDist();
				}
				if ( constraint ) {
					best = place;
					distance = d;
				}
			}
			
			boolean b =
				( depth % 3 == 0 && loc.getBlockX() <= place.getX() ) ||
			    ( depth % 3 == 1 && loc.getBlockY() <= place.getY() ) ||
			    ( depth % 3 == 2 && loc.getBlockZ() <= place.getZ() );
			
			if ( b && left != null )
				left.nearest( loc );
			if ( !b && right != null )
				right.nearest( loc );
			
			int axis = 0;
			if ( depth % 3 == 0 )
				axis = loc.getBlockX() - place.getX();
			if ( depth % 3 == 1 )
				axis = loc.getBlockY() - place.getY();
			if ( depth % 3 == 2 )
				axis = loc.getBlockZ() - place.getZ();
			
			if ( Math.abs( axis ) < distance || best == null ) {
				if ( b && right != null )
					right.nearest( loc );
				if ( !b && left != null )
					left.nearest( loc );
			}
		}
		
	}
	
	private Node root = null;
	
	public PlaceTree( List<Place> list )
	{
		if ( list != null && list.size() > 0 )
			root = new Node( list, 0 );
	}
	
	public Place nearest( Location loc )
	{
		if ( root == null )
			return null;
		best = null;
		distance = 0;
		root.nearest( loc );
		return best;
	}
	
}
