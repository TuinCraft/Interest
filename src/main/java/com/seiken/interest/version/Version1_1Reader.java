package com.seiken.interest.version;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;

import com.seiken.interest.Interest;
import com.seiken.interest.Place;

public class Version1_1Reader implements PlaceReader {
	
	public static final String VERSION = Interest.VERSION_1_1;

	private static final Logger log = Logger.getLogger("Minecraft");
	private Interest plugin;

	public Version1_1Reader(Interest plugin) {
		this.plugin = plugin;
	}

	public List<Place> read(BufferedReader f) {
		String s;
		List<Place> places = new ArrayList<Place>();
		try {
			while ((s = f.readLine()) != null) {
				int x, y, z, radius;
				String name;
				String[] xyz = s.split(" ", 2);
				Place p;
				if (xyz[0].equalsIgnoreCase("xyz")) {
					String[] args = xyz[1].split(" ", 7);
					x = Integer.parseInt(args[0]);
					y = Integer.parseInt(args[1]);
					z = Integer.parseInt(args[2]);
					int xDist = Integer.parseInt(args[3]);
					int yDist = Integer.parseInt(args[4]);
					int zDist = Integer.parseInt(args[5]);
					name = args[6].replaceAll("##", "§");
					p = new Place(new Location(plugin.getFirstWorld(), x, y, z), xDist, yDist, zDist, name);
				} else {
					String[] args = s.split(" ", 5);
					x = Integer.parseInt(args[0]);
					y = Integer.parseInt(args[1]);
					z = Integer.parseInt(args[2]);
					radius = Integer.parseInt(args[3]);
					name = args[4].replaceAll("##", "§");
					p = new Place(new Location(plugin.getFirstWorld(), x, y, z), radius, name);
				}
				places.add(p);
			}
		} catch (IOException e) {
			log.severe("[Interest] Could not read data file (Version 1.1): "
					+ e.getMessage());
		}
		return places;
	}

	public String getVersion() {
		return VERSION;
	}

}
