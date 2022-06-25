package Aventurier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
            scanner.close();
            bufferedReader.close();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}