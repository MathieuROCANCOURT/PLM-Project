
/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * 
 */
public class Plm {
	private static void displayPlanes(Hashtable<Integer, ArrayList<ArrayList<String>>> planes) {
		System.out.println("Voici la list de tous les avions.");
		for (Map.Entry<Integer, ArrayList<ArrayList<String>>> plane : planes.entrySet()) {
			ArrayList<String> planeData = plane.getValue().get(0);
			System.out.println("Id n°" + plane.getKey() + ": Avion " + planeData.get(0) + " qui est en "
					+ planeData.get(1) + " est destiné au " + planeData.get(2));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] phaseCurrently = { "étude de faisabilité", "conception", "définition", "construction", "en service",
				"clôturé" };
		Hashtable<Integer, ArrayList<ArrayList<String>>> planes = new Hashtable<>();

		ArrayList<ArrayList<String>> plane1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> plane2 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> plane3 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> plane4 = new ArrayList<ArrayList<String>>();

		plane1.add(new ArrayList<>(Arrays.asList("A320", phaseCurrently[0], "fret")));
		plane2.add(new ArrayList<>(Arrays.asList("A400M", phaseCurrently[2], "militaire")));
		plane3.add(new ArrayList<>(Arrays.asList("A300", phaseCurrently[3], "avions d'affaires")));
		plane4.add(new ArrayList<>(Arrays.asList("A380", phaseCurrently[5], "avions d'affaires")));

		// Adding elements
		planes.put(1, plane1);
		planes.put(2, plane2);
		planes.put(3, plane3);
		planes.put(4, plane4);

		displayPlanes(planes);
		;

	}

}
