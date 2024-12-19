package fileReaderClass;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class FitxategiEragiketak {

	/**
	 * Fitxategia betetzen du pelikulen datuekin. Datuak fitxategian idazten ditu
	 * taulako pelikulen informazioa erabiliz.
	 * 
	 * @param fitxategia Pelikulen datuak gordetzeko fitxategia.
	 * @param pelikula   Pelikulen zerrenda ArrayList batean gordeta.
	 * @throws IOException Fitxategiarekin lotutako erroreak gertatzen badira.
	 */
	public static void fitxategiaBete(File fitxategia, ArrayList<Pelikula> pelikula) throws IOException {
		// Try-with-resources erabiltzen da fitxategia modu seguruan idazteko
		try (FileWriter fitxategiaIdatzi = new FileWriter(fitxategia)) {
			// Fitxategiaren lehen lerroa idazten da (izenburua)
			fitxategiaIdatzi.write("Pelikulak\n");

			// Pelikulen zerrenda iteratzen da eta bakoitzaren datuak fitxategian idazten
			// dira
			for (Pelikula p : pelikula) {
				fitxategiaIdatzi.write(p.getKodigoa() + ";" + p.getIzenburua() + ";" + p.getUrtea() + ";"
						+ p.getNazionalitatea() + ";" + p.getIraupena() + ";" + p.getEstreinua() + ";" + p.getGeneroa()
						+ ";" + p.getTaquila() + ";" + p.getSaria() + "\n");
			}

			// Erabiltzaileari arrakasta mezua erakusten zaio
			System.out.println("Datuak ondo idatzi dira");
		} catch (IOException e) {
			// Errore bat gertatzen bada, mezua eta stack tracea erakusten dira
			System.out.println("Errore bat sortu da datuak idazterakoan.");
			e.printStackTrace();
		}
	}

	/**
	 * Fitxategiko eduki guztiak ezabatzen ditu, edukia hutsa utziz.
	 *
	 * @param fitxategia Hustu beharreko fitxategia.
	 */
	public static void fitxategiaHustu(File fitxategia) {
		try {
			// FileWriter sortzen da bigarren parametroarekin 'false', fitxategia husteko
			FileWriter writer = new FileWriter(fitxategia, false);
			// FileWriter itxi egiten da, fitxategia hutsik uzten
			writer.close();
		} catch (IOException e) {
			// Errore bat gertatzen bada, mezua erakusten da
			System.out.println("Errorea fitxategia hustean: " + e.getMessage());
		}
	}

	/**
	 * Fitxategian dauden erregistroen kopurua zenbatzen du. Lerro bakoitza
	 * erregistro bat bezala hartzen da, lehenengo lerroa izenburua izan ezik.
	 *
	 * @param fitxategia Zenbatu beharreko erregistroak dituen fitxategia.
	 */
	public static void erregistroakZenbatu(File fitxategia) {
		int zenbakia = -1; // Lehen lerroa (izenburua) ez da kontuan hartuko
		try {
			// Fitxategia irakurtzeko BufferedReader sortzen da
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			// Lerro bakoitza irakurtzen da eta zenbatzen da
			while (reader.readLine() != null) {
				zenbakia++;
			}
			// Zenbakiaren arabera mezua erakusten da
			if (zenbakia == -1) {
				System.out.println("Ez dago erregistroik fitxategian.");
			} else {
				System.out.println("Erregistro kopurua: " + zenbakia);
			}
		} catch (IOException e) {
			// Errore bat gertatzen bada, stack trace-a eta mezua erakusten dira
			e.printStackTrace();
			System.out.println("Errorea zenbatu bitartean: " + e.getMessage());
		}
	}

	/**
	 * Fitxategian emandako kodearen arabera erregistro bat bilatzen du. Aurkitutako
	 * erregistroa pantailaratzen du, eta ez bada aurkitzen, mezua erakusten da.
	 *
	 * @param fitxategia Bilatu beharreko erregistroak dituen fitxategia.
	 * @param scanner    Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 */
	public static void erregistroaBilatu(File fitxategia, Scanner scanner) {
		System.out.println("Sartu kodea: ");
		int erregistroZenbakia = scanner.nextInt(); // Erabiltzaileak bilatu nahi duen kodea sartzen du

		try {
			// Fitxategia lerroz lerro irakurtzeko BufferedReader erabiltzen da
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			String lerroa;
			boolean aurkitua = false; // Erregistroa aurkitzen den ala ez adierazteko aldagaia

			// Lerro bakoitza irakurtzen da fitxategitik
			while ((lerroa = reader.readLine()) != null) {
				String[] erregistroa = lerroa.split(";"); // Lerroa ';' bidez banatzen da eremuetan

				// Lehen eremua (kodea) erabiltzaileak sartutako kodearekin alderatzen da
				if (erregistroa[0].equals(Integer.toString(erregistroZenbakia))) {
					// Aurkitutako erregistroaren datuak esleitzen dira
					String kodigoa = erregistroa[0];
					String izenburua = erregistroa[1];
					String urtea = erregistroa[2];
					String nazionalitatea = erregistroa[3];
					String iraupena = erregistroa[4];
					String estreinua = erregistroa[5];
					String generoa = erregistroa[6];
					String taquilla = erregistroa[7];
					String saria = erregistroa[8];

					// Aurkitutako erregistroaren informazioa formateatuta erakusten da
					System.out.print("-".repeat(120) + "\n");
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Kodigoa", "Izenburua",
							"Urtea", "Nazionalitatea", "Iraupena", "Estreinua", "Generoa", "Taquilla", "Saria");
					System.out.print("-".repeat(120) + "\n");
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", kodigoa, izenburua,
							urtea, nazionalitatea, iraupena, estreinua, generoa, taquilla, saria);
					System.out.print("-".repeat(120) + "\n");

					aurkitua = true; // Erregistroa aurkitu dela markatzen da
					break;
				}
			}

			// Erregistroa ez bada aurkitzen, erabiltzaileari mezua erakusten zaio
			if (!aurkitua) {
				System.out.println("Ez da erregistroa aurkitu.");
			}

			reader.close(); // BufferedReader itxi egiten da
		} catch (IOException e) {
			// Errore bat gertatzen bada, stack trace-a eta errore mezua erakusten dira
			e.printStackTrace();
			System.out.println("Errorea erregistroa bilatzerakoan: " + e.getMessage());
		}
	}

	/**
	 * Fitxategian emandako generoaren arabera pelikula guztiak bilatzen ditu.
	 * Aurkitutako pelikulak formateatuta pantailaratzen dira.
	 *
	 * @param fitxategia Bilatu beharreko pelikulen informazioa duen fitxategia.
	 * @param scanner    Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 */
	public static void generogatikBilatu(File fitxategia, Scanner scanner) {
		System.out.println("Sartu pelikularen generoa: ");
		String generoa = scanner.nextLine(); // Erabiltzaileak bilatu nahi duen generoa sartzen du

		try {
			// Fitxategia lerroz lerro irakurtzeko BufferedReader erabiltzen da
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			String lerroa;
			boolean aurkitua = false; // Generoa duten pelikulak aurkitzen diren adierazteko aldagaia

			reader.readLine(); // Lehen lerroa (izenburua) saltatzen da

			// Lerro bakoitza irakurtzen da fitxategitik
			while ((lerroa = reader.readLine()) != null) {
				String[] erregistroa = lerroa.split(";"); // Lerroa ';' bidez banatzen da eremuetan

				// Generoa konparatzen da eta bat badator, pelikularen informazioa erakusten da
				if (erregistroa.length >= 8 && erregistroa[6].equalsIgnoreCase(generoa)) {
					if (!aurkitua) {
						// Lehen pelikula aurkitzean, goiburua pantailaratzen da
						System.out.println(" ".repeat(40) + "Pelikulak generoa: " + generoa);
						System.out.print("-".repeat(120) + "\n");
						System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Kodigoa",
								"Izenburua", "Urtea", "Nazionalitatea", "Iraupena", "Estreinua", "Generoa", "Taquilla",
								"Saria");
						System.out.print("-".repeat(120) + "\n");
						aurkitua = true; // Aurkitua markatzen da
					}
					// Pelikularen datuak formateatuta pantailaratzen dira
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", erregistroa[0],
							erregistroa[1], erregistroa[2], erregistroa[3], erregistroa[4], erregistroa[5],
							erregistroa[6], erregistroa[7], erregistroa[8]);
					System.out.print("-".repeat(120) + "\n");
				}
			}

			// Ez bada pelikula aurkitzen, erabiltzaileari mezua erakusten zaio
			if (!aurkitua) {
				System.out.println("Ez da pelikula aurkitu.");
			}

			reader.close(); // BufferedReader itxi egiten da
		} catch (IOException e) {
			// Errore bat gertatzen bada, stack trace-a eta errore mezua erakusten dira
			e.printStackTrace();
			System.out.println("Errorea erregistroa bilatzerakoan: " + e.getMessage());
		}
	}

	/**
	 * Fitxategiko azken kodearen arabera hurrengo kodea lortzen du. Lerro guztiak
	 * irakurtzen dira, eta kode altuena aurkitzen da. Hurrengo kodea altuena + 1
	 * izango da.
	 *
	 * @param fitxategia Kodeak gordetzen dituen fitxategia.
	 * @return Fitxategiko hurrengo kodea (kode altuena + 1).
	 */
	private static int lortuHurrengoKodea(File fitxategia) {
		int maxKodea = 0; // Kode altuena gordetzeko aldagaia

		try {
			// Fitxategia irakurtzeko BufferedReader erabiltzen da
			BufferedReader irakurlea = new BufferedReader(new FileReader(fitxategia));
			String lerroa;

			// Lehen lerroa (goiburua) saltatzen da
			lerroa = irakurlea.readLine();

			// Lerro bakoitza irakurtzen da
			while ((lerroa = irakurlea.readLine()) != null) {
				// Lerro hutsak saltatzen dira
				if (lerroa.trim().isEmpty()) {
					continue;
				}
				String[] eremuak = lerroa.split(";"); // Lerroa ';' bidez banatzen da eremuetan
				if (eremuak.length > 0) {
					try {
						// Kodea (lehen eremua) zenbakira bihurtzen da
						int kodea = Integer.parseInt(eremuak[0]);
						// Aurkitutako kodea maxKodea baino handiagoa bada, eguneratzen da
						if (kodea > maxKodea) {
							maxKodea = kodea;
						}
					} catch (NumberFormatException e) {
						// Kode baliogabea duen lerroaren informazioa erakusten da
						System.out.println("Kodea baliogabea lerro honetan: " + lerroa);
					}
				}
			}
			irakurlea.close(); // BufferedReader itxi egiten da
		} catch (IOException e) {
			// Errore bat gertatzen bada, stack trace-a eta errore mezua erakusten dira
			e.printStackTrace();
			System.out.println("Errorea hurrengo kodea lortzeko fitxategia irakurtzerakoan: " + e.getMessage());
		}
		return maxKodea + 1; // Hurrengo kodea bueltatzen da
	}

	/**
	 * Pelikula berri bat erabiltzailearen datuekin sortzen du eta fitxategian
	 * gehitzen du. Erabiltzaileari hainbat datu sartzeko eskatzen zaio,
	 * balioztapenarekin.
	 *
	 * @param fitxategia Pelikulak gordetzen diren fitxategia.
	 * @param scanner    Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param pelikula   Pelikulen zerrenda, datuak kudeatzeko.
	 */
	public static void erregistroaSartu(File fitxategia, Scanner scanner, ArrayList<Pelikula> pelikula) {
		// Hurrengo kodea kalkulatzen da fitxategian oinarrituta
		int kodea = lortuHurrengoKodea(fitxategia);
		System.out.println("Asignatutako kodea: " + kodea);

		// Izenburua eskatzen da eta hutsik ez dagoela ziurtatzen da
		String izenburua = "";
		while (izenburua.isEmpty()) {
			System.out.println("Sartu pelikularen izenburua: ");
			izenburua = scanner.nextLine();
			if (izenburua.isEmpty()) {
				System.out.println("Izenburua ezin da hutsik egon.");
			}
		}

		// Pelikularen urtea balioztapenarekin eskatzen da
		int urtea = 0;
		while (true) {
			try {
				System.out.println("Sartu pelikularen urtea (1900-2024): ");
				urtea = Integer.parseInt(scanner.nextLine());
				int urteAktuala = LocalDate.now().getYear();
				if (urtea > 1900 && urtea <= urteAktuala) {
					break;
				} else {
					System.out.println("Sartu urte baliodun bat.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Sartu urte baliodun bat (zenbakia).");
			}
		}

		// Nazionalitatea eskatzen da, hutsik ez dagoela eta karaktere baliodunak
		// dituela ziurtatuz
		String nazionalitatea = "";
		while (nazionalitatea.isEmpty() || !nazionalitatea.matches("[a-zA-Z\\s]+")) {
			System.out.println("Sartu pelikularen nazionalitatea: ");
			nazionalitatea = scanner.nextLine();
			if (nazionalitatea.isEmpty()) {
				System.out.println("Nazionalitatea ezin da hutsik egon.");
			} else if (!nazionalitatea.matches("[a-zA-Z\\s]+")) {
				System.out.println("Nazionalitatea ez da balioduna.");
			}
		}

		// Iraupena balioztapenarekin eskatzen da
		int iraupena = 0;
		while (true) {
			try {
				System.out.println("Sartu pelikularen iraupena: ");
				iraupena = Integer.parseInt(scanner.nextLine());
				if (iraupena >= 10 && iraupena <= 300) {
					break;
				} else {
					System.out.println("Iraupena 10 eta 300 minutu artean egon behar da.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Sartu iraupen baliodun bat.");
			}
		}

		// Estreinu data formatu egokian eskatzen da
		LocalDate estreinua = null;
		while (estreinua == null) {
			try {
				System.out.println("Sartu pelikularen estreinu data (UUUU-HH-EE): ");
				String dataInput = scanner.nextLine();
				estreinua = LocalDate.parse(dataInput);
			} catch (Exception e) {
				System.out.println("Data baliogabea. Mesedez, sartu data formatuan UUUU-HH-EE.");
			}
		}

		// Generoa balioztatzen da aukera baliodunen artean
		String generoa = "";
		String[] generoBaliodunak = { "Suspense", "Drama", "Romantica", "Comedia", "Aventura", "Fantasia" };
		while (true) {
			System.out.println(
					"Sartu pelikularen generoa ('Suspense', 'Drama', 'Romantica', 'Comedia', 'Aventura', 'Fantasia'): ");
			generoa = scanner.nextLine();
			boolean generoBalioduna = false;
			for (String g : generoBaliodunak) {
				if (generoa.equalsIgnoreCase(g)) {
					generoa = g; // Maiuskulak eta minuskulak zuzentzeko
					generoBalioduna = true;
					break;
				}
			}
			if (generoBalioduna) {
				break;
			} else {
				System.out.println(
						"Genero baliogabea. Aukera balioak: 'Suspense', 'Drama', 'Romantica', 'Comedia', 'Aventura', 'Fantasia'");
			}
		}

		// Taquilla zenbatekoa zenbaki baliodun bat dela ziurtatzen da
		double taquilla = 0.0;
		while (true) {
			try {
				System.out.println("Sartu pelikularen taquilla zenbatekoa: ");
				taquilla = Double.parseDouble(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Sartu zenbateko baliodun bat.");
			}
		}

		// Saria balioztatzen da 0 edo 1 dela ziurtatuz
		int saria = -1;
		while (true) {
			try {
				System.out.println("Sartu pelikularen saria zenbatekoa (0 ez / 1 bai): ");
				saria = Integer.parseInt(scanner.nextLine());
				if (saria == 0 || saria == 1) {
					break;
				} else {
					System.out.println("Saria 0 edo 1 izan behar da.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Sartu 0 edo 1 zenbakia.");
			}
		}

		// Datuak fitxategian idazten dira
		try (FileWriter writer = new FileWriter(fitxategia, true)) {
			writer.write(kodea + ";" + izenburua + ";" + urtea + ";" + nazionalitatea + ";" + iraupena + ";" + estreinua
					+ ";" + generoa + ";" + taquilla + ";" + saria + "\n");
			System.out.println("Erregistroa ondo sartu da.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errorea erregistroa sartzerakoan: " + e.getMessage());
		}
	}

	/**
	 * Fitxategitik emandako kodearen arabera erregistro bat ezabatzen du.
	 * Aurkitutako erregistroa pantailaratzen da ezabatu aurretik.
	 *
	 * @param fitxategia Erregistroak gordetzen dituen fitxategia.
	 * @param scanner    Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 */
	public static void erregistroaEzabatu(File fitxategia, Scanner scanner) {
		System.out.println("Sartu kodea: ");
		int erregistroZenbakia = scanner.nextInt(); // Erabiltzaileak ezabatu nahi duen kodea sartzen du

		try {
			// Fitxategia irakurtzeko BufferedReader erabiltzen da
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			String lerroa;
			boolean aurkitua = false; // Erregistroa aurkitu den ala ez kontrolatzeko aldagaia
			ArrayList<String> erregistroBerria = new ArrayList<>(); // Ezabatutako fitxategia gordetzeko lista

			// Lerro bakoitza irakurtzen da
			while ((lerroa = reader.readLine()) != null) {
				String[] erregistroa = lerroa.split(";"); // Lerroa ';' bidez banatzen da eremuetan

				// Kodea alderatzen da
				if (erregistroa[0].equals(Integer.toString(erregistroZenbakia))) {
					// Aurkitutako erregistroaren informazioa pantailaratzen da
					String kodigoa = erregistroa[0];
					String izenburua = erregistroa[1];
					String urtea = erregistroa[2];
					String nazionalitatea = erregistroa[3];
					String iraupena = erregistroa[4];
					String estreinua = erregistroa[5];
					String generoa = erregistroa[6];
					String taquilla = erregistroa[7];
					String saria = erregistroa[8];
					System.out.print("-".repeat(120) + "\n");
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Kodigoa", "Izenburua",
							"Urtea", "Nazionalitatea", "Iraupena", "Estreinua", "Generoa", "Taquilla", "Saria");
					System.out.print("-".repeat(120) + "\n");
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", kodigoa, izenburua,
							urtea, nazionalitatea, iraupena, estreinua, generoa, taquilla, saria);
					System.out.print("-".repeat(120) + "\n");
					aurkitua = true; // Erregistroa aurkitu dela markatzen da
				} else {
					// Ezabatuko ez diren erregistroak gordetzen dira
					erregistroBerria.add(lerroa);
				}
			}

			if (!aurkitua) {
				// Erregistroa ez bada aurkitzen, mezua erakusten da
				System.out.println("Ez da erregistroa aurkitu.");
			} else {
				// Fitxategia berridazten da erregistro eguneratuekin
				try (FileWriter writer = new FileWriter(fitxategia)) {
					for (String lerro : erregistroBerria) {
						writer.write(lerro + "\n");
					}
					System.out.println("Erregistroa ondo ezabatu da.");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Errorea erregistroa ezabatzerakoan: " + e.getMessage());
				}
			}
			reader.close(); // BufferedReader itxi egiten da
		} catch (IOException e) {
			// Errore bat gertatzen bada, stack trace-a eta errore mezua erakusten dira
			e.printStackTrace();
			System.out.println("Errorea erregistroa ezabatzerakoan: " + e.getMessage());
		}
	}

	/**
	 * Fitxategiaren kopia bat sortzen du emandako helmugan. Erabiltzaileari helmuga
	 * helbidea sartzeko aukera ematen zaio. Helmuga hutsik badago, kopia kokatuko
	 * da `C:\\Users\\Administrador\\Desktop\\FROGAK` direktorioan. Helmugako
	 * direktorioa existitzen ez bada, mezua pantailaratzen da.
	 *
	 * @param fitxategia Kopiatu beharreko fitxategia.
	 * @param scanner    Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 */
	public static void kopiaSortu(File fitxategia, Scanner scanner) {
		System.out.println(
				"Sartu kopia gorde nahi duzun helbidea (HUTSIK = C:\\Users\\Administrador\\Desktop\\FROGAK): ");
		String helmugaHelbidea = scanner.nextLine(); // Erabiltzaileak helmuga helbidea sartzen du
		if (helmugaHelbidea.equals("")) {
			// Helmuga hutsik badago, helbide lehenetsia erabiltzen da
			helmugaHelbidea = "C:\\Users\\Administrador\\Desktop\\FROGAK";
		}

		// Helmugako direktorioaren egiaztapena
		File helmugaDirektorioa = new File(helmugaHelbidea);
		if (!helmugaDirektorioa.exists() || !helmugaDirektorioa.isDirectory()) {
			System.out.println("Errorea: Helmuga direktorioa ez da aurkitu: " + helmugaHelbidea);
			return; // Metodoa amaitzen da helmuga ez badago
		}

		// Kopia fitxategiaren helmuga eta izena definitzen dira
		File kopia = new File(helmugaHelbidea, fitxategia.getName());

		try {
			// Fitxategia lerroz lerro irakurtzen da eta helmugan idazten da
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			FileWriter writer = new FileWriter(kopia);
			String lerroa;
			while ((lerroa = reader.readLine()) != null) {
				writer.write(lerroa + "\n");
			}
			reader.close(); // Irakurlea itxi egiten da
			writer.close(); // Idazlea itxi egiten da
			// Arrakastaren mezua erakusten da helmugako kokapenarekin
			System.out.println("Fitxategiaren kopia sortu da: " + kopia.getAbsolutePath());
		} catch (IOException e) {
			// Errore bat gertatzen bada, stack trace-a eta errore mezua erakusten dira
			e.printStackTrace();
			System.out.println("Errorea kopia sortzerakoan: " + e.getMessage());
		}
	}

	/**
	 * Emandako kodearen arabera fitxategian dagoen erregistro bat aldatzen du.
	 * Erregistroaren eremuak erabiltzailearen sarreraren bidez eguneratzen dira.
	 *
	 * @param fitxategia Erregistroak gordetzen dituen fitxategia.
	 * @param scanner    Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 */
	public static void erregistroaAldatu(File fitxategia, Scanner scanner) {
		System.out.println("Sartu kodea: ");
		int erregistroZenbakia = scanner.nextInt(); // Erabiltzaileak aldatu nahi duen kodea sartzen du
		scanner.nextLine(); // Saltar la línea restante

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			String lerroa;
			boolean aurkitua = false; // Erregistroa aurkitzen den ala ez kontrolatzeko aldagaia
			ArrayList<String> erregistroBerria = new ArrayList<>(); // Eguneratutako erregistroak gordetzeko

			while ((lerroa = reader.readLine()) != null) {
				String[] erregistroa = lerroa.split(";"); // Lerroa ';' bidez banatzen da eremuetan
				if (erregistroa[0].equals(Integer.toString(erregistroZenbakia))) {
					aurkitua = true;

					// Aurkitutako erregistroa pantailaratzen da
					System.out.print("-".repeat(120) + "\n");
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Kodigoa", "Izenburua",
							"Urtea", "Nazionalitatea", "Iraupena", "Estreinua", "Generoa", "Taquilla", "Saria");
					System.out.print("-".repeat(120) + "\n");
					System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", erregistroa[0],
							erregistroa[1], erregistroa[2], erregistroa[3], erregistroa[4], erregistroa[5],
							erregistroa[6], erregistroa[7], erregistroa[8]);
					System.out.print("-".repeat(120) + "\n");

					// Eremuaren aldaketa aukeratzea
					System.out.println("Aldatu nahi duzun eremua (1-9): ");
					int aldaketa = scanner.nextInt();
					scanner.nextLine(); // Saltar la línea restante

					// Eremua aldatu aukeraren arabera
					switch (aldaketa) {
					case 1:
						erregistroa[1] = stringBalidatu(scanner, "Sartu pelikularen izenburua: ");
						break;
					case 2:
						erregistroa[2] = String
								.valueOf(urteaBalidatu(scanner, "Sartu pelikularen urtea (1900-2040): ", 1900, 2024));
						break;
					case 3:
						erregistroa[3] = stringBalidatu(scanner, "Sartu pelikularen nazionalitatea: ");
						break;
					case 4:
						erregistroa[4] = String
								.valueOf(intBalidatu(scanner, "Sartu pelikularen iraupena (minutuetan): "));
						break;
					case 5:
						erregistroa[5] = dataBalidatu(scanner, "Sartu pelikularen estreinu data (UUUU-HH-EE): ");
						break;
					case 6:
						erregistroa[6] = generoaBalidatu(scanner, "Sartu pelikularen generoa: ");
						break;
					case 7:
						erregistroa[7] = String
								.valueOf(doubleBalidatu(scanner, "Sartu pelikularen taquilla zenbatekoa: "));
						break;
					case 8:
						erregistroa[8] = String
								.valueOf(sariaBalidatu(scanner, "Sartu pelikularen saria (0 ez / 1 bai): "));
						break;
					default:
						System.out.println("Aukera baliogabea.");
					}
					erregistroBerria.add(String.join(";", erregistroa)); // Egokitutako erregistroa gordetzen da
				} else {
					erregistroBerria.add(lerroa); // Aldatu ez diren erregistroak mantentzen dira
				}
			}

			if (!aurkitua) {
				System.out.println("Ez da erregistroa aurkitu.");
			} else {
				// Eguneratutako erregistroak fitxategira idazten dira
				try (FileWriter writer = new FileWriter(fitxategia)) {
					for (String lerro : erregistroBerria) {
						writer.write(lerro + "\n");
					}
					System.out.println("Erregistroa ondo aldatu da.");
				}
			}
			reader.close(); // Fitxategia irakurtzea amaitzen da
		} catch (IOException e) {
			System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
		}
	}

	// Métodos de validación
	// Balidazio metodoak

	/**
	 * Erabiltzaileak hutsik ez dagoen String bat sartzen duela ziurtatzen du.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @return Erabiltzaileak sartutako String balioduna.
	 */
	private static String stringBalidatu(Scanner scanner, String mezua) {
		String input;
		do {
			System.out.println(mezua);
			input = scanner.nextLine();
			if (input.isEmpty())
				System.out.println("Eremua ezin da hutsik egon.");
		} while (input.isEmpty());
		return input;
	}

	/**
	 * Erabiltzaileak urte baliodun bat sartzen duela ziurtatzen du, zehaztutako
	 * tartearen barruan. Urteak 4 digitu izan behar dituela ere egiaztatzen da.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @param min     Onartzen den urte minimoa.
	 * @param max     Onartzen den urte maximoa.
	 * @return Erabiltzaileak sartutako urte balioduna.
	 */
	private static int urteaBalidatu(Scanner scanner, String mezua, int min, int max) {
		while (true) {
			try {
				System.out.println(mezua);
				String input = scanner.nextLine();

				// Egiaztatu urteak 4 digitu dituela
				if (input.length() != 4) {
					System.out.println("Urteak 4 digitu izan behar ditu.");
					continue;
				}

				int value = Integer.parseInt(input);

				// Egiaztatu urteak tartean dagoela
				if (value >= min && value <= max) {
					return value;
				} else {
					System.out.println("Urtea " + min + " eta " + max + " artean egon behar da.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Sartu zenbaki baliodun bat.");
			}
		}
	}

	/**
	 * Erabiltzaileak zenbaki baliodun bat sartzen duela ziurtatzen du.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @return Erabiltzaileak sartutako zenbaki balioduna.
	 */
	private static int intBalidatu(Scanner scanner, String mezua) {
		while (true) {
			try {
				System.out.println(mezua);
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sartu zenbaki baliodun bat.");
			}
		}
	}

	/**
	 * Erabiltzaileak zenbaki hamartar baliodun bat sartzen duela ziurtatzen du.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @return Erabiltzaileak sartutako zenbaki hamartar balioduna.
	 */
	private static double doubleBalidatu(Scanner scanner, String mezua) {
		while (true) {
			try {
				System.out.println(mezua);
				return Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sartu zenbaki hamartar baliodun bat.");
			}
		}
	}

	/**
	 * Erabiltzaileak data formatu egokian sartzen duela ziurtatzen du.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @return Erabiltzaileak sartutako data balioduna (String formatuan).
	 */
	private static String dataBalidatu(Scanner scanner, String mezua) {
		while (true) {
			try {
				System.out.println(mezua);
				return LocalDate.parse(scanner.nextLine()).toString();
			} catch (Exception e) {
				System.out.println("Data baliogabea. Formatu egokia: UUUU-HH-EE");
			}
		}
	}

	/**
	 * Erabiltzaileak generoa aukera baliodunen artean sartzen duela ziurtatzen du.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @return Erabiltzaileak sartutako genero balioduna.
	 */
	private static String generoaBalidatu(Scanner scanner, String mezua) {
		String[] generoBaliodunak = { "Suspense", "Drama", "Romantica", "Comedia", "Aventura", "Fantasia" };
		while (true) {
			System.out.println(mezua);
			String input = scanner.nextLine();
			for (String genero : generoBaliodunak) {
				if (input.equalsIgnoreCase(genero)) {
					return genero;
				}
			}
			System.out.println("Genero baliogabea. Aukera balioak: " + String.join(", ", generoBaliodunak));
		}
	}

	/**
	 * Erabiltzaileak 0 edo 1 sartzen duela ziurtatzen du, sariaren eremurako.
	 *
	 * @param scanner Erabiltzailearen sarrerak jasotzeko Scanner objektua.
	 * @param mezua   Erabiltzaileari erakusten zaion mezua.
	 * @return Erabiltzaileak sartutako saria (0 edo 1).
	 */
	private static int sariaBalidatu(Scanner scanner, String mezua) {
		while (true) {
			try {
				System.out.println(mezua);
				int saria = Integer.parseInt(scanner.nextLine());
				if (saria == 0 || saria == 1)
					return saria;
				System.out.println("Saria 0 edo 1 izan behar da.");
			} catch (NumberFormatException e) {
				System.out.println("Sartu 0 edo 1 zenbakia.");
			}
		}
	}

}
