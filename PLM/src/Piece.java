/**
 * 
 */

/**
 * 
 */
public class Piece {
	String piecesAirbus = "Airbus";
	String piecesInstruments = "Instruments";
	String piecesDiversCategory = "Pièces diverses";
	
	
	String[][] shop = {
		{"Tuyères (x2)", "450", piecesAirbus},
		{"Vanne de Réglage", "150", piecesAirbus},
		{"Verins de train", "150", piecesAirbus},
		{"Capots moteurs", "100", piecesAirbus},
		{"Switch", "90", piecesAirbus},
		{"Poignée de Gaz", "150", piecesAirbus},
		{"Bonbonne air", "90", piecesAirbus},
		{"Planche de bord fouga", "1200", piecesInstruments},
		{"Manomètre d'admission", "35", piecesInstruments},
		{"Tachymère", "39", piecesInstruments},
		{"Anémomètre (ASI)", "49", piecesInstruments},
		{"6 Jambes de train", "200", piecesInstruments},
		{"Renvoi sécat", "35", "Breguet 'Deux ponts'"},
		{"Filtre à huile", "30", piecesDiversCategory},
		{"Pièces dassault, etc", "30", piecesDiversCategory},
		{"Connecteurs", "15", piecesDiversCategory},
		{"Pièces alu", "5", piecesDiversCategory},
	};
	
	protected String[][] getShopPieces() {
		return shop;
	}
	
	protected boolean isInShop(String wordPiece) {
		for (String[] piece: shop) {
			if (piece[0].equalsIgnoreCase(wordPiece)) {
				return true;
			}
		}
		return false;
	}
}
