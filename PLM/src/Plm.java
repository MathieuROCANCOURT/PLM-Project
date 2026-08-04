
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
		for (String piece : pieces) {
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

	private static int checkId(boolean isRemove, Scanner sc, Hashtable<Integer, ArrayList<String>> planes) {
		System.out.print("À quelle identifiant voulez-vous mettre ? ");
		try {
			int index = Integer.parseInt(sc.nextLine());
			if (planes.containsKey(index)) {
				ArrayList<String> planeData = planes.get(index);
				if (!isRemove) {
					return index;
				}
				if (!canRemovePiecePlane(planeData)) {
					System.err.println("Cette avion n'a pas de pièce.");
					return -1;
				}
				System.err.println("La pièce n'est pas dans la liste");
				return -1;
			} else {
				System.err.println("Le numéro de l'identifiant n'est pas présent.");
			}
		} catch (Exception e) {
			System.err.println("Erreur de saisie, cela doit être un nombre entier.");
		}
		return -1;
	}

	private static boolean canRemovePiecePlane(ArrayList<String> dataPlane) {
		return dataPlane.size() > 3;
	}

	private static boolean canRemovePiece(Hashtable<Integer, ArrayList<String>> planes) {
		for (Map.Entry<Integer, ArrayList<String>> plane : planes.entrySet()) {
			if (canRemovePiecePlane(plane.getValue())) {
				return true;
			}
		}
		return false;
	}

	private static void addRemovePiece(Scanner sc, ArrayList<String> dataPlane, boolean isAddPiece) {
		Piece shopPiece = new Piece();
		String pieceUser = "";
		String addOrRemove = isAddPiece ? "ajouter" : "retirer";
		boolean actToAddOrRemove = false;

		while (!actToAddOrRemove) {
			System.out.print("Quelle pièce voulez-vous " + addOrRemove + " ? ");
			pieceUser = sc.nextLine();
			if (!isAddPiece) {
				if (dataPlane.contains(pieceUser)) {
					dataPlane.remove(pieceUser);
					actToAddOrRemove = true;
				} else {
					System.err.println("Votre saisie n'est pas dans la liste des pièces de l'avion.");
				}
			} else {
				if (shopPiece.isInShop(pieceUser)) {
					dataPlane.add(pieceUser);
					actToAddOrRemove = true;
				} else {
					System.err.println("Votre saisie n'est pas dans la liste des pièces.");
				}
			}
		}
	}

	private static void addRemoveMultiplePieces(Scanner sc, Hashtable<Integer, ArrayList<String>> planes,
			boolean isAddPiece) {
		boolean wantAddOrRemovePiece = true;
		String addOrRemove = isAddPiece ? "ajouter" : "retirer";

		while (wantAddOrRemovePiece) {
			// No pieces of all planes and step to remove piece.
			if (!isAddPiece && !canRemovePiece(planes)) {
				System.out.println("Toutes les avions n'ont pas de pièces.");
				wantAddOrRemovePiece = false;
			} else {
				System.out.print("Voulez-vous " + addOrRemove + " une pièce à un avion ?[O/n] ");
				String responseUser = sc.nextLine().trim();

				if (responseUser.isEmpty() || responseUser.equalsIgnoreCase("O")) {
					int index;
					do {
						index = checkId(isAddPiece, sc, planes);
					} while (index == -1);

					addRemovePiece(sc, planes.get(index), isAddPiece);

				} else if (responseUser.equalsIgnoreCase("n")) {
					wantAddOrRemovePiece = false;
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
			// displayPlanes2(planes);
		}

		// searchKeyWordPlane(planes, "80");
		addRemoveMultiplePieces(sc, planes, true);
		displayPlanes(planes);
		addRemoveMultiplePieces(sc, planes, false);

		if (wantDisplay(sc)) {
			displayPlanes(planes);
		}

		sc.close();
	}
}
