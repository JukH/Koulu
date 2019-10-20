
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Sanakirja {

	public static void main(String[] args) throws IOException {

		Scanner lukija = new Scanner(System.in);
		//Alustetaan jokunen sana taulukoihin
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		String sana = null;

		HashMap<String, String> käännökset = new HashMap<String, String>();

		try {
			// Tiedoston avaus
			FileInputStream fis = new FileInputStream("src\\sanakirja.oma");
			ObjectInputStream luettuData = new ObjectInputStream(fis);
			käännökset = (HashMap<String, String>) luettuData.readObject(); // HUOM! Tämä oli se ongelmakohta, kumma kyllä kun lisäsin @SuppressWarnings("unchecked"),
																			// lähti toimimaan ja vaikka poistin sen, toimii yhä. 
			luettuData.close();
			fis.close();

		} catch (ClassNotFoundException c) {
			System.out.println("Luokkaa ei löydy");
			c.printStackTrace();
		} catch (EOFException e) { // HUOM! Täällä oli toinen ongelmakohta. Eclipse tarjosi automaattisesti IOException-catchia, jonka takia ei
									// voinut lisätä erikseen EOFException-catchia, jota ilman ohjelma kaatui.
			System.out.println("Tiedoston loppu saavutettiin");
		} catch (FileNotFoundException fn) {
			System.out.println("Tuotavaa tiedostoa ei löydy");
		}

		for (int i = 0; i < suomi.length; i++) {
			käännökset.put(suomi[i], englanti[i]);
		}

		// Sanojen lisäys; alustetaan uusille sanoille muuttujat
		String uusiSana;
		String uusiKäännös;

		do {
			// Otetaan uudet käännökset talteen
			System.out.print("Sana alkukielellä?  (Tyhjä sana lopettaa) ");
			uusiSana = lukija.nextLine();
			if (!uusiSana.equalsIgnoreCase("")) {
				System.out.print("Sana käännettynä?  (Tyhjä sana lopettaa) ");
				uusiKäännös = lukija.nextLine();
				//Lisätään uudet sanat HashMappiin
				käännökset.put(uusiSana, uusiKäännös);
			} else if (uusiSana == "") {
				break;
			}
		} while (!uusiSana.equalsIgnoreCase("")); //Lopetetaan looppi tyhjällä sanalla
		{

			System.out.println("Sanakirjan sisältö: " + käännökset); //Näytetään nykyinen sisältö
		}

		// Kääntämislohko, yksittäisten käännösten näyttäminen
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

		// Tallennetaan käännökset-HashMappiin
		FileOutputStream apuTied = new FileOutputStream("src\\sanakirja.oma");
		ObjectOutputStream talteen = new ObjectOutputStream(apuTied);
		talteen.writeObject(käännökset);
		talteen.close();

		lukija.close();

		System.out.println("Ohjelma lopetetaan, kiitos käynnistä!\n");

	}
}
