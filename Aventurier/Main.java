package Aventurier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Etape 1: Lire le fichier txt 
//Etape 2: Récupérer les coordonnées de l'utilisateur avec le Scanner et les stocker dans un tableau (deux dimensions ?)
//Etape 3: Créer une méthode qui empêche de commencer en dehors du cadre et sur un #
//Etape 4: Permettre à l'utisateur de rentrer les directions, utilisation du switch case
//Etape 5: Gérer le système des collisions

public class Main {

    private static final String path = "/home/arouviere/Bureau/carte.txt";
    private static final File file = new File(path);

    private static boolean playAgain = true;

    public static void main(String[] args) {
        do {
            try (Scanner scanner = new Scanner(System.in)) {
                FileReader fr = new FileReader(file);
                BufferedReader bufferReader = new BufferedReader(fr);
                StringBuffer stringBuffer = new StringBuffer();
                String line;

                while ((line = bufferReader.readLine()) != null) {
                    // ajoute la ligne au buffer
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
                }
                bufferReader.close();
                System.out.println(stringBuffer);

                // Récupération des coordonnées de départ
                int[] startCoordinates = getStartCoordinates();

                if (isStartCoordinate(startCoordinates)) {
                    System.out.println("Veuillez rentrer les directions");
                    String direction = scanner.nextLine();

                    if (getDirection(direction, startCoordinates)) {
                        System.out.println("La coordonnée du personnage est: (" + startCoordinates[0] + "," + startCoordinates[1] + ")");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (playAgain);
    }

    public static int[] getStartCoordinates() {
        int[] coordinates = new int[2];
        Scanner scanner = new Scanner(System.in);

        // Récupération de la donnée x
        System.out.println("Veuillez rentrer les coordonnées x :");
        coordinates[0] = scanner.nextInt();
        // Récupération de la donnée y
        System.out.println("Veuillez rentrer les coordonnées y :");
        coordinates[1] = scanner.nextInt();

        scanner.close();
        return coordinates;
    }

    public static boolean isStartCoordinate(int[] coordinates) {
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
                            System.out.println("Impossible de commencer à cette position, vous êtes sur un arbre !");
                            return false;
                        } else {
                            System.out.println("Coordonnées de départ enregistrées !");
                            return true;
                        }
                    }
                    bufferReader.close();
                }
                lineIndex += 1;
            }
            filereader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("La coordonnée n'est pas située sur la carte, veuillez recommencer !");
        return false;
    }

    public static boolean getDirection(String direction, int[] coordinates) {
        // Utiliser un char --> 1 caractère
        direction = direction.toUpperCase();

        for (int i = 0; i < direction.length(); i++) {
            char charDirection = direction.charAt(i);
            if (charDirection != 'N' && charDirection != 'S' && charDirection != 'E' && charDirection != 'O') {
                System.out.println("Veuillez renseigner une bonne direction : 'E', 'S', 'N' ou 'O'");
                return false;
            } else {
                switch (charDirection) {
                    case 'N' : coordinates[1] -= 1;
                    case 'S' : coordinates[1] += 1;
                    case 'E' : coordinates[0] += 1;
                    case 'O' : coordinates[0] -= 1;
                }

                if (collision(coordinates)) {
                    System.out.println("Vous ne pouvez entrer dans la forêt impénétrable, restez sur votre case");
                    switch (charDirection) {
                        case 'N' : coordinates[1] += 1;
                        case 'S' : coordinates[1] -= 1;
                        case 'E' : coordinates[0] -= 1;
                        case 'O' : coordinates[0] += 1;
                    }
                }
            }
        }
        return true;
    }

    public static boolean collision(int[] coordinates) {
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(filereader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            int lineIndex = 0;

            while ((line = bufferReader.readLine()) != null) {
                if (lineIndex == coordinates[1]) {
                    // Calcul de la taille
                    stringBuilder.append(line);
                    lineIndex = stringBuilder.length();

                    if (coordinates[0] <= lineIndex) {
                        return stringBuilder.charAt(coordinates[0]) == '#';
                    }
                    bufferReader.close();
                }
                lineIndex += 1;
            }

            filereader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}