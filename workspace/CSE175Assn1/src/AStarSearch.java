
public class AStarSearch {
	public Map graph;
	public int expansionCount;
	public Location iLoc;
	public Location dLoc;
	public int limit;
	public Waypoint way;
	
	public AStarSearch(Map graph, String iLoc, String dLoc, int limit) {
		this.graph = graph;
		this.iLoc = graph.findLocation(iLoc);
		this.dLoc = graph.findLocation(dLoc);
		this.limit = limit;
		way = new Waypoint(this.iLoc);
		expansionCount = 0;
	}
	
	public Waypoint search(boolean b) {
		SortedFrontier frontier = new SortedFrontier(SortBy.f);
		SortedFrontier explored = new SortedFrontier(SortBy.f);
		GoodHeuristic h = new GoodHeuristic(graph, dLoc);
		Waypoint node;
		expansionCount = 0;

		frontier.addSorted(way);

		while(!frontier.isEmpty()) {
			node = frontier.removeTop();
			
			if(node.isFinalDestination(dLoc.name))
				return node;
			if(node.depth >= limit)
				return null;
			
			explored.addSorted(node);
			node.expand(h);
			expansionCount++;

			for(int i = 0; i < node.options.size(); i++) {
				Waypoint child = new Waypoint();
				child = node.options.get(i);
				if(b) {
					if(!explored.contains(child) && !frontier.contains(child))
						frontier.addSorted(child);
					else if (frontier.contains(child)) {
						if (frontier.find(child).partialPathCost > child.partialPathCost) {
							frontier.fringe.remove(frontier.find(child));
							frontier.addSorted(child);
						}
					}
					else if(explored.contains(child)) {
						if (explored.find(child).partialPathCost > child.partialPathCost) {
							explored.fringe.remove(explored.find(child));
							explored.addSorted(child);
						}
					}
				}
				else frontier.addSorted(child);
			}
		}
		return null;
	}
}
