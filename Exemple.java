public class Exemple 
{
    //Initialisation de la matrice de donnees
    public static void initPlateau(int[][] matrice) 
    {
		for(int col = 0 ; col < matrice.length ; col++)
		{
			for(int lig = 0 ; lig < matrice[0].length ; lig++)
			{
				matrice[col][lig] = 0;
			}
		}
    }
	

	
    public static void main(String[] argv) 
    {
		
	int nbColonnes = 7; // <=> largeur, abscisse (x)
	int nbLignes = 6; // <=> hauteur, ordonnee (y)
	int[][] plateau = new int[nbColonnes][nbLignes];

	final int JOUEUR_1 = 1;
	final int JOUEUR_2 = 2;
		
	//Initialisation des cases du plateau � 0
	initPlateau(plateau);

	//Creation et initialisation de l'interface graphique
	Puissance4GUI gui = new Puissance4GUI(plateau);

	//Affichage dans la zone de notification
	gui.modifierMessage("c'est parti !");

		
	System.out.println("Appuyer sur ENTREE pour continuer");
	Saisie.litexte();

	//Modification de la valeur de la case colonne 0 ligne 0
	plateau[0][0]=JOUEUR_1;

	//Mise a jour de l'interface graphique
	gui.rafraichirCase(0,0);

	System.out.println("Appuyer sur ENTREE pour continuer");
	Saisie.litexte();

	//Modification de la valeur de la case colonne 4 ligne 0
	plateau[4][0]=JOUEUR_2;

	//Mise a jour de l'interface graphique
	gui.rafraichirCase(4,0);

	System.out.println("Fin fermer la fenetre");
    }
}
