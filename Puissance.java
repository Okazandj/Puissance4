class Puissance

{
		// Initialisation du plateau
	public static void initialisation (int [][] plateau)
	{
		for (int nbColonne = 0; nbColonne<plateau.length; nbColonne++)
		{
			for (int nbLigne = 0; nbLigne < plateau[0].length; nbLigne++)
			{
				plateau[nbColonne][nbLigne] = 0;
			}
		}
	}
		//Permet de déterminer qui commence entre le Joueur et IA
	public static int quicommence ()
	{
		int tour = 0;
		double j = Math.random()*(100-0)+0;
		double ai = Math.random()*(100-0)+0;
		if ( ai <= j)
			tour = 0;
		if ( ai > j)
			tour = 1;
		return tour;
	}
		// Choix du mode de jeu
	public static int mode()
	{
		int mode;
		do {
			System.out.println("Veuillez entrer 1 pour allez en mode Joueur1 contre Joueur2");
			System.out.println("Veuillez entrer 2 pour aller en mode Joueur1 contre IA");
			System.out.println("Veuillez entrer 3 pour allez en mode spectateur d'une partie IA contre IA");
			mode = Saisie.litentier();
		} while (mode > 4);
		return mode;
	}

		// Demande la colonne où le joueur veut jouer et gère la case
	public static int demandecolonne (int [][] plateau)
	{
		int colonne = 0;
		do {
			System.out.println("Choisis la colonne où tu vas placer ton jeton");
			String position = Saisie.litexte();

			position = position.toUpperCase();
			colonne = ((int)position.charAt(0)-65);

			// Reinitialisation car colonne remplie
			if(colonne >= 0 && colonne <= 6)
			{
				if (plateau[colonne][0] != 0)
				{
					colonne = -1;
					System.out.println("Colonne pleine, veuillez choisir une autre colonne");
				}			
			}
		} while (colonne < 0 || colonne > 6);
		return colonne;
	}

		// Place aléatoirement un jeton dans une colonne et gère si la colonne est remplie
	public static int colonneIA(int [][] plateau)
	{
		int colonne=1;
		do {
			colonne =(int)(Math.random()*(7-0))+0;
			if (plateau[colonne][0] != 0)
				colonne = -1;
		} while(colonne < 0);
		
		return colonne;
	}
		// vérifie les dimensions, puis vérifie à gauche et à droite du nouveau jeton, s'arrête de compter si le jeton de gauche ou de droite n'est pas un jeton du joueur 
	public static int horizontale (int [][]plateau, int colonne, int ligne, final int Joueur)
	{
		boolean gaucheb = true;
		boolean droiteb = true;

		int compteurexecution = 0;
		int gauche = 1;
		int compteurD = 0;
		int droite = 1;
		int compteurG = 0;
		int compteur = 0;

		do{
			if (colonne-gauche>0)
			{
				if (plateau[colonne-gauche][ligne] == Joueur && gaucheb == true)
				{
					compteurG++;
					gauche++;
				}
				else
					gaucheb = false;
			}
			if (colonne+droite < 7)
			{
				if (plateau[colonne+droite][ligne] == Joueur && droiteb == true)
				{
					droite++;
					compteurD++;
				}
				else
					droiteb = false;
			}
			compteurexecution++;
		} while ( compteurexecution < 3);

	compteur = compteurD + compteurG;
	return compteur;
	}
		// vérifie si appartient au tableau, puis vérifie le bas du jeton, si c'est différent du joueur alors le compteur s'arrête de compter
	public static int verticale (int [][]plateau, int colonne, int ligne, final int Joueur)
	{
		int compteurB = 0;
		boolean stop = false;

		for (int i = 1; i < 4; i++)
		{
			if (ligne+i< 6)
			{
				if (plateau[colonne][ligne+i] == Joueur && stop == false)
					compteurB++;
				else
					stop = true;
			}
		}
		return compteurB;
	}
		// Fonction qui additionne les diagonales bas gauche et haut droit; haut gauche et bas droite
	public static int diagonale(int [][]plateau, int colonne, int ligne, final int Joueur)
	{
		int compteurBG = diagonaleC(plateau, colonne, ligne, Joueur, -1, 1);
		int compteurHD = diagonaleC(plateau, colonne, ligne, Joueur, 1, -1);
		// Pour avoir les mêmes chiffres de victoire dans tous les cas de victoire, je n'ai pas compter le jeton poser donc soustrait par 2
		int compteurBGHD = compteurBG + compteurHD-2;
		
		int compteurHG = diagonaleC(plateau, colonne, ligne, Joueur, -1, -1);
		int compteurBD = diagonaleC(plateau, colonne, ligne, Joueur, 1, 1);

		int compteurHGBD = compteurHG + compteurBD-2;

		if (compteurHGBD >= compteurBGHD)
			return compteurHGBD;
		else
			return compteurBGHD;
	}

		// Fonction diagonale qui gère tous les possibilités de diagonale grâce aux coefficiants
	public static int diagonaleC(int [][] plateau, int colonne , int ligne, final int Joueur, int a, int b)
	{
		int compteur = 0;
		int k =0;
		boolean fin = false;

		do
		{
			if ((0 <= colonne+k*a) && (colonne+k*a < 7) && (0 <= ligne+k*b)  && (ligne+k*b < 6))
			{
				if (plateau[colonne +k*a][ligne +k*b] == Joueur && fin == false)
					compteur++;
				else
					fin = true;
			}
			k++;
		} while (k < 4);
		return compteur;
	}
	// Pose le jeton en le voyant tomber
	public static void posejeton(int [][] plateau, int colonne, int ligne, final int Joueur, Puissance4GUI gui)
	{
		for (int l = 0; l <= ligne; l++)
		{
			plateau[colonne][l] = Joueur;
			gui.rafraichirCase(colonne,l);
			if (l > 0)
				gui.rafraichirCase(colonne, l-1);
			if (l != ligne)
				plateau[colonne][l] = 0;
			try {
				Thread.currentThread().sleep(200);
			}
			catch (InterruptedException ie){}
		}
	}

	
		// Trouve la ligne où doit être placé le pion
	public static int Ligne (int [][] plateau, int colonne)
	{
		int ligne = 0;
		boolean exe = false;
		do {
			if (plateau[colonne][ligne] != 0)
			{
				exe = true;
				ligne--;
				return ligne;
			}
			if (plateau[colonne][5] == 0)
				return ligne = 5;
			ligne++;
		} while (exe != true);
		return ligne;
	}

	public static void main (String [] args)
	{
		boolean recommencer;

		int nbColonne = 7;
		int nbLigne = 6;
		int [][] plateau = new int [nbColonne][nbLigne];
		initialisation(plateau);
		Puissance4GUI gui = new Puissance4GUI(plateau);

		int colonne;
		int ligne;
			// Utilité pour se protéger en verticale IA
		int colonnesave = 0;
		int lignesave = 0;
			// Utilise pour gagner en verticale IA
		boolean prochevictoire = false;
		int colonnevictoire = 0;
		int lignevictoire = 0;

		final int Joueur1 = 1;
		final int Joueur2 = 2;

		do {
			int tour = 0;
			boolean fin = false;
			int V1 = 0;
			int V2 = 0;
			int V3 = 0;
			recommencer = false;
			// Remise à zero du jeu, utile si le joueur veut recommencer
			initialisation(plateau);
			for (int i = 0; i < 7 ;i++)
			{
				for (int j= 0; j < 6; j++)
				{
					gui.rafraichirCase(i,j);
				}
			}
			int mode = mode();
		
			// Joueur contre Joueur //
			if (mode == 1)
			{
				gui.modifierMessage("c'est parti !");
				System.out.println("Veuillez entrer le nom du joueur qui commencera");
				String J1 = Saisie.litexte();
				System.out.println("Veuillez entrer le nom du second joueur");
				String J2 = Saisie.litexte();

				do {
					if (fin == false)
					{
						gui.modifierMessage("Le tour de "+J1);

						colonne = demandecolonne(plateau);
						ligne = Ligne(plateau, colonne);

						posejeton(plateau, colonne, ligne, Joueur1, gui);
					
						V1 = horizontale(plateau, colonne, ligne, Joueur1);
						V2 = verticale(plateau, colonne, ligne, Joueur1);
						V3 = diagonale(plateau, colonne, ligne, Joueur1);
				// Vérification de victoire
						if (V1 >= 3 || V2 >= 3 || V3 >= 3)
						{
							System.out.println(J1+" a gagné");
							gui.modifierMessage( J1+" a gagné");
							fin = true;
						}
						tour++;
					}
					if (fin == false)
					{
						gui.modifierMessage("Le tour de "+J2);

						colonne = demandecolonne(plateau);
						ligne = Ligne(plateau, colonne);
					
						posejeton(plateau, colonne, ligne, Joueur2, gui);

						V1 = horizontale (plateau, colonne, ligne, Joueur2);
						V2 = verticale(plateau, colonne, ligne, Joueur2);
						V3 = diagonale(plateau, colonne, ligne, Joueur2);

						if (V1 >= 3 || V2 >= 3 || V3 >= 3 && fin == false)
						{
							gui.modifierMessage( J2+" a gagné");
							fin = true;
						}				
					
						tour ++;
				
						if (tour == 42)
						{
							System.out.println("Match nul");
							fin = true;
						}
					}
				} while (fin != true);
			}

			//Joueur contre IA (gère le blocage en verticale du joueur adverse, ainsi que sa victoire en verticale sinon joue aléatoirement)
			if (mode == 2)
			{
				gui.modifierMessage("c'est parti !");
				System.out.println("Veuillez entrer votre nom");
				String J1 = Saisie.litexte();
		
				tour = quicommence();
				int compteurN;

				do {
					compteurN = 0;
					if (tour%2 == 0)
					{
						if (fin == false)
						{
							gui.modifierMessage("Le tour de "+J1);
						
							colonne = demandecolonne(plateau);
							ligne = Ligne(plateau, colonne);
							
							posejeton(plateau, colonne, ligne, Joueur1, gui);
						
							V1 = horizontale(plateau, colonne, ligne, Joueur1);
							V2 = verticale(plateau, colonne, ligne, Joueur1);
							V3 = diagonale(plateau, colonne, ligne, Joueur1);
						// Sauvegarde la colonne si le joueur adverse aligne 3 jetons en verticale et si la colonne n'est pas remplie
							if (V2 == 2 && ligne != 0 && plateau[colonne][ligne-1] == 0)
							{
								colonnesave = colonne;
								lignesave = ligne;
							}
				
							if (V1 >= 3 || V2 >= 3 || V3 >= 3)
							{
								gui.modifierMessage( J1+" a gagné");
								fin = true;
							}
							tour++;
						}
					}
					
					else
					{
						if (fin == false)
						{
							gui.modifierMessage("Le tour de l'ordinateur ");
						//Bloque
							if (V2 == 2 && lignesave != 0)
								colonne = colonnesave;
							else
								colonne = colonneIA(plateau);
						//Gagne, ordre important
							// si condition victoire et que la ligne du dessus est libre alors joue et remet à zero condition
							if (prochevictoire == true && plateau[colonnevictoire][lignevictoire-1] == 0)
							{
								colonne = colonnevictoire;
								prochevictoire = false;
							}
							else
								prochevictoire = false;

							ligne = Ligne(plateau, colonne);

							posejeton(plateau, colonne, ligne, Joueur2, gui);
			
							V1 = horizontale (plateau, colonne, ligne, Joueur2);
							V2 = verticale(plateau, colonne, ligne, Joueur2);
							V3 = diagonale(plateau, colonne, ligne, Joueur2);
			
							if (V1 >= 3 || V2 >= 3 || V3 >= 3)
							{
								gui.modifierMessage(" L'ordinateur a gagné");
								fin = true;
							}
							tour++;
					// Verticale: Si condition de victoire et colonne pas remplie, sauvegarde des positions et la condition
							if (V2 == 2 && ligne != 0)
							{
								prochevictoire = true;
								colonnevictoire = colonne;
								lignevictoire = ligne;
							}
						}
					}
			// Condition de match nul différente car tour = 1 ou tour = 0; compte les colonnes pleines si toutes les colonnes sont pleines alors stop
					if (tour > 41)
					{
						for (int col=0; col < 7; col++)
						{
							if (plateau[col][0] != 0)
								compteurN++;
						}
						if (compteurN == 7)
						{
							fin = true;
							System.out.println("Match nul");
						}
					}
				} while ( fin != true);
			}

			// IA contre IA, 100% aléatoire
			if (mode == 3) 
			{
				do {
					if (fin == false)
					{
						gui.modifierMessage("Le tour de l'ordinateur1 ");

						colonne = colonneIA(plateau);
						ligne = Ligne(plateau, colonne);
					
						posejeton(plateau, colonne, ligne, Joueur1, gui);
			
						V1 = horizontale (plateau, colonne, ligne, Joueur1);
						V2 = verticale(plateau, colonne, ligne, Joueur1);
						V3 = diagonale(plateau, colonne, ligne, Joueur1);
			
						if (V1 >= 3 || V2 >= 3 || V3 >= 3)
						{
							gui.modifierMessage(" L'ordinateur1 a gagné");
							fin = true;
						}				
					}
					tour ++;
					
					if (fin == false)
					{ 
						gui.modifierMessage("Le tour de l'ordinateur2 ");

						colonne = colonneIA(plateau);
						ligne = Ligne(plateau, colonne);
						
						posejeton(plateau, colonne, ligne, Joueur2, gui);
			
						V1 = horizontale (plateau, colonne, ligne, Joueur2);
						V2 = verticale(plateau, colonne, ligne, Joueur2);
						V3 = diagonale(plateau, colonne, ligne, Joueur2);
			
						if (V1 >= 3 || V2 >= 3 || V3 >= 3)
						{
							gui.modifierMessage(" L'ordinateur2 a gagné");
							fin = true;
						}				
					}

					tour ++;
					if (tour == 42)
					{
						System.out.println("Match nul");
						fin = true;
					}
				} while (fin != true);

			}
		System.out.println("Voulez-vous recommencez ?");
		System.out.println("Si oui, tappez 1.");
		System.out.println("Sinon fermer la fenêtre.");
		int s = Saisie.litentier();
		if (s == 1)
			recommencer = true;
		} while (recommencer = true); 
	}
}