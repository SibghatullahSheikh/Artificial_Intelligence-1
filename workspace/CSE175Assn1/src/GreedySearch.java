
public class GreedySearch {
	public Map graph;
	public int expansionCount;
	public Location iLoc;
	public Location dLoc;
	public int limit;
	public Waypoint way;
	
	public GreedySearch(Map graph, String iLoc, String dLoc, int limit) {
		this.graph = graph;
		this.iLoc = graph.findLocation(iLoc);
		this.dLoc = graph.findLocation(dLoc);
		this.limit = limit;
		way = new Waypoint(this.iLoc);
		expansionCount = 0;
	}
	
	public Waypoint search(boolean b) {
		GoodHeuristic h = new GoodHeuristic(graph, dLoc);
		SortedFrontier frontier = new SortedFrontier(SortBy.h);
		Frontier explored = new Frontier();
		Waypoint node;
		expansionCount = 0;

		frontier.addSorted(way);

		while(!frontier.isEmpty()) {
			node = frontier.removeTop();
			
			if(node.isFinalDestination(dLoc.name))
				return node;
			if(node.depth >= limit)
				return null;
			
			explored.addToTop(node);
			node.expand(h);
			expansionCount++;

			for(int i = 0; i < node.options.size(); i++) {
				Waypoint child = new Waypoint();
				child = node.options.get(i);
				if(b) {
					if(!explored.contains(child) || frontier.contains(child))
						frontier.addSorted(child);
				}
				else frontier.addSorted(child);
			}
		}
		return null;
	}
}
