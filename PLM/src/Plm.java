
/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */
public class Plm {
	private static void displayPlanes(Map<Integer, ArrayList<String>> planes) {
		System.out.println("Voici la liste de tous les avions.");

		planes.forEach((key, planeData) -> {
			System.out.println("Id n°" + key + ": Avion " + planeData.get(0) + " qui est en " + planeData.get(1)
					+ " est destiné au " + planeData.get(2));
			if (havePiecePlane(planeData)) {
				Piece.displayPieces(planeData.subList(3, planeData.size()));
			}
			System.out.println("--------------------------------------------");
		});
	}

	private static void displayPlanes2(Map<Integer, ArrayList<String>> planes) {
		System.out.println("Voici la liste des avions.");
		for (int index : planes.keySet()) {
			System.out.println("Id n°" + index + " :" + planes.get(index).toString());
		}
	}

	private static boolean wantDisplay(Scanner sc) {
		System.out.print("Voulez-vous voir la liste de tous les avions ? [o/n]");
		String inputUser = sc.nextLine();

		while (!(inputUser.equalsIgnoreCase("o") || inputUser.equalsIgnoreCase("n"))) {
			System.out.print("Veuillez entrer 'o' ou 'n'.");
			inputUser = sc.nextLine();
		}

		return inputUser.equalsIgnoreCase("o");
	}

	private static void searchKeyWordPlane(Map<Integer, ArrayList<String>> planes, String keyWord) {
		Map<Integer, ArrayList<String>> planesFilterKeyWord = new HashMap<>();

		planes.forEach((key, planeData) -> {
			if (planeData.get(0).contains(keyWord)) {
				planesFilterKeyWord.put(key, planeData);
			}
		});

		System.out.println("Application du mot clé " + keyWord + " à la liste.");
		displayPlanes(planesFilterKeyWord);
	}

	private static int checkId(Scanner sc, Map<Integer, ArrayList<String>> planes, boolean isAdd) {
		System.out.print("À quelle identifiant voulez-vous mettre ? ");

		try {
			int index = Integer.parseInt(sc.nextLine());

			if (planes.containsKey(index)) {
				ArrayList<String> planeData = planes.get(index);

				if (isAdd || havePiecePlane(planeData)) {
					return index;
				}
				System.err.println("Cette avion n'a pas de pièce.");
				return -1;

			} else {
				System.err.println("Le numéro de l'identifiant n'est pas présent.");
			}
		} catch (Exception e) {
			System.err.println("Erreur de saisie, cela doit être un nombre entier.");
		}
		return -1;
	}

	private static boolean havePiecePlane(ArrayList<String> dataPlane) {
		return dataPlane.size() > 3;
	}

	private static boolean canRemovePiece(Map<Integer, ArrayList<String>> planes) {
		for (ArrayList<String> dataPlane : planes.values()) {
			if (havePiecePlane(dataPlane)) {
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

	private static void addRemoveMultiplePieces(Scanner sc, Map<Integer, ArrayList<String>> planes,
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
						index = checkId(sc, planes, isAddPiece);
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
		Map<Integer, ArrayList<String>> planes = new HashMap<Integer, ArrayList<String>>();

		// Adding elements to dictionary
		planes.put(1, new ArrayList<>(Arrays.asList("A320", phaseCurrently[0], "fret")));
		planes.put(2, new ArrayList<>(Arrays.asList("A400M", phaseCurrently[2], "militaire")));
		planes.put(3, new ArrayList<>(Arrays.asList("A300", phaseCurrently[3], "affaire")));
		planes.put(4, new ArrayList<>(Arrays.asList("A380", phaseCurrently[5], "civil")));
		planes.put(5, new ArrayList<>(Arrays.asList("A380", phaseCurrently[1], "militaire")));
		planes.put(8, new ArrayList<>(Arrays.asList("A340", phaseCurrently[4], "affaire")));

		if (wantDisplay(sc)) {
			displayPlanes(planes);
			displayPlanes2(planes);
		}

		searchKeyWordPlane(planes, "80");
		addRemoveMultiplePieces(sc, planes, true);
		displayPlanes(planes);
		addRemoveMultiplePieces(sc, planes, false);

		if (wantDisplay(sc)) {
			displayPlanes(planes);
		}

		sc.close();
	}
}
