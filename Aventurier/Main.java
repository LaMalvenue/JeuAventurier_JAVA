package Aventurier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Etape 1: Lire le fichier txt avec le Scanner
//Etape 2: Récupérer les coordonnées de l'utilisateur et les stocker dans un tableau (double dimensions ?)
//Etape 3: Créer une méthode qui empêche de commencer en dehors du cadre et sur un #
//Etape 4: Permettre à l'utisateur de rentrer les directions, utilisation du switch cases
//Etape 5: gérer le système de collision.


public class Main {

	public static void main(String[] args) {

		// Chemin du fichier
		String path = "/home/cristini/Bureau/carte.txt";
		Scanner scanner = new Scanner(System.in);
		// Chargement du fichier txt
		// Le fichier d'entrée
		File file = new File(path);
		// Créer l'objet File Reader
		try {
			FileReader fr = new FileReader(file);

			// Créer l'objet BufferedReader
			BufferedReader bufferReader = new BufferedReader(fr);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferReader.readLine()) != null) {
				// ajoute la ligne au buffer
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}

			System.out.println(stringBuffer.toString());

			// Récupération des coordonnées de départ
			int[] startCoordinates = getStartCoordinates();
			boolean testStartCoordinates = isRightArea(startCoordinates, file);

			if (testStartCoordinates) {
				System.out.println("Veuillez rentrer les directions");
				String direction = scanner.nextLine();
				
				boolean testDirection = getDirection(direction, startCoordinates,file);
				if (testDirection) {
					System.out.println("La coordonnée du personnage est: (" +startCoordinates[0] +"," + startCoordinates[1] +")");
                    /*if (stringBuffer.charAt(startCoordinates[0] | startCoordinates[1]) == '#') {
                        System.out.println("Attention, vous êtes sur un arbre");
                    }*/
                } 
                
			}
            scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int[] getStartCoordinates() {

		int[] coordinates = new int[2];
		Scanner scanner = new Scanner(System.in);
		// Récupération de la donnée x
		System.out.println("Veuillez rentrer les coordonnées x");
		coordinates[0] = scanner.nextInt();
		// Récupération de la donnée y
		System.out.println("Veuillez rentrer les coordonnées y");
		coordinates[1] = scanner.nextInt();
        
		return coordinates;
	}

    public static boolean isRightArea(int[] coordinates, File file){
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(filereader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            
            int lineIndex = 0;

			while ((line = bufferReader.readLine()) != null) {
				if (lineIndex == coordinates[1]) {
					// Calcul de la taille
					stringBuffer.append(line);
					lineIndex = stringBuffer.length();
                    
					if (coordinates[0] <= lineIndex) {

						if (stringBuffer.charAt(coordinates[0]) == '#') {
							System.out.println("Impossible de commencer à cette position");
							return false;
						} else {
                            System.out.println("Coordonnées de départ enregistrées");
							return true;
						}
					}
                    
				}
				lineIndex += 1;
			}
            
			filereader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("La coordonnée n'est pas située sur la carte");
		return false;
    }

	public static boolean getDirection(String direction, int[] coordinates,File file) {

        // Utiliser un char --> 1 caractère
		direction = direction.toUpperCase();

		for (int i = 0; i < direction.length(); i++) {
			char charDirection = direction.charAt(i);
			if (charDirection != 'N' && charDirection != 'S' && charDirection != 'E' && charDirection != 'O') {
				System.out.println("Veuillez renseigner une bonne direction E, S, N ou O");
				return false;
			} else {
				
				switch(charDirection) {
				case 'N':
                coordinates[1] -= 1;
					break;
				case 'S':
                coordinates[1] += 1;
					break;
				case 'E':
                coordinates[0] += 1;
					break;
				case 'O':
                coordinates[0] -= 1;
					break;
				}
			}
		}
		return true;
	}
}