import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

	public static class Flight{
		public char destination;
		public int duration;
		
		public Flight(char destination, int duration) {
			this.destination = destination;
			this.duration = duration;
		}
			
	}

	public static Set<Character> airports = new HashSet<Character>();
	public static HashMap<Character, ArrayList<Flight>> flights = new HashMap<Character, ArrayList<Flight>>();
	public static PrintWriter printer;
	
	public static void main(String[] args) {
		
		extractFlightInfo("resources/flights.txt");
		
		char origin = 0;
		char destination = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("resources/tasks.txt"));
			String task = reader.readLine();
			origin = task.charAt(0);
			destination = task.charAt(2);
			
			printer = new PrintWriter(new BufferedWriter(new FileWriter("output/output.txt")));
			printQuickestPath(origin, destination);
			printer.append('\n');
			
			task = reader.readLine();
			origin = task.charAt(0);
			int numFlights = Integer.valueOf(task.substring(2));
			printDestinations(origin, numFlights);
			printer.append('\n');
			
			task = reader.readLine();
			origin = task.charAt(0);
			printQuickestPath(origin, origin);
			
			reader.close();
			printer.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void extractFlightInfo(String filePath) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String flight = reader.readLine();
			
			while (flight != null) {
				
				String[] flightDetails = flight.split(",");
				
				char origin      = flightDetails[0].charAt(0);
				char destination = flightDetails[1].charAt(0);
				
				airports.add(origin);
				airports.add(destination);
			
				ArrayList<Flight> flightsFromOrigin = flights.get(origin);
				Flight currFlight = new Flight(destination, Integer.valueOf(flightDetails[2]));
				if (flightsFromOrigin == null) {
					ArrayList<Flight> flightList = new ArrayList<Flight>();
					flightList.add(currFlight);
					flights.put(origin, flightList);
				}
				else {
					flights.get(origin).add(currFlight);
				}
				
				flight = reader.readLine();
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printQuickestPath(char origin, 
										 char destination){
		
		PriorityQueue<AirportNode>  unvisitedAirportsMinHeap = new PriorityQueue<AirportNode>();
		HashMap<Character, Integer> unvisitedAirportsHashMap = new HashMap<Character, Integer>();
		HashMap<Character, AirportNode> visitedAirports = new HashMap<Character, AirportNode>();
		
		if (origin != destination) {
			for (Character airport : airports) {
				if (!airport.equals(origin)) {
					unvisitedAirportsMinHeap.add(new AirportNode(airport, Integer.MAX_VALUE, '\0'));
					unvisitedAirportsHashMap.put(airport, Integer.MAX_VALUE);
				}
			}
			unvisitedAirportsMinHeap.add(new AirportNode(origin, 0, '\0'));
			unvisitedAirportsHashMap.put(origin, 0);
		}
		else {
			HashSet<Character> airportsAtFirstLevel = new HashSet<Character>();
			for (Flight flight : flights.get(origin)) {
				unvisitedAirportsMinHeap.add(new AirportNode(flight.destination, flight.duration, origin));
				unvisitedAirportsHashMap.put(flight.destination, flight.duration);
				airportsAtFirstLevel.add(flight.destination);
			}
			for (Character airport : airports) {
				if (!airportsAtFirstLevel.contains(airport)) {
					unvisitedAirportsMinHeap.add(new AirportNode(airport, Integer.MAX_VALUE, '\0'));
					unvisitedAirportsHashMap.put(airport, Integer.MAX_VALUE);
				}
			}
		}		
		
		AirportNode currAirport = null;
		do {
			currAirport = unvisitedAirportsMinHeap.peek();
			ArrayList<Flight> flightsFromCurrNode = flights.get(currAirport.name);
			for (Flight flight : flightsFromCurrNode) {
				int newDuration = currAirport.duration + flight.duration;
				
				if (newDuration < unvisitedAirportsHashMap.get(flight.destination)) {
					unvisitedAirportsHashMap.put(flight.destination, newDuration);
					
					AirportNode updatedNode = new AirportNode(flight.destination, newDuration, currAirport.name);
					unvisitedAirportsMinHeap.remove(updatedNode);
					unvisitedAirportsMinHeap.add(updatedNode);
				}
			}
			visitedAirports.put(currAirport.name, unvisitedAirportsMinHeap.poll());
		}
		while (currAirport.name != destination && 
			  !unvisitedAirportsMinHeap.isEmpty() &&
			   unvisitedAirportsMinHeap.peek().duration < Integer.MAX_VALUE);
		
		String path = new String(String.valueOf(currAirport.name));

		while (currAirport.parent != origin) {
			path = String.valueOf(currAirport.parent) + "," + path;
			currAirport = visitedAirports.get(currAirport.parent);
		}
		path = String.valueOf(currAirport.parent) + "," + path;
		
		printer.print(path);
	}

	public static class QueueEntry {
		public char airport;
		public int level;
		
		public QueueEntry(char airport, int level) {
			this.airport = airport;
			this.level = level;
		}
		
	}
	
	public static void printDestinations(char origin, int numFlights) {
		     ArrayDeque<QueueEntry> queue = new ArrayDeque<QueueEntry>();
		       HashSet<Character> visited = new HashSet<Character>();
		ArrayList<Character> destinations = new ArrayList<Character>();
		
		queue.add(new QueueEntry(origin, 0));
		while (!queue.isEmpty()) {
			QueueEntry head = queue.poll();
			visited.add(head.airport);
			if (head.level == numFlights) {
				destinations.add(head.airport);
			}
			else {
				ArrayList<Flight> flightsFromCurrAirport = flights.get(head.airport);
				for (Flight flight : flightsFromCurrAirport) {
					if (!visited.contains(flight.destination)) {
						queue.add(new QueueEntry(flight.destination, 1 + head.level));
					}
				}
			}
		}
		Collections.sort(destinations);
		int numDestinations = destinations.size();
		for (int index = 0; index < numDestinations; index++) {
			printer.append(destinations.get(index));
			if (index < numDestinations -1) {
				printer.append(',');
			}
		}
	}
}
