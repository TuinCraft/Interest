package com.seiken.interest.version;


import com.seiken.interest.Interest;
import com.seiken.interest.Place;

public class Version1_1Writer extends AbstractPlaceWriter {

	public static final String VERSION = Interest.VERSION_1_1;

	public String getVersion() {
		return VERSION;
	}

	public String saveString(Place p) {
		if (p.hasRadius() || (!p.hasXDist() && !p.hasYDist() && !p.hasZDist()))
			return "" + p.getX() + " " + p.getY() + " " + p.getZ() + " "
					+ p.getRadiusValue() + " " + p.getName().replaceAll("§", "##");
		return "xyz " + p.getX() + " " + p.getY() + " " + p.getZ() + " "
				+ p.getXDist() + " " + p.getYDist() + " " + p.getZDist() + " "
				+ p.getName().replaceAll("§", "##");
	}

}
