/*
 * Mark Torio
 * Intro to AI
 * Assignment #0
 *
public class BFSearch {

	public int expansionCount; // counter for # of expansions done
	private int dlimit; // depth limit variable
	private Map bfmap; // Map parameter for constructor
	private Waypoint loc; // store the created waypoint here
	private Location location; // Initial location parameter
	private Location destination; // Destination location parameter

	public BFSearch(Map map, String loct, String dest, int limit) //constructor
	{
		expansionCount = 0;
		dlimit = limit;
		bfmap = map;
		location = bfmap.findLocation(loct);
		destination = bfmap.findLocation(dest);
		loc = new Waypoint(location);
	}

	public Waypoint search(boolean chck) //search function
	{
		Frontier front = new Frontier(); // FIFO queue
		Frontier explored = new Frontier(); // explored queue
		front.addToBottom(loc); // insert root into the Frontier
		expansionCount = 0;
		Waypoint node;
		while (!front.isEmpty())
		{
			node = front.removeTop(); // pop()
			if (node.isFinalDestination(destination.name))
				return node;
			explored.addToBottom(node); // add node to explored queue
			if (dlimit < expansionCount) // terminate loop if we reach depth limit
				break;
			node.expand(); // expand node to access children.
			expansionCount++;
			for (int i = 0; i < node.options.size(); i++)
			{
				Waypoint child = new Waypoint();
				child = node.options.get(i);
				if (chck)
				{
					// check if child has been explored before or is in the frontier
					if (!(explored.contains(child) || front.contains(child)))
						front.addToBottom(child); // add child to queue
				}
				else
				{
					front.addToBottom(child);
				}
			}
		}
		return null;
	}
}*/