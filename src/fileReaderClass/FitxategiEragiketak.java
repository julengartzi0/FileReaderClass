package fileReaderClass;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class FitxategiEragiketak {
	
	public static void fitxategiaBete(File fitxategia, ArrayList<Pelikula> pelikula) throws IOException {
        try (FileWriter fitxategiaIdatzi = new FileWriter(fitxategia)) {
            fitxategiaIdatzi.write("Pelikulak\n");
            for (Pelikula p : pelikula) {
                fitxategiaIdatzi.write(p.getKodigoa() + ";" + p.getIzenburua() + ";" + p.getUrtea() + ";"
                        + p.getNazionalitatea() + ";" + p.getIraupena() + ";" + p.getEstreinua() + ";" + p.getGeneroa()
                        + ";" + p.getTaquila() + ";" + p.getSaria() + "\n");
            }
            System.out.println("Datuak ondo idatzi dira");
        } catch (IOException e) {
            System.out.println("Errore bat sortu da datuak idazterakoan.");
            e.printStackTrace();
        }
    }
	
	public static void fitxategiaHustu(File fitxategia) {
        try {
            FileWriter writer = new FileWriter(fitxategia, false);
            writer.close();
        } catch (IOException e) {
            System.out.println("Errorea fitxategia hustean: " + e.getMessage());
        }
    }
	
	public static void erregistroakZenbatu(File fitxategia) {
        int zenbakia = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
            while (reader.readLine() != null) {
                zenbakia++;
            }
            if(zenbakia == -1) {
            System.out.println("Ez dago erregistroik fitxategian.");
            }else {
            System.out.println("Erregistro kopurua: " + zenbakia);}
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errorea zenbatu bitartean: " + e.getMessage());
        }
    }
	
	
	public static void erregistroaBilatu(File fitxategia, Scanner scanner) {
        System.out.println("Sartu kodea: ");
        int erregistroZenbakia = scanner.nextInt();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
            String lerroa;
            boolean aurkitua = false;

            while ((lerroa = reader.readLine()) != null) {
            	String[] erregistroa = lerroa.split(";");
                
                if (erregistroa[0].equals(Integer.toString(erregistroZenbakia))) {
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
                	System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Kodigoa", "Izenburua", "Urtea", "Nazionalitatea", "Iraupena", "Estreinua", "Generoa", "Taquilla", "Saria");
                	System.out.print("-".repeat(120) + "\n");
                	System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", kodigoa, izenburua, urtea, nazionalitatea, iraupena, estreinua, generoa, taquilla, saria);
                	System.out.print("-".repeat(120) + "\n");
                    aurkitua = true;
                    break;
                }
            }

            if (!aurkitua) {
                System.out.println("Ez da erregistroa aurkitu.");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errorea erregistroa bilatzerakoan: " + e.getMessage());
        }
    }
	
	public static void generogatikBilatu(File fitxategia, Scanner scanner) {
        System.out.println("Sartu pelikularen generoa: ");
        String generoa = scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
            String lerroa;
            boolean aurkitua = false;

            reader.readLine(); // Saltar la primera línea

            while ((lerroa = reader.readLine()) != null) {
                String[] erregistroa = lerroa.split(";");
                if (erregistroa.length >= 8 && erregistroa[6].equalsIgnoreCase(generoa)) {
                    System.out.println("Pelikula aurkitu da: " + lerroa);
                    aurkitua = true;
                }
            }

            if (!aurkitua) {
                System.out.println("Ez da pelikula aurkitu.");
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errorea erregistroa bilatzerakoan: " + e.getMessage());
        }
    }
	
	private static int lortuHurrengoKodea(File fitxategia) {
        int maxKodea = 0;
        // Hurrengo kodea lortzen da fitxategitik irakurriz
        try {
            BufferedReader irakurlea = new BufferedReader(new FileReader(fitxategia));
            String lerroa;

            // Goiburua saltatzen da
            lerroa = irakurlea.readLine();

            while ((lerroa = irakurlea.readLine()) != null) {
                // Lerro hutsak saltatzen dira
                if (lerroa.trim().isEmpty()) {
                    continue;
                }
                String[] eremuak = lerroa.split(";");
                if (eremuak.length > 0) {
                    try {
                        int kodea = Integer.parseInt(eremuak[0]);
                        if (kodea > maxKodea) {
                            maxKodea = kodea;
                        }
                    } catch (NumberFormatException e) {
                        // Kode baliogabea duen lerroa
                        System.out.println("Kodea baliogabea lerro honetan: " + lerroa);
                    }
                }
            }
            irakurlea.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errorea hurrengo kodea lortzeko fitxategia irakurtzerakoan: " + e.getMessage());
        }
        return maxKodea + 1;
    }
	
	public static void erregistroaSartu(File fitxategia, Scanner scanner, ArrayList<Pelikula> pelikula) {
        int kodea = lortuHurrengoKodea(fitxategia);
        System.out.println("Asignatutako kodea: " + kodea);

        // Izenburua eskatzen da
        String izenburua = "";
        while (izenburua.isEmpty()) {
            System.out.println("Sartu pelikularen izenburua: ");
            izenburua = scanner.nextLine();
            if (izenburua.isEmpty()) {
                System.out.println("Izenburua ezin da hutsik egon.");
            }
        }

        // Pelikularen urtea eskatzen da
        int urtea = 0;
        while (true) {
            try {
                System.out.println("Sartu pelikularen urtea: ");
                urtea = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Sartu urte baliodun bat (zenbakia).");
            }
        }

        // Nazionalitatea eskatzen da
        String nazionalitatea = "";
        while (nazionalitatea.isEmpty()) {
            System.out.println("Sartu pelikularen nazionalitatea: ");
            nazionalitatea = scanner.nextLine();
            if (nazionalitatea.isEmpty()) {
                System.out.println("Nazionalitatea ezin da hutsik egon.");
            }
        }

        // Iraupena eskatzen da
        int iraupena = 0;
        while (true) {
            try {
                System.out.println("Sartu pelikularen iraupena (minutuetan): ");
                iraupena = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Sartu iraupen baliodun bat (zenbakia).");
            }
        }

        // Estreinu data eskatzen da
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

        // Generoa eskatzen da
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

        // Taquilla zenbatekoa eskatzen da
        double taquilla = 0.0;
        while (true) {
            try {
                System.out.println("Sartu pelikularen taquilla zenbatekoa: ");
                taquilla = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Sartu zenbateko baliodun bat (zenbaki hamartarra).");
            }
        }

        // Sariari buruzko informazioa eskatzen da
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
	
	public static void erregistroaEzabatu(File fitxategia, Scanner scanner) {
		System.out.println("Sartu kodea: ");
		int erregistroZenbakia = scanner.nextInt();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
			String lerroa;
			boolean aurkitua = false;
			ArrayList<String> erregistroBerria = new ArrayList<>();

			while ((lerroa = reader.readLine()) != null) {
				String[] erregistroa = lerroa.split(";");
				if (erregistroa[0].equals(Integer.toString(erregistroZenbakia))) {
					System.out.println("Erregistroa ezabatuko da: " + lerroa);
					aurkitua = true;
				} else {
					erregistroBerria.add(lerroa);
				}
			}

			if (!aurkitua) {
				System.out.println("Ez da erregistroa aurkitu.");
			} else {
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
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Errorea erregistroa ezabatzerakoan: " + e.getMessage());
		}
	}
	
	public static void kopiaSortu(File fitxategia, Scanner scanner) {
        System.out.println(
                "Sartu kopia gorde nahi duzun helbidea (HUTSIK = C:\\Users\\Administrador\\Desktop\\FROGAK): ");
        String helmugaHelbidea = scanner.nextLine();
        if (helmugaHelbidea.equals("")) {
            helmugaHelbidea = "C:\\Users\\Administrador\\Desktop\\FROGAK";
        }

        File kopia = new File(helmugaHelbidea, fitxategia.getName());

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
            FileWriter writer = new FileWriter(kopia);
            String lerroa;
            while ((lerroa = reader.readLine()) != null) {
                writer.write(lerroa + "\n");
            }
            reader.close();
            writer.close();
            System.out.println("Fitxategiaren kopia sortu da: " + kopia.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errorea kopia sortzerakoan: " + e.getMessage());
        }
    }
	
	public static void erregistroaAldatu(File fitxategia, Scanner scanner) {
	    System.out.println("Sartu kodea: ");
	    int erregistroZenbakia = scanner.nextInt();
	    scanner.nextLine(); // Saltar la línea restante

	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(fitxategia));
	        String lerroa;
	        boolean aurkitua = false;
	        ArrayList<String> erregistroBerria = new ArrayList<>();

	        while ((lerroa = reader.readLine()) != null) {
	            String[] erregistroa = lerroa.split(";");
	            if (erregistroa[0].equals(Integer.toString(erregistroZenbakia))) {
	                aurkitua = true;

	                // Mostrar el registro
	                System.out.print("-".repeat(120) + "\n");
	                System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", 
	                    "Kodigoa", "Izenburua", "Urtea", "Nazionalitatea", "Iraupena", 
	                    "Estreinua", "Generoa", "Taquilla", "Saria");
	                System.out.print("-".repeat(120) + "\n");
	                System.out.printf("%-10s %-25s %-10s %-20s %-10s %-10s %-10s %-10s %-10s\n", 
	                    erregistroa[0], erregistroa[1], erregistroa[2], erregistroa[3],
	                    erregistroa[4], erregistroa[5], erregistroa[6], erregistroa[7], erregistroa[8]);
	                System.out.print("-".repeat(120) + "\n");

	                // Seleccionar campo a modificar
	                System.out.println("Aldatu nahi duzun eremua (1-9): ");
	                int aldaketa = scanner.nextInt();
	                scanner.nextLine(); // Saltar la línea restante

	                switch (aldaketa) {
	                    case 1: // Izenburua
	                        erregistroa[1] = validarString(scanner, "Sartu pelikularen izenburua: ");
	                        break;
	                    case 2: // Urtea
	                        erregistroa[2] = String.valueOf(validarUrtea(scanner, "Sartu pelikularen urtea (1900-2040): ", 1900, 2024));
	                        break;
	                    case 3: // Nazionalitatea
	                        erregistroa[3] = validarString(scanner, "Sartu pelikularen nazionalitatea: ");
	                        break;
	                    case 4: // Iraupena
	                        erregistroa[4] = String.valueOf(validarInt(scanner, "Sartu pelikularen iraupena (minutuetan): "));
	                        break;
	                    case 5: // Estreinua
	                        erregistroa[5] = validarData(scanner, "Sartu pelikularen estreinu data (UUUU-HH-EE): ");
	                        break;
	                    case 6: // Generoa
	                        erregistroa[6] = validarGeneroa(scanner, "Sartu pelikularen generoa: ");
	                        break;
	                    case 7: // Taquilla
	                        erregistroa[7] = String.valueOf(validarDouble(scanner, "Sartu pelikularen taquilla zenbatekoa: "));
	                        break;
	                    case 8: // Saria
	                        erregistroa[8] = String.valueOf(validarSaria(scanner, "Sartu pelikularen saria (0 ez / 1 bai): "));
	                        break;
	                    default:
	                        System.out.println("Aukera baliogabea.");
	                }
	                erregistroBerria.add(String.join(";", erregistroa));
	            } else {
	                erregistroBerria.add(lerroa);
	            }
	        }

	        if (!aurkitua) {
	            System.out.println("Ez da erregistroa aurkitu.");
	        } else {
	            try (FileWriter writer = new FileWriter(fitxategia)) {
	                for (String lerro : erregistroBerria) {
	                    writer.write(lerro + "\n");
	                }
	                System.out.println("Erregistroa ondo aldatu da.");
	            }
	        }
	        reader.close();
	    } catch (IOException e) {
	        System.out.println("Errorea fitxategia irakurtzean: " + e.getMessage());
	    }
	}

	// Métodos de validación
	private static String validarString(Scanner scanner, String mezua) {
	    String input;
	    do {
	        System.out.println(mezua);
	        input = scanner.nextLine();
	        if (input.isEmpty()) System.out.println("Eremua ezin da hutsik egon.");
	    } while (input.isEmpty());
	    return input;
	}
	
	private static int validarUrtea(Scanner scanner, String mezua, int min, int max) {
	    while (true) {
	        try {
	            System.out.println(mezua);
	            int value = Integer.parseInt(scanner.nextLine());
	            if (value >= min && value <= max) {
	                return value;
	            } else {
	                System.out.println("Balioa " + min + " eta " + max + " artean egon behar da.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Sartu zenbaki baliodun bat.");
	        }
	    }
	}

	private static int validarInt(Scanner scanner, String mezua) {
	    while (true) {
	        try {
	            System.out.println(mezua);
	            return Integer.parseInt(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Sartu zenbaki baliodun bat.");
	        }
	    }
	}

	private static double validarDouble(Scanner scanner, String mezua) {
	    while (true) {
	        try {
	            System.out.println(mezua);
	            return Double.parseDouble(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Sartu zenbaki hamartar baliodun bat.");
	        }
	    }
	}

	private static String validarData(Scanner scanner, String mezua) {
	    while (true) {
	        try {
	            System.out.println(mezua);
	            return LocalDate.parse(scanner.nextLine()).toString();
	        } catch (Exception e) {
	            System.out.println("Data baliogabea. Formatu egokia: UUUU-HH-EE");
	        }
	    }
	}

	private static String validarGeneroa(Scanner scanner, String mezua) {
	    String[] generoBaliodunak = {"Suspense", "Drama", "Romantica", "Comedia", "Aventura", "Fantasia"};
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

	private static int validarSaria(Scanner scanner, String mezua) {
	    while (true) {
	        try {
	            System.out.println(mezua);
	            int saria = Integer.parseInt(scanner.nextLine());
	            if (saria == 0 || saria == 1) return saria;
	            System.out.println("Saria 0 edo 1 izan behar da.");
	        } catch (NumberFormatException e) {
	            System.out.println("Sartu 0 edo 1 zenbakia.");
	        }
	    }
	}


}
