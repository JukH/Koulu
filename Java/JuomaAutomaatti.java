import java.util.Scanner;

public class JuomaAutomaatti {

	private int teetä;
	private int kahvia;
	private int kaakaota;

	public JuomaAutomaatti() {
		teetä = 50;
		kahvia = 50;
		kaakaota = 50;
	}

	public void valmistaKahvi() {
		if (kahvia >= 10) {
			kahvia -= 10;
			System.out.println("Odota hetki, kahvia valmistetaan.");
		} else {
			System.out.println("Kahvia ei enää jäljellä! Täytä säiliö.");
		}
	}

	public void valmistaTee() {
		if (teetä >= 10) {
			teetä -= 10;
			System.out.println("Odota hetki, teetä valmistetaan.");
		} else {
			System.out.println("Teetä ei enää jäljellä! Täytä säiliö.");
		}
	}

	public void valmistaKaakao() {
		if (kaakaota >= 10) {
			kaakaota -= 10;
			System.out.println("Odota hetki, kaakaota valmistetaan.");
		} else {
			System.out.println("Kaakaota ei enää jäljellä! Täytä säiliö.");
		}
	}

	public void täytäSäiliöt() { // Tein huvin vuoksi tämän metodin jolla voi täyttää säiliöt ja jatkaa ohjelmaa.
		kahvia = 50;
		teetä = 50;
		kaakaota = 50;
		System.out.println("Säiliöt täytetty!");
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
		return "Kahvia jäljellä: " + kahvia + ", teetä jäljellä: " + teetä + ", kaakaota jäljellä: " + kaakaota;
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
			System.out.println("5. Täytä säiliöt");
			System.out.println();
			System.out.println("*******************************");
			valinta = lukija.nextInt();
			System.out.println();
			if (valinta == 1) {
				if (maatti.kahvia >= 10) { // Poistetaan tällä if-elsellä tilttimahdollisuus jos säiliö on tyhjä
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
				if (maatti.teetä >= 10) { // Poistetaan tällä if-elsellä tilttimahdollisuus jos säiliö on tyhjä
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
				if (maatti.kaakaota >= 10) { // Poistetaan tällä if-elsellä tilttimahdollisuus jos säiliö on tyhjä
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
				maatti.täytäSäiliöt();
				System.out.println(maatti);
				continue;
			}

			// Lisätään ehto valinnan oikeellisuudesta
			else if (valinta != 1 || valinta != 2 || valinta != 3 || valinta != 4 || valinta != 5) {
				System.out.println("Valitse 1-5!");

			}
		} while (valinta != 4);

	}

}
