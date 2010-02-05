package aimax.osm.data.entities;

import java.util.ArrayList;
import java.util.List;

import aimax.osm.data.MapEntityVisitor;

/**
 * Represents a track. A track is not really part of a map, but essential
 * for displaying route planning results and agent movements. Therefore,
 * it has been added here.
 * @author R. Lunde
 */
public class Track extends MapEntity {
	private ArrayList<MapNode> trkpts;
	
	public Track(long id, String name) {
		this.id = id;
		this.name = name;
		trkpts = new ArrayList<MapNode>();
	}

	public List<MapNode> getTrkPts() {
		return trkpts;
	}
	
	public MapNode getLastTrkPt() {
		MapNode result = null;
		if (!trkpts.isEmpty())
			result = trkpts.get(trkpts.size()-1);
		return result;
	}

	public void addTrkPt(MapNode node) {
		this.trkpts.add(node);
	}

	public void accept(MapEntityVisitor visitor) {
		visitor.visitTrack(this);
	}
	
	public int compareLatitude(float lat) {
		int result = trkpts.get(0).compareLatitude(lat);
		for (int i = 1; i < trkpts.size(); i++)
			if (result != trkpts.get(i).compareLatitude(lat))
				return 0;
		return result;
	}
	
	public int compareLongitude(float lon) {
		int result = trkpts.get(0).compareLongitude(lon);
		for (int i = 1; i < trkpts.size(); i++)
			if (result != trkpts.get(i).compareLongitude(lon))
				return 0;
		return result;
	}
}
