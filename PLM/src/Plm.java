
/**
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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
			if (planeData.size() > 3) {
				displayPieces(planeData.subList(3, planeData.size()));
			}
		}
	}

	private static void displayPlanes2(Hashtable<Integer, ArrayList<String>> planes) {
		System.out.println("Voici la liste des avions.");
		for (Map.Entry<Integer, ArrayList<String>> plane : planes.entrySet()) {
			System.out.println("Id n°" + plane.toString());
		}
	}
	
	private static void displayPieces(List<String> pieces) {
		System.out.println("Voici la liste des pièces:");
		for (String piece: pieces) {
			System.out.println(piece);
		}
	}

	private static boolean wantDisplay(Scanner sc) {
		System.out.print("Voulez-vous voir la liste de tous les avions ? [o/n]");

		while (!(sc.hasNext("o") || sc.hasNext("n"))) {
			sc.nextLine();
			System.out.print("Veuillez entrer 'o' ou 'n'.");
		}

		return sc.nextLine().equals("o");
	}

	private static void searchKeyWordPlane(Hashtable<Integer, ArrayList<String>> planes, String keyWord) {
		Hashtable<Integer, ArrayList<String>> planesFilterKeyWord = new Hashtable<Integer, ArrayList<String>>();

		for (Map.Entry<Integer, ArrayList<String>> plane : planes.entrySet()) {
			if (plane.getValue().get(0).contains(keyWord)) {
				planesFilterKeyWord.put(plane.getKey(), plane.getValue());
			}
		}

		System.out.println("Application du mot clé " + keyWord + " à la liste.");
		displayPlanes(planesFilterKeyWord);
	}

	private static boolean checkProgram(String pieceUser, boolean addOrRemove, Scanner sc, Hashtable<Integer, ArrayList<String>> planes) {
		System.out.print("À quelle identifiant voulez-vous mettre ?");
		try {
			int index = Integer.parseInt(sc.nextLine());
			if (planes.containsKey(index)) {
				if (addOrRemove) {
					planes.get(index).add(pieceUser);
					return true;
				}
				if (planes.get(index).size() < 4) {
					System.err.println("Cette avion n'a pas de pièce.");
					return false;
				}
				if (planes.get(index).contains(pieceUser)) {
					planes.get(index).remove(pieceUser);
					return true;
				}
				System.err.print("La pièce n'est pas dans la liste");
				return false;
			} else {
				System.err.println("Le numéro de l'identifiant n'est pas présent.");
			}
		} catch (Exception e) {
			System.err.println("Erreur de saisie, cela doit être un nombre entier.");
		}
		return false;
	}
	
	private static boolean canRemovePiece(Hashtable<Integer, ArrayList<String>> planes) {
		for (Map.Entry<Integer, ArrayList<String>> plane: planes.entrySet()) {
			if (plane.getValue().size() > 3) {
				return true;
			}
		}
		return false;
	}

	private static void addOrRemovePieces(Scanner sc, Hashtable<Integer, ArrayList<String>> planes, boolean isAddPiece) {
		Piece shopPiece = new Piece();
		boolean wantAddPiece = true;
		String addOrRemove = isAddPiece ? "ajouter" : "retirer";

		while (wantAddPiece) {
			if (!isAddPiece && !canRemovePiece(planes)) {
				System.out.println("Toutes les avions n'ont pas de pièces.");
				wantAddPiece = false;
			} else {
				System.out.print("Voulez-vous " + addOrRemove + " une pièce à un avion ?[O/n]");
				String responseUser = sc.nextLine().trim();
	
				if (responseUser.isEmpty() || responseUser.equalsIgnoreCase("O")) {
					String pieceUser = "";
					while (!shopPiece.isInShop(pieceUser)) {
						System.out.print("Quelle pièce voulez-vous " + addOrRemove + " ajouter ?");
						pieceUser = sc.nextLine();
					}
	
					while (!checkProgram(pieceUser, isAddPiece, sc, planes));
	
				} else if (responseUser.equalsIgnoreCase("n")) {
					wantAddPiece = false;
				} else {
					System.err.println("La saisie n'est pas valide.");
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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

		if (wantDisplay(sc)) {
			displayPlanes(planes);
			//displayPlanes2(planes);
		}

		// searchKeyWordPlane(planes, "80");
		addOrRemovePieces(sc, planes, true);
		displayPlanes(planes);
		addOrRemovePieces(sc, planes, false);

		if (wantDisplay(sc)) {
			displayPlanes(planes);
		}

		sc.close();
	}

}
