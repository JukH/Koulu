
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

		HashMap<String, String> k��nn�kset = new HashMap<String, String>();

		try {
			// Tiedoston avaus
			FileInputStream fis = new FileInputStream("src\\sanakirja.oma");
			ObjectInputStream luettuData = new ObjectInputStream(fis);
			k��nn�kset = (HashMap<String, String>) luettuData.readObject(); // HUOM! T�m� oli se ongelmakohta, kumma kyll� kun lis�sin @SuppressWarnings("unchecked"),
																			// l�hti toimimaan ja vaikka poistin sen, toimii yh�. 
			luettuData.close();
			fis.close();

		} catch (ClassNotFoundException c) {
			System.out.println("Luokkaa ei l�ydy");
			c.printStackTrace();
		} catch (EOFException e) { // HUOM! T��ll� oli toinen ongelmakohta. Eclipse tarjosi automaattisesti IOException-catchia, jonka takia ei
									// voinut lis�t� erikseen EOFException-catchia, jota ilman ohjelma kaatui.
			System.out.println("Tiedoston loppu saavutettiin");
		} catch (FileNotFoundException fn) {
			System.out.println("Tuotavaa tiedostoa ei l�ydy");
		}

		for (int i = 0; i < suomi.length; i++) {
			k��nn�kset.put(suomi[i], englanti[i]);
		}

		// Sanojen lis�ys; alustetaan uusille sanoille muuttujat
		String uusiSana;
		String uusiK��nn�s;

		do {
			// Otetaan uudet k��nn�kset talteen
			System.out.print("Sana alkukielell�?  (Tyhj� sana lopettaa) ");
			uusiSana = lukija.nextLine();
			if (!uusiSana.equalsIgnoreCase("")) {
				System.out.print("Sana k��nnettyn�?  (Tyhj� sana lopettaa) ");
				uusiK��nn�s = lukija.nextLine();
				//Lis�t��n uudet sanat HashMappiin
				k��nn�kset.put(uusiSana, uusiK��nn�s);
			} else if (uusiSana == "") {
				break;
			}
		} while (!uusiSana.equalsIgnoreCase("")); //Lopetetaan looppi tyhj�ll� sanalla
		{

			System.out.println("Sanakirjan sis�lt�: " + k��nn�kset); //N�ytet��n nykyinen sis�lt�
		}

		// K��nt�mislohko, yksitt�isten k��nn�sten n�ytt�minen
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

		// Tallennetaan k��nn�kset-HashMappiin
		FileOutputStream apuTied = new FileOutputStream("src\\sanakirja.oma");
		ObjectOutputStream talteen = new ObjectOutputStream(apuTied);
		talteen.writeObject(k��nn�kset);
		talteen.close();

		lukija.close();

		System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!\n");

	}
}
