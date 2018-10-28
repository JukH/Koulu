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
		HashMap<String, String> k��nn�kset = new HashMap();
		
		/**try {
			//Tiedoston avaus
			FileInputStream fis = new FileInputStream("C:\\Users\\jukka\\workspace\\Sanakirja\\src\\sanakirja.oma");
			ObjectInputStream luettuData = new ObjectInputStream(fis);
			k��nn�kset = (HashMap) luettuData.readObject();
			luettuData.close();
			fis.close();// TODO Auto-generated catch block
			
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
			return;
		}catch(ClassNotFoundException c){
			System.out.println("Luokkaa ei l�ydy");
			c.printStackTrace();
			return;
		} **/
		
		
		
		
			
		
		
		
		//testailualue
		for(int i = 0; i < suomi.length ; i++) {
			k��nn�kset.put(suomi[i], englanti[i]);
		}
		
		//Sanojen lis�ys:
		String uusiSana;
		String uusiK��nn�s;
		
		do {
		
		System.out.print("Sana alkukielell�?  (Tyhj� sana lopettaa) ");
		uusiSana = lukija.nextLine();
		if(!uusiSana.equalsIgnoreCase("")) {
		System.out.print("Sana k��nnettyn�?  (Tyhj� sana lopettaa) ");
		uusiK��nn�s = lukija.nextLine();
		
		k��nn�kset.put(uusiSana, uusiK��nn�s);
		} 
		else if (uusiSana == "")  {
			break;
		}
		} while (!uusiSana.equalsIgnoreCase(""));{
		
		
		System.out.println("Sanakirjan sis�lt�: " + k��nn�kset);
		}
	
		
		do  {
			System.out.print("Mink� sanan k��nn�ksen haluat tiet��? (Tyhj� sana lopettaa) \n");
			sana = lukija.nextLine();
			if(k��nn�kset.containsKey(sana)){
			System.out.println("Sanan " + "\"" + sana + "\"" +" k��nn�s on " + "\"" + k��nn�kset.get(sana) + "\"" + "\n");
			} else if (sana.equalsIgnoreCase("")){
				break;
			}
			else {
				System.out.println("Sanan " + "\"" + sana + "\"" +" k��nn�s on " + "tuntematon!" + "\n");
			}
		} while (sana != "");
		
		//Tallennus
		
		/**
		talteen.writeObject(k��nn�kset);
		talteen.close();
		apuTied.close();
		**/
		
		System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!\n"); 
		
		//txt-tallennus
		
		
		
		
		
		

		}
	}


