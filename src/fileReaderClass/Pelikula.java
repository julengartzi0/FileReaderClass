package fileReaderClass;

import java.time.LocalDate;

public class Pelikula {

	// Atributuak pelikularen ezaugarriak gordetzeko
	private int kodigoa; 				// Pelikularen kode bakarra
	private String izenburua; 			// Pelikularen izenburua
	private int urtea; 					// Pelikula argitaratu zen urtea
	private String nazionalitatea;		// Pelikulak duen herrialde edo herrialdeak
	private int iraupena; 				// Pelikulak irauten duen denbora minututan
	private LocalDate estreinua; 		// Pelikula estreinatu zen data
	private String generoa; 			// Pelikularen generoa (drama, komedia, etab.)
	private double taquila; 			// Pelikulak irabazitako diru kopurua taquillan
	private int saria; 					// Pelikulak sariren bat irabazi duen (0 ez / 1 bai)

	/**
	 * Eraikitzailea, pelikula berri bat sortzeko erabiliko dena, atributu guztiekin
	 * @param kodigoa
	 * @param izenburua
	 * @param urtea
	 * @param nazionalitatea
	 * @param iraupena
	 * @param estreinua
	 * @param generoa
	 * @param taquila
	 * @param saria
	 */
	public Pelikula(int kodigoa, String izenburua, int urtea, String nazionalitatea, int iraupena, LocalDate estreinua,
			String generoa, double taquila, int saria) {
		this.kodigoa = kodigoa;
		this.izenburua = izenburua;
		this.urtea = urtea;
		this.nazionalitatea = nazionalitatea;
		this.iraupena = iraupena;
		this.estreinua = estreinua;
		this.generoa = generoa;
		this.taquila = taquila;
		this.saria = saria;
	}

	// Getters eta Setters metodoak, atributuak eskuratu eta aldatzeko
	// Pelikularen kodea lortzen du
	public int getKodigoa() {
		return kodigoa;
	}

	// Pelikularen kodea ezartzen du
	public void setKodigoa(int kodigoa) {
		this.kodigoa = kodigoa;
	}

	// Pelikularen izenburua lortzen du
	public String getIzenburua() {
		return izenburua;
	}

	// Pelikularen izenburua ezartzen du
	public void setIzenburua(String izenburua) {
		this.izenburua = izenburua;
	}

	// Pelikula argitaratu zen urtea lortzen du
	public int getUrtea() {
		return urtea;
	}

	// Pelikula argitaratu zen urtea ezartzen du
	public void setUrtea(int urtea) {
		this.urtea = urtea;
	}

	// Pelikularen nazionalitatea lortzen du
	public String getNazionalitatea() {
		return nazionalitatea;
	}

	// Pelikularen nazionalitatea ezartzen du
	public void setNazionalitatea(String nazionalitatea) {
		this.nazionalitatea = nazionalitatea;
	}

	// Pelikularen iraupena lortzen du
	public int getIraupena() {
		return iraupena;
	}

	// Pelikularen iraupena ezartzen du
	public void setIraupena(int iraupena) {
		this.iraupena = iraupena;
	}

	// Pelikularen estreinua lortzen du
	public LocalDate getEstreinua() {
		return estreinua;
	}

	// Pelikularen estreinua ezartzen du
	public void setEstreinua(LocalDate estreinua) {
		this.estreinua = estreinua;
	}

	// Pelikularen generoa lortzen du
	public String getGeneroa() {
		return generoa;
	}

	// Pelikularen generoa ezartzen du
	public void setGeneroa(String generoa) {
		this.generoa = generoa;
	}

	// Pelikularen taquillan irabazitako dirua lortzen du
	public double getTaquila() {
		return taquila;
	}

	// Pelikularen taquillan irabazitako dirua ezartzen du
	public void setTaquila(double taquila) {
		this.taquila = taquila;
	}

	// Pelikulak saria duen (1) edo ez duen (0) lortzen du
	public int getSaria() {
		return saria;
	}

	// Pelikulak saria duen (1) edo ez duen (0) ezartzen du
	public void setSaria(int saria) {
		this.saria = saria;
	}

	// Pelikularen datuak era ulergarri batean itzultzen dituen metodoa
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("\nKodigoa: ");
		sb.append(kodigoa);
		sb.append("\nIzenburua: ");
		sb.append(izenburua);
		sb.append("\nUrtea: ");
		sb.append(urtea);
		sb.append("\nNazionalitatea: ");
		sb.append(nazionalitatea);
		sb.append("\nIraupena: ");
		sb.append(iraupena);
		sb.append("\nEstreinua: ");
		sb.append(estreinua);
		sb.append("\nGeneroa: ");
		sb.append(generoa);
		sb.append("\nTaquila: ");
		sb.append(taquila);
		sb.append("\nSaria: ");
		sb.append(saria);
		return sb.toString(); // Pelikulako datuak itzultzen ditu
	}

}
