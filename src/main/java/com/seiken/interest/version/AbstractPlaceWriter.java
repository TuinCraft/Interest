package com.seiken.interest.version;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.seiken.interest.Place;

public abstract class AbstractPlaceWriter implements PlaceWriter {

	protected static final Logger log = Logger.getLogger("Minecraft");

	public void write(BufferedWriter writer, List<Place> places) {
		try {
			writer.write(getVersion() + "\n");
			for (Place place : places) {
				writer.write(saveString(place) + "\n");
			}
		} catch (IOException e) {
			log.severe("[Interest] Error writing data file (" + getVersion()
					+ "): " + e.getMessage());
		}
	}

	public abstract String getVersion();

	public abstract String saveString(Place p);

}
