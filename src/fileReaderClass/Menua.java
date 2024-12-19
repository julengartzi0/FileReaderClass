package fileReaderClass;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menua {

	// Scanner klasea erabiltzen da erabiltzailearen sarrera jasotzeko
	private static Scanner scanner = new Scanner(System.in);
	// Fitxategi objektua deklaratzen da
	private static File fitxategia;
	// Pelikula objektuak gordetzeko ArrayList-a sortzen da
	private static ArrayList<Pelikula> pelikula = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		// Fitxategi bat sortzen da erabiltzailearen sarrera erabiliz
		fitxategia = FitxategiKudeatzailea.fitxategiaSortu(scanner);
		// Pelikulak kargatzen dira fitxategitik ArrayList-era
		FitxategiKudeatzailea.pelikulaKargatu(pelikula);

		// Fitxategia behar bezala sortu bada, menua hasiko da
		if (fitxategia != null) {
			menuaHasi();
		} else {
			// Fitxategia sortzerakoan errorea gertatu bada, mezua erakusten da
			System.out.println("Errorea fitxategia sortzean. Ezin da jarraitu.");
		}
	}

	// Menu nagusia martxan jartzen duen metodoa
	private static void menuaHasi() throws IOException {

		while (true) {
			// Menua pantailaratzen da
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
				// Erabiltzailearen aukera jasotzen da
				aukera = scanner.nextInt();
				scanner.nextLine();

				// Aukera bakoitzaren arabera metodo desberdinak deitzen dira
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
					// Aukera okerra eman bada, mezua erakusten da
					System.out.println("Aukera okerra.");
				}

			} catch (InputMismatchException e) {
				// Input-ak zenbakia izan behar duela ohartarazteko mezua
				System.out.println("Errorea: zenbaki bat sartu behar duzu.");
				scanner.nextLine(); // Inputa garbitzen du errorearen ondoren
			}
		}
	}
}
