//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method. The provided "good"
// heuristic function is admissible.
//
// Mark Torio -- 9/27/2012
//


public class GoodHeuristic extends Heuristic {

	// YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
	// ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

	public Map hmap;
	public Location dst;

	public GoodHeuristic(Map map, Location destination)
	{
		dst = destination;
		hmap = map;
	}

	public double maxDistance()
	{
		double x, y, length, speed;
		double maximumspeed = 0.0;
		int lcounter = 0;
		while (lcounter < hmap.locations.size())
		{
			Location loc = hmap.locations.get(lcounter);
			int rcounter = 0; // initializing counter and zeroing.
			while (rcounter < loc.roads.size())
			{
				//calculate distance
				Road rd = loc.roads.get(rcounter);
				Location prevloc = rd.fromLocation;
				Location nexloc = rd.toLocation;
				x = prevloc.longitude - nexloc.longitude;
				y = prevloc.latitude - nexloc.latitude;
				length = Math.sqrt((x * x) + (y * y));
				//divide by time to get speed
				speed = length / rd.cost;
				//update maximum speed if necessary
				if (maximumspeed < speed)
					maximumspeed = speed;
				rcounter++; //increment counter
			}
			lcounter++; //increment counter
		}
		return maximumspeed;
	}

	// heuristicFunction -- Return the appropriate heuristic values for the
	// given search tree node. Note that the given Waypoint should not be
	// modified within the body of this function.
	public double heuristicFunction(Waypoint wp)
	{
		double xcoor, ycoor, max;
		//calculate distance to destination.
		xcoor = wp.loc.latitude - dst.latitude;
		ycoor = wp.loc.longitude - dst.longitude;
		max = Math.sqrt((xcoor * xcoor) + (ycoor * ycoor));
		//returning time
		return max / maxDistance();
	}

}