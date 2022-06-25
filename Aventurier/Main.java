package Aventurier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Etape 1: lire le fichier txt.
//Etape 2: Récupérer les coordonnées et les mettre dans un tableau (double dimensions ?)
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
			FileReader filereader = new FileReader(file);

			// Créer l'objet BufferedReader
			BufferedReader bufferedReader = new BufferedReader(filereader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// ajoute la ligne au buffer
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}

			System.out.println(stringBuffer);

            //Récupération des données de départ
            int[] startCoordinates = getStartCoordinates();
            scanner.close();
            bufferedReader.close();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

    public static int[] getStartCoordinates()
    {
        int[] coordinates = new int[2];
        Scanner scanner = new Scanner(System.in);
        //Récupération donnée x
        System.out.println("Veuillez rentrer les coordonnées x :");
        coordinates[0] = scanner.nextInt();
        //Récupération donnée y
        System.out.println("Veuillez rentrer les coordonnées y :");
        coordinates[1] = scanner.nextInt();
        scanner.close();
        return coordinates;
    }
}