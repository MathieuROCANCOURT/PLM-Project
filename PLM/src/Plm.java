
/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 
 */
public class Plm {
	private static void displayPlanes(Hashtable<Integer, ArrayList<String>> planes) {
		System.out.println("Voici la liste de tous les avions.");
		for (Map.Entry<Integer, ArrayList<String>> plane : planes.entrySet()) {
			ArrayList<String> planeData = plane.getValue();
			System.out.println("Id n°" + plane.getKey() + ": Avion " + planeData.get(0) + " qui est en "
					+ planeData.get(1) + " est destiné au " + planeData.get(2));
		}
	}

	private static void displayPlanes2(Hashtable<Integer, ArrayList<String>> planes) {
		System.out.println("Voici la liste des avions.");
		for (Map.Entry<Integer, ArrayList<String>> plane : planes.entrySet()) {
			System.out.println("Id n°" + plane.toString());
		}
	}

	private static boolean wantDisplay() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Voulez-vous voir la liste de tous les avions ? [o/n]");

		while (!(sc.hasNext("o") || sc.hasNext("n"))) {
			sc.next();
			System.out.print("Veuillez entrer 'o' ou 'n'.");
		}

		if (sc.hasNext("o")) {
			sc.close();
			return true;
		}
		sc.close();
		return false;
	}
	
	private static void searchKeyWordPlane(Hashtable<Integer, ArrayList<String>> planes, String keyWord) {
		Hashtable<Integer, ArrayList<String>> planesFilterKeyWord = new Hashtable<Integer, ArrayList<String>>();
		
		for (Map.Entry<Integer, ArrayList<String>> plane: planes.entrySet()) {
			if (plane.getValue().get(0).contains(keyWord)) {
				planesFilterKeyWord.put(plane.getKey(), plane.getValue());
			}
		}
		
		System.out.println("Application du mot clé " + keyWord + " à la liste.");
		displayPlanes(planesFilterKeyWord);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] phaseCurrently = { "étude de faisabilité", "conception", "définition", "construction", "en service",
				"clôturé" };
		Hashtable<Integer, ArrayList<String>> planes = new Hashtable<>();

		ArrayList<String> plane1 = new ArrayList<String>();
		ArrayList<String> plane2 = new ArrayList<String>();
		ArrayList<String> plane3 = new ArrayList<String>();
		ArrayList<String> plane4 = new ArrayList<String>();
		ArrayList<String> plane5 = new ArrayList<String>();
		ArrayList<String> plane6 = new ArrayList<String>();

		Collections.addAll(plane1, "A320", phaseCurrently[0], "fret");
		Collections.addAll(plane2, "A400M", phaseCurrently[2], "militaire");
		Collections.addAll(plane3, "A300", phaseCurrently[3], "affaire");
		Collections.addAll(plane4, "A380", phaseCurrently[5], "civil");
		Collections.addAll(plane5, "A380", phaseCurrently[1], "militaire");
		Collections.addAll(plane6, "A340", phaseCurrently[4], "affaire");

		// Adding elements to dictionary
		planes.put(1, plane1);
		planes.put(2, plane2);
		planes.put(3, plane3);
		planes.put(4, plane4);
		planes.put(5, plane5);
		planes.put(8, plane6);

		if (wantDisplay()) {
			displayPlanes(planes);
			displayPlanes2(planes);
		}
		
		searchKeyWordPlane(planes, "80");

	}

}
