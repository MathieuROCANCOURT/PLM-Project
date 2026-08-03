
/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 
 */
public class Plm {
	enum Phase {
		ETUDE_DE_FAISABILITE, CONCEPTION, DEFINITION, CONSTRUCTION, EN_SERVICE, CLOTURE
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hashtable<Integer, ArrayList<ArrayList<String>>> planes = new Hashtable<>();

		ArrayList<ArrayList<String>> plane1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> plane2 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> plane3 = new ArrayList<ArrayList<String>>();
		
		plane1.add(new ArrayList<>(Arrays.asList("A320", Plm.Phase.ETUDE_DE_FAISABILITE.toString(), "fret")));
		plane2.add(new ArrayList<>(Arrays.asList("A400M", Plm.Phase.DEFINITION.toString(), "militaire")));
		plane3.add(new ArrayList<>(Arrays.asList("A300", Plm.Phase.CONSTRUCTION.toString(), "avions d'affaires")));

		// Adding elements
		planes.put(1, plane1);
		planes.put(2, plane2);
		planes.put(3, plane3);

		System.out.print(planes);

	}

}
