package fileReaderClass;

import java.io.File;
import java.time.LocalDate;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class FitxategiKudeatzailea {

	public static File fitxategiaSortu(Scanner scanner) {
        String fitxategiIzena;
        String fitxategiHelbidea;
        File fitxategia = null;

        // Fitxategiaren helbidea eskatzen da erabiltzaileari
        System.out.println("Sartu fitxategiaren helbidea (HUTSIK = C:\\Users\\Administrador\\Desktop\\FROGAK): ");
        fitxategiHelbidea = scanner.nextLine();
        if (fitxategiHelbidea.equals("")) {
            fitxategiHelbidea = "C:\\Users\\Administrador\\Desktop\\FROGAK";
        }

        // Fitxategiaren direktorioa existitzen ez bada, sortzen da
        File direktorioa = new File(fitxategiHelbidea);
        if (!direktorioa.exists()) {
            if (direktorioa.mkdirs()) {
                System.out.println("Direktorioa sortuta: " + direktorioa.getAbsolutePath());
            } else {
                System.out.println("Errorea direktorioa sortzerakoan.");
                return null; // Ezin bada sortu, amaitzen da
            }
        }

        // Fitxategia sortzeko edo erabiltzeko logika
        while (true) {
            System.out.println("Sartu fitxategiaren izena:");
            fitxategiIzena = scanner.nextLine();

            fitxategiIzena = fitxategiIzena + ".txt";
            fitxategia = new File(fitxategiHelbidea, fitxategiIzena);

            if (fitxategia.exists()) {
                System.out.println("Fitxategia existitzen da: " + fitxategia.getAbsolutePath());
                System.out.println("Erabili fitxategi hau? (bai/ez):");

                String erantzuna;
                while (true) {
                    erantzuna = scanner.nextLine();
                    if (erantzuna.equalsIgnoreCase("bai")) {
                        System.out.println("Fitxategia erabiliko da: " + fitxategia.getAbsolutePath());
                        return fitxategia;
                    } else if (erantzuna.equalsIgnoreCase("ez")) {
                        System.out.println("Beste fitxategi izen bat aukeratu.");
                        break; // Go back to ask for a new name
                    } else {
                        System.out.println("Aukera baliogabea. Mesedez, idatzi 'bai' edo 'ez':");
                    }
                }
            } else {
                // Fitxategia existitzen ez bada, sortzen saiatzen da
                try {
                    System.out.println("Ez da existitzen. Fitxategia sortzen saiatzen...");
                    if (fitxategia.createNewFile()) {
                        System.out.println("Fitxategia sortu da: " + fitxategia.getAbsolutePath());
                        return fitxategia;
                    } else {
                        System.out.println("Ezin izan da fitxategia sortu.");
                    }
                } catch (IOException e) {
                    System.err.println("Errorea fitxategia sortzean: " + e.getMessage());
                }
            }
        }
    }
    
    public static void pelikulaKargatu(ArrayList<Pelikula> pelikula) {
        // Pelikula objektuak sortu eta zerrendara gehitzen dira
        pelikula.add(new Pelikula(1, "31 dias", 2000, "Estados Unidos", 145, LocalDate.of(2001, 3, 23), "Suspense",
                1103731.95, 0));
        pelikula.add(new Pelikula(2, "El hijo de la novia", 2001, "España Argentina", 124, LocalDate.of(2001, 11, 23),
                "Drama", 7230415.69, 0));
        pelikula.add(new Pelikula(3, "El señor de los anillos. La comunidad del anillo.", 2001, "Nueva Zelanda", 160,
                LocalDate.of(2001, 12, 19), "Fantasia", 31263314.97, 1));
        pelikula.add(new Pelikula(4, "Mar adentro", 2004, "España", 95, LocalDate.of(2004, 9, 3), "Drama", 19517968.62, 0));
        pelikula.add(new Pelikula(5, "Casablanca", 1942, "Estados Unidos", 98, LocalDate.of(1946, 12, 19), "Romantica",
                318310.24, 0));
        pelikula.add(new Pelikula(6, "El bola", 2000, "España", 88, LocalDate.of(2000, 10, 20), "Drama", 2998626.52, 0));
        pelikula.add(new Pelikula(7, "Torrente, el brazo tonto de la ley", 1998, "España", 97, LocalDate.of(1998, 3, 13), 
                "Comedia", 10902559.95, 0));
        pelikula.add(new Pelikula(8, "Solas", 1998, "España", 101, LocalDate.of(1999, 3, 5), "Drama", 3675149.47, 0));
        pelikula.add(new Pelikula(9, "Poseidon", 2005, "Estados Unidos", 105, LocalDate.of(2005, 6, 25), "Aventura", 0, 0));
        pelikula.add(new Pelikula(10, "flags of our fathers", 2005, "Estados Unidos", 108, LocalDate.of(2005, 7, 2), 
                "Drama", 0, 0));
    }
}
