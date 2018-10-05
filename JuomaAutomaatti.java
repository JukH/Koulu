import java.util.Scanner;

public class JuomaAutomaatti {

	private int teet�;
	private int kahvia;
	private int kaakaota;

	public JuomaAutomaatti() {
		teet� = 50;
		kahvia = 50;
		kaakaota = 50;
	}

	public void valmistaKahvi() {
		if (kahvia >= 10) {
			kahvia -= 10;
			System.out.println("Odota hetki, kahvia valmistetaan.");
		} else {
			System.out.println("Kahvia ei en�� j�ljell�! T�yt� s�ili�.");
		}
	}

	public void valmistaTee() {
		if (teet� >= 10) {
			teet� -= 10;
			System.out.println("Odota hetki, teet� valmistetaan.");
		} else {
			System.out.println("Teet� ei en�� j�ljell�! T�yt� s�ili�.");
		}
	}

	public void valmistaKaakao() {
		if (kaakaota >= 10) {
			kaakaota -= 10;
			System.out.println("Odota hetki, kaakaota valmistetaan.");
		} else {
			System.out.println("Kaakaota ei en�� j�ljell�! T�yt� s�ili�.");
		}
	}

	public void t�yt�S�ili�t() { // Tein huvin vuoksi t�m�n metodin jolla voi t�ytt�� s�ili�t ja jatkaa ohjelmaa.
		kahvia = 50;
		teet� = 50;
		kaakaota = 50;
		System.out.println("S�ili�t t�ytetty!");
	}

	public boolean onnistuuko() {
		int luku = (int) (Math.random() * 100 + 1);
		if (luku <= 25 && luku > 0) {
			return false;
		} else {
			return true;
		}

	}

	public String toString() {
		return "Kahvia j�ljell�: " + kahvia + ", teet� j�ljell�: " + teet� + ", kaakaota j�ljell�: " + kaakaota;
	}

	public static void main(String[] args) {

		JuomaAutomaatti maatti = new JuomaAutomaatti();
		int valinta = 0;
		Scanner lukija = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("*******Juoma-Automaatti*******");
			System.out.println();
			System.out.println("1. Kahvi");
			System.out.println("2. Tee");
			System.out.println("3. Kaakao");
			System.out.println("4. Lopeta");
			System.out.println("5. T�yt� s�ili�t");
			System.out.println();
			System.out.println("*******************************");
			valinta = lukija.nextInt();
			System.out.println();
			if (valinta == 1) {
				if (maatti.kahvia >= 10) { // Poistetaan t�ll� if-elsell� tilttimahdollisuus jos s�ili� on tyhj�
					maatti.onnistuuko();
					if (maatti.onnistuuko() == true) {
						maatti.valmistaKahvi();
						System.out.println(maatti);
						continue;
					} else {
						System.out.println("Ei onnistu, kiitos kuitenkin maksustasi!");
					}
				} else {
					maatti.valmistaKahvi();
					System.out.println(maatti);
					continue;
				}
			} else if (valinta == 2) {
				if (maatti.teet� >= 10) { // Poistetaan t�ll� if-elsell� tilttimahdollisuus jos s�ili� on tyhj�
					maatti.onnistuuko();
					if (maatti.onnistuuko() == true) {
						maatti.valmistaTee();
						System.out.println(maatti);
						continue;
					} else {
						System.out.println("Ei onnistu, kiitos kuitenkin maksustasi!");
					}
				} else {
					maatti.valmistaTee();
					System.out.println(maatti);
					continue;
				}
			} else if (valinta == 3) {
				if (maatti.kaakaota >= 10) { // Poistetaan t�ll� if-elsell� tilttimahdollisuus jos s�ili� on tyhj�
					maatti.onnistuuko();
					if (maatti.onnistuuko() == true) {
						maatti.valmistaKaakao();
						System.out.println(maatti);
						continue;
					} else {
						System.out.println("Ei onnistu, kiitos kuitenkin maksustasi!");
					}
				} else {
					maatti.valmistaKaakao();
					System.out.println(maatti);
					continue;
				}
			} else if (valinta == 5) {
				maatti.t�yt�S�ili�t();
				System.out.println(maatti);
				continue;
			}

			// Lis�t��n ehto valinnan oikeellisuudesta
			else if (valinta != 1 || valinta != 2 || valinta != 3 || valinta != 4 || valinta != 5) {
				System.out.println("Valitse 1-5!");

			}
		} while (valinta != 4);

	}

}
