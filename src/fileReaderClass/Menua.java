package fileReaderClass;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menua {

	private static Scanner scanner = new Scanner(System.in);
	private static File fitxategia;
	private static ArrayList<Pelikula> pelikula = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		fitxategia = FitxategiKudeatzailea.fitxategiaSortu(scanner);
		FitxategiKudeatzailea.pelikulaKargatu(pelikula);

		if (fitxategia != null) {
			menuaHasi();
		} else {
			System.out.println("Errorea fitxategia sortzean. Ezin da jarraitu.");
		}
	}

	private static void menuaHasi() throws IOException {

		while (true) {
			System.out.println("\nMenua:");
			System.out.println("1. Fitxategia bete taulako datuekin");
			System.out.println("2. Fitxategia hustu");
			System.out.println("3. Erregistro kopurua zenbatu");
			System.out.println("4. Kode bidez bilatu erregistro bat");
			System.out.println(
					"5. Bilatu pelikulak generoaren arabera ('Suspense', 'Drama', 'Romantica', 'Comedia', 'Aventura', 'Fantasia')");
			System.out.println("6. Erregistro berria sartu");
			System.out.println("7. Erregistroa ezabatu");
			System.out.println("8. Fitxategiaren kopia sortu");
			System.out.println("9. Erregistroa aldatu");
			System.out.println("Zure aukera sartu: ");

			int aukera = -1;
			try {
				aukera = scanner.nextInt();
				scanner.nextLine();

				switch (aukera) {
				case 1:
					FitxategiEragiketak.fitxategiaBete(fitxategia, pelikula);
					break;
				case 2:
					FitxategiEragiketak.fitxategiaHustu(fitxategia);
					break;
				case 3:
					FitxategiEragiketak.erregistroakZenbatu(fitxategia);
					break;
				case 4:
					FitxategiEragiketak.erregistroaBilatu(fitxategia, scanner);
					break;
				case 5:
					FitxategiEragiketak.generogatikBilatu(fitxategia, scanner);
					break;
				case 6:
					FitxategiEragiketak.erregistroaSartu(fitxategia, scanner, pelikula);
					break;
				case 7:
					FitxategiEragiketak.erregistroaEzabatu(fitxategia, scanner);
					break;
				case 8:
					FitxategiEragiketak.kopiaSortu(fitxategia, scanner);
					break;
				case 9:
					FitxategiEragiketak.erregistroaAldatu(fitxategia, scanner);
					break;
				default:
					System.out.println("Aukera okerra.");
				}

			} catch (InputMismatchException e) {
				// Errorea gertatzen bada input okerra dela eta, mezua erakusten da
				System.out.println("Errorea: zenbaki bat sartu behar duzu.");
				scanner.nextLine(); // Inputa garbitzen du errorearen ondoren
			}
		}
	}
}
