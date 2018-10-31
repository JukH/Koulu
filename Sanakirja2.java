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
		//Tsekataan l�ytyyk� tiedostot, jos ei niin luodaan.
		if (suomeksi.createNewFile()) {
			System.out.println("Luotu tiedosto: suomeksi.txt");
		} else {
			System.out.println("K�ytet��n tiedostoa: suomeksi.txt");
		}

		if (enkuksi.createNewFile()) {
			System.out.println("Luotu tiedosto: enkuksi.txt");
		} else {
			System.out.println("K�ytet��n tiedostoa: enkuksi.txt");
		}
		//Alustetaan kirjoittajat joilla lis�t��n sanat txt-tiedostoihin.
		FileWriter suomiKirjoittaja = new FileWriter(suomeksi, true);
		FileWriter enkkuKirjoittaja = new FileWriter(enkuksi, true);

		Scanner lukija = new Scanner(System.in);

		String sana = null;
		//Luodaan HashMap k��nn�ksille, suomenkielinen sana = avain, englanninkielinen = arvo.
		HashMap<String, String> k��nn�kset = new HashMap<String, String>();

		// Luetaan sanat tekstitiedostoista ja lis�t��n ne HashMappiin
		Scanner suomiLukija = new Scanner(suomeksi);
		Scanner enkkuLukija = new Scanner(enkuksi);
		//Luetaan kunnes rivej� ei en�� ole
		while (suomiLukija.hasNextLine() && (enkkuLukija.hasNextLine())) {
			String finska = suomiLukija.nextLine();
			String enkku = enkkuLukija.nextLine();
			k��nn�kset.put(finska, enkku);
		}

		// Sanojen lis�ys; alustetaan uusille sanoille ja k��nn�ksille muuttujat
		String uusiSana;
		String uusiK��nn�s;

		do {
			//Tallennetaan uudet sanat muuttujiin
			System.out.print("Sana alkukielell�?  (Tyhj� sana lopettaa) ");
			uusiSana = lukija.nextLine();
			if (!uusiSana.equalsIgnoreCase("")) {
				System.out.print("Sana k��nnettyn�?  (Tyhj� sana lopettaa) ");
				uusiK��nn�s = lukija.nextLine();
				
				// kirjoitetaan uudet sanat txt-tiedostoihin
				suomiKirjoittaja.write(uusiSana);
				suomiKirjoittaja.write(System.getProperty("line.separator")); //Lis�t��n rivinvaihto, ei n�kynyt notepadissa pelk�ll� \n-k�skyll�.
				enkkuKirjoittaja.write(uusiK��nn�s);
				enkkuKirjoittaja.write(System.getProperty("line.separator")); // -||-
				//Lis�t��n uudet k��nn�kset hashmappiin
				k��nn�kset.put(uusiSana, uusiK��nn�s);
			} else if (uusiSana == "") {
				break;
			}
		} while (!uusiSana.equalsIgnoreCase(""));
		{
			// Suljetaan kirjoittajat
			suomiKirjoittaja.close();
			enkkuKirjoittaja.close();

			System.out.println("Sanakirjan sis�lt�: " + k��nn�kset);
		}

		// K��nt�mislohko, n�ytet��n haluttu k��nn�s
		do {
			System.out.print("Mink� sanan k��nn�ksen haluat tiet��? (Tyhj� sana lopettaa) \n");
			sana = lukija.nextLine();
			if (k��nn�kset.containsKey(sana)) {
				System.out.println(
						"Sanan " + "\"" + sana + "\"" + " k��nn�s on " + "\"" + k��nn�kset.get(sana) + "\"" + "\n");
			} else if (sana.equalsIgnoreCase("")) {
				break;
			} else {
				System.out.println("Sanan " + "\"" + sana + "\"" + " k��nn�s on " + "tuntematon!" + "\n");
			}
		} while (sana != "");
		
		//Suljetaan turhat lukijat ja poistutaan ohjelmasta
		lukija.close();
		suomiLukija.close();
		enkkuLukija.close();
		System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!\n");

	}
}