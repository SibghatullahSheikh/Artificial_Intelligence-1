import java.util.*;

public class DFSearch {
	public Map graph;		// Global graph
	public Location iLoc;		// Global initial location
	public Location dLoc;		// Global destination location
	public int limit;		// Initial limit, 1000
	public int expansionCount;
	public Waypoint way;


	public DFSearch(Map graph, String iLoc, String dLoc, int limit) {
		this.graph = graph;
		this.limit = limit;
		this.iLoc = graph.findLocation(iLoc);
		this.dLoc = graph.findLocation(dLoc);
		way = new Waypoint(this.iLoc);
		expansionCount = 0;
	}

	public Waypoint search(boolean b) {
		Frontier frontier = new Frontier();
		Frontier explored = new Frontier();
		Waypoint node;
		expansionCount = 0;

		frontier.addToTop(way);

		while(!frontier.isEmpty()) {
			node = frontier.removeTop();
			
			if(node.isFinalDestination(dLoc.name))
				return node;
			if(node.depth >= limit)
				return null;
			
			explored.addToTop(node);
			node.expand();
			expansionCount++;

			for(int i = 0; i < node.options.size(); i++) {
				Waypoint child = new Waypoint();
				child = node.options.get(i);
				if(b) {
					if(!explored.contains(child) || frontier.contains(child))
						frontier.addToTop(child);
				}
				else frontier.addToTop(child);
			}
		}
		return null;
	}
}