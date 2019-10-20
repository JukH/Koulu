import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Sanakirja2 {

	public static void main(String[] args) throws IOException {
		//Alustetaan txt-tiedostot
		File suomeksi = new File("src\\suomeksi.txt");
		File enkuksi = new File("src\\enkuksi.txt");
		//Tsekataan löytyykö tiedostot, jos ei niin luodaan.
		if (suomeksi.createNewFile()) {
			System.out.println("Luotu tiedosto: suomeksi.txt");
		} else {
			System.out.println("Käytetään tiedostoa: suomeksi.txt");
		}

		if (enkuksi.createNewFile()) {
			System.out.println("Luotu tiedosto: enkuksi.txt");
		} else {
			System.out.println("Käytetään tiedostoa: enkuksi.txt");
		}
		//Alustetaan kirjoittajat joilla lisätään sanat txt-tiedostoihin.
		FileWriter suomiKirjoittaja = new FileWriter(suomeksi, true);
		FileWriter enkkuKirjoittaja = new FileWriter(enkuksi, true);

		Scanner lukija = new Scanner(System.in);

		String sana = null;
		//Luodaan HashMap käännöksille, suomenkielinen sana = avain, englanninkielinen = arvo.
		HashMap<String, String> käännökset = new HashMap<String, String>();

		// Luetaan sanat tekstitiedostoista ja lisätään ne HashMappiin
		Scanner suomiLukija = new Scanner(suomeksi);
		Scanner enkkuLukija = new Scanner(enkuksi);
		//Luetaan kunnes rivejä ei enää ole
		while (suomiLukija.hasNextLine() && (enkkuLukija.hasNextLine())) {
			String finska = suomiLukija.nextLine();
			String enkku = enkkuLukija.nextLine();
			käännökset.put(finska, enkku);
		}

		// Sanojen lisäys; alustetaan uusille sanoille ja käännöksille muuttujat
		String uusiSana;
		String uusiKäännös;

		do {
			//Tallennetaan uudet sanat muuttujiin
			System.out.print("Sana alkukielellä?  (Tyhjä sana lopettaa) ");
			uusiSana = lukija.nextLine();
			if (!uusiSana.equalsIgnoreCase("")) {
				System.out.print("Sana käännettynä?  (Tyhjä sana lopettaa) ");
				uusiKäännös = lukija.nextLine();
				
				// kirjoitetaan uudet sanat txt-tiedostoihin
				suomiKirjoittaja.write(uusiSana);
				suomiKirjoittaja.write(System.getProperty("line.separator")); //Lisätään rivinvaihto, ei näkynyt notepadissa pelkällä \n-käskyllä.
				enkkuKirjoittaja.write(uusiKäännös);
				enkkuKirjoittaja.write(System.getProperty("line.separator")); // -||-
				//Lisätään uudet käännökset hashmappiin
				käännökset.put(uusiSana, uusiKäännös);
			} else if (uusiSana == "") {
				break;
			}
		} while (!uusiSana.equalsIgnoreCase(""));
		{
			// Suljetaan kirjoittajat
			suomiKirjoittaja.close();
			enkkuKirjoittaja.close();

			System.out.println("Sanakirjan sisältö: " + käännökset);
		}

		// Kääntämislohko, näytetään haluttu käännös
		do {
			System.out.print("Minkä sanan käännöksen haluat tietää? (Tyhjä sana lopettaa) \n");
			sana = lukija.nextLine();
			if (käännökset.containsKey(sana)) {
				System.out.println(
						"Sanan " + "\"" + sana + "\"" + " käännös on " + "\"" + käännökset.get(sana) + "\"" + "\n");
			} else if (sana.equalsIgnoreCase("")) {
				break;
			} else {
				System.out.println("Sanan " + "\"" + sana + "\"" + " käännös on " + "tuntematon!" + "\n");
			}
		} while (sana != "");
		
		//Suljetaan turhat lukijat ja poistutaan ohjelmasta
		lukija.close();
		suomiLukija.close();
		enkkuLukija.close();
		System.out.println("Ohjelma lopetetaan, kiitos käynnistä!\n");

	}
}