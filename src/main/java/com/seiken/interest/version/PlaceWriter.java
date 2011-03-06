package com.seiken.interest.version;

import java.io.BufferedWriter;
import java.util.List;

import com.seiken.interest.Place;

public interface PlaceWriter {
	
	public void write(BufferedWriter writer, List<Place> p);
	
	public String getVersion();

}
