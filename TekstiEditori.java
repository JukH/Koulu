import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class TekstiEditori extends JFrame {

	private JPanel contentPane;
	private JEditorPane editorPane;
	private final Action action = new SwingAction();

	/**
	 * Ohjelman käynnistys. Lisähuomio: jätetty kommentteina alkuperäistä koodia paljonkin
	 * oppimista varten.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TekstiEditori frame = new TekstiEditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TekstiEditori() {
		setTitle("Oma editori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				//Scanner lukija = null;
				//File tiedosto = new File("src//gradu.txt");
				String rivi = "";
				//luetaan tiedostoa skannerin avulla rivi kerrallaan
				//varaudutaan poikkeuksiin try-catch -rakenteella
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				//Tuunataan tietoja
				valintaikkuna.setApproveButtonText("Avaa tiedosto");
				valintaikkuna.setDialogTitle("Tiedoston valinta");
				
				try {
					
					Scanner lukija = null;
					File tiedosto = new File(uusiTiedosto);
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine()+"\n";
						System.out.println(rivi);
					}} catch (FileNotFoundException p) {
						System.out.println("Tiedostoa ei löydy..");
					}
				
				editorPane.setText(rivi);
				}
			}
		);
		mntmAvaa.setIcon(new ImageIcon(TekstiEditori.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Tallennusikkuna:
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				String pääte = ".txt"; //Luodaan tiedostopääte
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath() +pääte; //Lisätään .txt tallennettaessa
				
				
				//System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
				
				try {
					PrintWriter writer = new PrintWriter( uusiTiedosto );
					String sisalto = editorPane.getText();
					
					writer.println(sisalto);
					
					writer.flush();
					writer.close();
				} catch (Exception e1) {
					
					System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
					e1.printStackTrace();
				}
			}
		});
		mnTiedosto.add(mntmTallenna);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TEE TÄMÄ
				System.exit(0);
			}
		});
		mnTiedosto.add(mntmLopeta);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mnTiedosto.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		JMenuItem mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Etsi-toiminnallisuus
				
				//TESTI_ALUE
				JFrame ikkuna = new JFrame();  ikkuna.setSize(300, 100); //Luodaan ikkuna
				ikkuna.setTitle("Etsi"); //Ikkunalle titteli
				ikkuna.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Suljetaan ruksilla ikkuna?
				ikkuna.setVisible(true); //Ikkuna näkyväksi
				BorderLayout sijoittelija = new BorderLayout(); //Tuodaan layout-manageri
				ikkuna.getContentPane().setLayout(sijoittelija); //Otetaan se käyttöön
				ikkuna.setLocationRelativeTo(null); //Asetetaan ikkuna aukeamaan keskelle

				JTextField tekstiKenttä = new JTextField(); //Etsi-ikkunan tekstikentän luonti
				tekstiKenttä.setPreferredSize( new Dimension( 200, 24 ) );
				
				JButton etsintä = new JButton("Etsi"); //Haku-nappi
				
				JPanel paneeli = new JPanel();
				
				ikkuna.getContentPane().add(paneeli, BorderLayout.CENTER);

				paneeli.add(tekstiKenttä, BorderLayout.CENTER);
				paneeli.add(etsintä, BorderLayout.EAST);
				
				etsintä.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Scanner lukija = new Scanner(System.in);
						String haku = tekstiKenttä.getText();
						//System.out.println("Anna haettava sana: ");
						//haku = lukija.nextLine();
						
						String sisalto = editorPane.getText();
						sisalto = sisalto.toLowerCase();
						
						//String haettava = "auto";
						int indeksi = sisalto.indexOf(haku);
						System.out.println("Indeksi: " + indeksi);
						
						editorPane.setSelectionColor(Color.BLUE);
						
						editorPane.setSelectedTextColor(Color.YELLOW);
						editorPane.setSelectionStart(indeksi);
						editorPane.setSelectionEnd(indeksi + haku.length());
					}
				});
				

				//TESTI_ALUE
				
				//Scanner lukija = new Scanner(System.in);
				//String haku = "Auto";
				//System.out.println("Anna haettava sana: ");
				//haku = lukija.nextLine();
				
				//String sisalto = editorPane.getText();
				//sisalto = sisalto.toLowerCase();
				
				//String haettava = "auto";
				//int indeksi = sisalto.indexOf(haku);
				//System.out.println("Indeksi: " + indeksi);
				
				//editorPane.setSelectionColor(Color.BLUE);
				
				//editorPane.setSelectedTextColor(Color.YELLOW);
				//editorPane.setSelectionStart(indeksi);
				//editorPane.setSelectionEnd(indeksi + haku.length());
			}
		});
		mnMuokkaa.add(mntmEtsi);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Etsi-toiminnallisuus
				
				//TESTI_ALUE
				JFrame ikkuna = new JFrame();  ikkuna.setSize(300, 130); //Luodaan ikkuna
				ikkuna.setTitle("Etsi ja korvaa"); //Ikkunalle titteli
				ikkuna.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Suljetaan ruksilla ikkuna?
				ikkuna.setVisible(true); //Ikkuna näkyväksi
				ikkuna.setLocationRelativeTo(null); //Asetetaan ikkuna aukeamaan keskelle
				//BorderLayout sijoittelija = new BorderLayout(); //Tuodaan layout-manageri
				//ikkuna.setLayout(sijoittelija); //Otetaan se käyttöön

				JTextField tekstiKenttä = new JTextField(); //Etsi-ikkunan tekstikentän luonti
				tekstiKenttä.setPreferredSize( new Dimension( 150, 24 ) );
				
				JTextField tekstiKenttä1 = new JTextField(); //Korvaa- tekstikentän luonti
				tekstiKenttä1.setPreferredSize( new Dimension( 150, 24 ) );
				
				JButton etsintä = new JButton("Etsi"); //Haku-nappi
				etsintä.setPreferredSize(new Dimension(100, 24)); //napin koko
				JButton korvaus = new JButton("Korvaa"); //Korvaa-nappi
				korvaus.setPreferredSize(new Dimension(100, 24));
				JPanel paneeli = new JPanel(); //napin koko
				
				

				paneeli.add(tekstiKenttä, BorderLayout.WEST); // lisätään etsi-kenttä
				paneeli.add(etsintä, BorderLayout.EAST);
				
				paneeli.add(tekstiKenttä1, BorderLayout.WEST); //lisätään korvaa-kenttä
				paneeli.add(korvaus, BorderLayout.EAST);
				
				ikkuna.getContentPane().add(paneeli, BorderLayout.CENTER);
				
				etsintä.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Scanner lukija = new Scanner(System.in);
						String haku = tekstiKenttä.getText();
						//System.out.println("Anna haettava sana: ");
						//haku = lukija.nextLine();
						
						String sisalto = editorPane.getText();
						sisalto = sisalto.toLowerCase();
						
						//String haettava = "auto";
						int indeksi = sisalto.indexOf(haku);
						System.out.println("Indeksi: " + indeksi);
						
						editorPane.setSelectionColor(Color.BLUE);
						
						editorPane.setSelectedTextColor(Color.YELLOW);
						editorPane.setSelectionStart(indeksi);
						editorPane.setSelectionEnd(indeksi + haku.length());
					}
				});
				
				korvaus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Scanner lukija = new Scanner(System.in);
						String haku = tekstiKenttä.getText();
						//System.out.println("Anna haettava sana: ");
						//haku = lukija.nextLine();
						
						String korvaus = tekstiKenttä1.getText();
						
						
						
						String sisalto = editorPane.getText();
						sisalto = sisalto.toLowerCase();
						
						String korvattu = sisalto.replaceAll(haku,korvaus);
						editorPane.setText(korvattu);
						
						//String haettava = "auto";
						int indeksi = sisalto.indexOf(haku);
						System.out.println("Indeksi: " + indeksi);
						
						editorPane.setSelectionColor(Color.GREEN);
						
						editorPane.setSelectedTextColor(Color.YELLOW);
						editorPane.setSelectionStart(indeksi);
						editorPane.setSelectionEnd(indeksi + haku.length());
					}
				});
				

				//TESTI_ALUE
				
				//Scanner lukija = new Scanner(System.in);
				//String haku = "Auto";
				//System.out.println("Anna haettava sana: ");
				//haku = lukija.nextLine();
				
				//String sisalto = editorPane.getText();
				//sisalto = sisalto.toLowerCase();
				
				//String haettava = "auto";
				//int indeksi = sisalto.indexOf(haku);
				//System.out.println("Indeksi: " + indeksi);
				
				//editorPane.setSelectionColor(Color.BLUE);
				
				//editorPane.setSelectedTextColor(Color.YELLOW);
				//editorPane.setSelectionStart(indeksi);
				//editorPane.setSelectionEnd(indeksi + haku.length());
			}
		});
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String link = "https://github.com/JukH/School";
				JOptionPane.showMessageDialog(null,
                        "(C) Jukka Heikkinen 2018\n"
                        + "Tekstieditori javan olio-ohjelmointikurssille.\n"
                        + link, 
                        "", 
                        JOptionPane.PLAIN_MESSAGE);
				

				
			}
		});
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AVAA TIEDOSTO-pikanappi
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser valintaikkuna = new JFileChooser();
						valintaikkuna.showOpenDialog(null);
						//Scanner lukija = null;
						//File tiedosto = new File("src//gradu.txt");
						String rivi = "";
						//luetaan tiedostoa skannerin avulla rivi kerrallaan
						//varaudutaan poikkeuksiin try-catch -rakenteella
						String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
						
						//Tuunataan tietoja
						valintaikkuna.setApproveButtonText("Avaa tiedosto");
						valintaikkuna.setDialogTitle("Tiedoston valinta");
						
						try {
							
							Scanner lukija = null;
							File tiedosto = new File(uusiTiedosto);
							lukija = new Scanner(tiedosto);
							
							while (lukija.hasNextLine()) {
								rivi += lukija.nextLine()+"\n";
								System.out.println(rivi);
							}} catch (FileNotFoundException p) {
								System.out.println("Tiedostoa ei löydy..");
							}
						
						editorPane.setText(rivi);
						}
					}
				);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(TekstiEditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Tallenna-pikanappi
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				//System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
				
				try {
					PrintWriter writer = new PrintWriter( uusiTiedosto );
					String sisalto = editorPane.getText();
					
					writer.println(sisalto);
					
					writer.flush();
					writer.close();
				} catch (Exception e1) {
					
					System.out.println("Tiedoston tallennuksessa tapahtui virhe!");
					e1.printStackTrace();
			}
		}
		});
		btnNewButton_1.setIcon(new ImageIcon(TekstiEditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Leikkaa-nappi
				String poistettava = editorPane.getSelectedText();
				String korvaus = "";
				String sisalto = editorPane.getText();
				
				
				String korvattu = sisalto.replaceAll(poistettava,korvaus);
				editorPane.setText(korvattu);
				
				
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(TekstiEditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		toolBar.add(btnNewButton_2);
		
		editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
	}

	public JEditorPane getEditorPane() {
		return editorPane;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
