
public class AirportNode implements Comparable<AirportNode>{
	char name;
	int duration;
	char parent;
	
	public AirportNode(char name, int duration, char parent) {
		this.name = name;
		this.duration = duration;
		this.parent = parent;
	}

	@Override
	public int compareTo(AirportNode airportNode) {
		return this.duration - airportNode.duration;
	}
	
	public boolean equals(AirportNode node) {
		return this.name == node.name;
	}
}
