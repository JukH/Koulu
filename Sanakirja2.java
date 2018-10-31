
import java.io.BufferedWriter;
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
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;


public class Sanakirja2 {
	
	
		
	

	
	
	public static void main(String[] args) throws IOException {
		
		File suomeksi = new File("src\\suomeksi.txt");
		File enkuksi = new File("src\\enkuksi.txt");
		
		if (suomeksi.createNewFile()){
			System.out.println("Luotu tiedosto: suomeksi.txt");
		} else {
			System.out.println("K�ytet��n tiedostoa: suomeksi.txt");
		}
		
		if (enkuksi.createNewFile()){
			System.out.println("Luotu tiedosto: enkuksi.txt");
		} else {
			System.out.println("K�ytet��n tiedostoa: enkuksi.txt");
		}
		
		FileWriter suomiKirjoittaja = new FileWriter(suomeksi, true);
		FileWriter enkkuKirjoittaja = new FileWriter(enkuksi, true);
		//BufferedWriter BufSuomi = new BufferedWriter(suomiKirjoittaja);
		//BufferedWriter BufEnkku = new BufferedWriter(enkkuKirjoittaja);
		
		
		
		
		
		
		
		Scanner lukija = new Scanner(System.in);
		
//		String[] suomi = { "kissa", "koira", "hevonen", "auto", "vene" };
//		String[] englanti = { "cat", "dog", "horse", "car", "boat" };
		String sana = null;
		
		
		HashMap<String, String> k��nn�kset = new HashMap<String, String>();
		
		
//		for(int i = 0; i < suomi.length ; i++) {
//			k��nn�kset.put(suomi[i], englanti[i]);
//			//Tsekkaa n�m�
//			suomiKirjoittaja.write(suomi[i]+"\n");
//			
//			enkkuKirjoittaja.write(englanti[i]+"\n");
//			
//		}
//		suomiKirjoittaja.close();
//		enkkuKirjoittaja.close();
		
		//Luetaan sanat tekstitiedostoista ja lis�t��n ne HashMappiin
		Scanner suomiLukija = new Scanner(suomeksi);
		Scanner enkkuLukija = new Scanner(enkuksi);
		
		while(suomiLukija.hasNextLine() && (enkkuLukija.hasNextLine())){
			String finska = suomiLukija.nextLine();
			String enkku = enkkuLukija.nextLine();
			k��nn�kset.put(finska, enkku);
		}
		
		
		//Sanojen lis�ys; alustetaan 
		String uusiSana;
		String uusiK��nn�s;
		
		do {
		//Lis�ill��n uudet sanat HashMappiin
		System.out.print("Sana alkukielell�?  (Tyhj� sana lopettaa) ");
		uusiSana = lukija.nextLine();
		if(!uusiSana.equalsIgnoreCase("")) {
		System.out.print("Sana k��nnettyn�?  (Tyhj� sana lopettaa) ");
		uusiK��nn�s = lukija.nextLine();
		//kirjoitetaan uudet sanat txt-tiedostoihin
		
		suomiKirjoittaja.write(uusiSana);
		suomiKirjoittaja.write(System.getProperty( "line.separator" ));
		enkkuKirjoittaja.write(uusiK��nn�s);
		enkkuKirjoittaja.write(System.getProperty( "line.separator" ));
		
		
		k��nn�kset.put(uusiSana, uusiK��nn�s);
		} 
		else if (uusiSana == "")  {
			break;
		}
		} while (!uusiSana.equalsIgnoreCase(""));{
			//Suljetaan kirjoittajat
			suomiKirjoittaja.close();
			enkkuKirjoittaja.close();
		
		System.out.println("Sanakirjan sis�lt�: " + k��nn�kset);
		}
	
		//K��nt�mislohko
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
		
		//Kirjoitetaan HashMapin sis�lt� tekstitiedostoihin JATKA T�ST�, EI TOIMI!!!!!!
//		for(int i = 0; i <= k��nn�kset.size(); i++){
//			String enkkuLis�ys = k��nn�kset.get(i);
//			suomiKirjoittaja.write(k��nn�kset.get(i) + "\n");
//			enkkuKirjoittaja.write(enkkuLis�ys + "\n");
//		}
		
		
		
		System.out.println("Ohjelma lopetetaan, kiitos k�ynnist�!\n"); 
		
		}
	}


