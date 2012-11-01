import java.util.List;
import java.util.SortedSet;

public class UniformCostSearch {
	public Map graph;
	public int expansionCount;
	public Location iLoc;
	public Location dLoc;
	public int limit;
	public Waypoint way;

	public UniformCostSearch(Map graph, String iLoc, String dLoc, int limit) {
		this.graph = graph;
		this.iLoc = graph.findLocation(iLoc);
		this.dLoc = graph.findLocation(dLoc);
		this.limit = limit;
		way = new Waypoint(this.iLoc);
		expansionCount = 0;
	}

	public Waypoint search(boolean b) {
		expansionCount = 0;
		SortedFrontier frontier = new SortedFrontier();
		Frontier explored = new Frontier();
		Waypoint node;

		frontier.addSorted(way);

		while(!frontier.isEmpty()) {
			node = frontier.removeTop();

			if(node.isFinalDestination(dLoc.name))
				return node;
			explored.addToBottom(node);
			node.expand();
			expansionCount++;
			for(int i = 0; i < node.options.size(); i++) {
				Waypoint child = new Waypoint();
				child = node.options.get(i);
				if(b) {
					if(!explored.contains(child) && !frontier.contains(child)) {
						frontier.addSorted(child);
					}
					else if (frontier.contains(child)) {
						if (frontier.find(child).partialPathCost > child.partialPathCost) {
							frontier.fringe.remove(frontier.find(child));
							frontier.addSorted(child);
						}
					}
				}
				else frontier.addSorted(child);
			}
		}
		return null;
	}
}
