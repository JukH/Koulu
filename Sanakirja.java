import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Sanakirja {
	
	
		
	

	public static void main(String[] args) throws IOException {
		
		/**
		//Tiedoston tallennus
		File tiedosto = new File("C:\\Users\\jukka\\workspace\\Sanakirja\\src\\sanakirja.oma");
		FileOutputStream apuTied = new FileOutputStream("C:\\Users\\jukka\\workspace\\Sanakirja\\src\\sanakirja.oma");
		ObjectOutputStream talteen = new ObjectOutputStream(apuTied); **/
		
		
		
		
	    
		//Tiedoston avaus loppu
		
		
		Scanner lukija = new Scanner(System.in);
		
		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		String sana = null;
		
		//Testailualue
		HashMap<String, String> käännökset = new HashMap();
		
		/**try {
			//Tiedoston avaus
			FileInputStream fis = new FileInputStream("C:\\Users\\jukka\\workspace\\Sanakirja\\src\\sanakirja.oma");
			ObjectInputStream luettuData = new ObjectInputStream(fis);
			käännökset = (HashMap) luettuData.readObject();
			luettuData.close();
			fis.close();// TODO Auto-generated catch block
			
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
			return;
		}catch(ClassNotFoundException c){
			System.out.println("Luokkaa ei löydy");
			c.printStackTrace();
			return;
		} **/
		
		
		
		
			
		
		
		
		//testailualue
		for(int i = 0; i < suomi.length ; i++) {
			käännökset.put(suomi[i], englanti[i]);
		}
		
		//Sanojen lisäys:
		String uusiSana;
		String uusiKäännös;
		
		do {
		
		System.out.print("Sana alkukielellä?  (Tyhjä sana lopettaa) ");
		uusiSana = lukija.nextLine();
		if(!uusiSana.equalsIgnoreCase("")) {
		System.out.print("Sana käännettynä?  (Tyhjä sana lopettaa) ");
		uusiKäännös = lukija.nextLine();
		
		käännökset.put(uusiSana, uusiKäännös);
		} 
		else if (uusiSana == "")  {
			break;
		}
		} while (!uusiSana.equalsIgnoreCase(""));{
		
		
		System.out.println("Sanakirjan sisältö: " + käännökset);
		}
	
		
		do  {
			System.out.print("Minkä sanan käännöksen haluat tietää? (Tyhjä sana lopettaa) \n");
			sana = lukija.nextLine();
			if(käännökset.containsKey(sana)){
			System.out.println("Sanan " + "\"" + sana + "\"" +" käännös on " + "\"" + käännökset.get(sana) + "\"" + "\n");
			} else if (sana.equalsIgnoreCase("")){
				break;
			}
			else {
				System.out.println("Sanan " + "\"" + sana + "\"" +" käännös on " + "tuntematon!" + "\n");
			}
		} while (sana != "");
		
		//Tallennus
		
		/**
		talteen.writeObject(käännökset);
		talteen.close();
		apuTied.close();
		**/
		
		System.out.println("Ohjelma lopetetaan, kiitos käynnistä!\n"); 
		
		//txt-tallennus
		
		
		
		
		
		

		}
	}


