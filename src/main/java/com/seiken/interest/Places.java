package com.seiken.interest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seiken.interest.version.PlaceReader;
import com.seiken.interest.version.Version1_1Reader;
import com.seiken.interest.version.Version1_1Writer;
import com.seiken.interest.version.Version1_2Reader;
import com.seiken.interest.version.Version1_2Writer;

public class Places {

	private final Interest plugin;
	private List<Place> places = new ArrayList<Place>();
	private static final Logger log = Logger.getLogger("Minecraft");

	private static final PlaceReader[] versions = new PlaceReader[2];

	public Places(final Interest plugin) {
		this.plugin = plugin;
		versions[0] = new Version1_1Reader(plugin);
		versions[1] = new Version1_2Reader(plugin);

		try {

			BufferedReader reader = new BufferedReader(new FileReader(
					plugin.getDataFile()));
			String s = reader.readLine();

			for(PlaceReader r : versions)
			{
				if(r.getVersion().equals(s))
				{
					this.places = r.read(reader);
				}
			}

			reader.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, "[Interest] Error opening "
					+ plugin.getDataFile().getPath());
		}
	}

	public List<Place> getPlaces() {
		return places;
	}

	void updateData() {
		try {
			// plugin.getDataFolder().mkdir();
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					plugin.getDataFile()));
			new Version1_2Writer().write(writer, places);
			writer.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, "[Interest] Error opening "
					+ plugin.getDataFile().getPath());
		}
	}

}
