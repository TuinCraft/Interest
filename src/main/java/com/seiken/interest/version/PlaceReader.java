package com.seiken.interest.version;

import java.io.BufferedReader;
import java.util.List;

import com.seiken.interest.Place;

public interface PlaceReader {
	
	public String getVersion();

	public List<Place> read(BufferedReader f);
}
