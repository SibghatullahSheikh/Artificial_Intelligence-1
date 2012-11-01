//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method.  The provided "good"
// heuristic function is admissible.
//
// YOUR NAME -- TODAY'S DATE
//GoodHeuristic.heuristicFunction(option)


public class GoodHeuristic extends Heuristic {

	// YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
	// ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

	// heuristicFunction -- Return the appropriate heuristic values for the
	// given search tree node.  Note that the given Waypoint should not be
	// modified within the body of this function.
	private Map map;
	private Location dest;
	
	public GoodHeuristic(Map map, Location dest) {
		this.map = map;
		this.dest = dest;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see Heuristic#heuristicFunction(Waypoint)
	 * 
	 * In design, this can have 2 inputs (in my mind, and this ramble may make no sense if you
	 * (the TA) decides to read this later when fully implemented), the first being the current
	 * node and this will find the smallest value to traverse, or the next node, which will
	 * will return the value/cost of taking that path. In thinking of this, the best option
	 * would be to have each call only return the "expected single traversal" as to reduce
	 * the cost of the search. Therefore, this can be done in a single method and has no need
	 * for my second method at all. What will be required to make this work is to call each
	 * individual option when in the methods and have it come here.
	 */
	
	public double heuristicFunction(Waypoint wp) {
		double hVal = 0.0, x, y;

		x = wp.loc.latitude - dest.latitude;
		y = wp.loc.longitude - dest.longitude;
		hVal = Math.sqrt((x*x) + (y*y));

		return (hVal);
	}

	/*
	private double shortDistance(Waypoint way) {
		double min = Double.POSITIVE_INFINITY, current = 0, lon, lat;
		int count = 0;
		
		way.expand();
		
		while(count < way.options.size()) {
			Waypoint next = way.options.get(count);
			lon = next.loc.longitude - dest.longitude;
			lat = next.loc.latitude - dest.latitude;
			current = Math.sqrt(lon*lon + lat*lat);
			if(current < min)
				min = current;
			count++;
		}
		
		return min;
	}
	*/
}
