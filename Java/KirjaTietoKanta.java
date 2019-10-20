

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KirjaTietoKanta {

	public static void main(String[] args) {

		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		
		
		try {

			// Luodaan tietokantayhteys
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7265083", "sql7265083", "UU8TSn6Z6G");

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
			Statement stmt = con.createStatement();

			// Luodaan tulosjoukko, johon sijoitetaan kyselyn tulos
			ResultSet rs = stmt.executeQuery("SELECT * FROM kirja");
			int i = 0;

			// Tulosjoukko k‰yd‰‰n silmukassa l‰pi
			while (rs.next()) {
				//konsoliin tulostus, j‰tet‰‰n optioksi kommenttina
				//System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));

				data.add( new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) } ); //tiedot taulukkoon
				i++;
			}
		
			con.close();
			// Varaudutaan virheisiin
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Tapahtui virhe");
		}

		JScrollPane scrollPane = new JScrollPane();
		
		//Luodaan tablemodel ja tehd‰‰n asiaankuuluvat sarakkeet
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Kirjan nimi");
		model.addColumn("Tekij‰");
		model.addColumn("Julkaisuvuosi");

		JTable table = new JTable(model);


		//Tulostetaan taulukon data JTableen n‰kyville riveiksi
		for (int i=0; i < data.size(); i++ ) {
			model.addRow(data.get(i));
		}
		
		JFrame ikkuna = new JFrame(); //Luodaan ikkuna
		JPanel paneeli = new JPanel(); //Luodaan ikkunaan paneeli
		JButton lis‰‰ = new JButton("Lis‰‰"); //Lis‰‰-napin luonti
		
		
		// TƒSTƒ ALKAA PƒƒIKKUNAN LISƒƒ-NAPIN KOODI
		lis‰‰.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFrame lis‰ys = new JFrame();   //Luodaan ikkuna
				lis‰ys.setSize(400, 200);
				lis‰ys.setTitle("Kirjan lis‰ys"); //Ikkunalle titteli
				lis‰ys.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Suljetaan ruksilla ikkuna
				lis‰ys.setVisible(true); //Ikkuna n‰kyv‰ksi
				lis‰ys.setLocationRelativeTo(null); //Asetetaan ikkuna aukeamaan keskelle
				JTextField tekstiKentt‰ = new JTextField(); //Kirjan nimi-tekstikent‰n luonti
				tekstiKentt‰.setPreferredSize( new Dimension( 150, 20 ) );
				
				JTextField tekstiKentt‰1 = new JTextField(); //Tekij‰n nimi-tekstikent‰n luonti
				tekstiKentt‰1.setPreferredSize( new Dimension( 150, 20 ) );
				
				JTextField tekstiKentt‰2 = new JTextField(); //Julkaisuvuosi-tekstikentt‰
				tekstiKentt‰2.setPreferredSize( new Dimension( 150, 20 ) );
				
				
				JButton lis‰‰Kirja = new JButton("Lis‰‰ kirjastoon"); //Kirjan lis‰ys-nappi
				lis‰‰Kirja.setPreferredSize(new Dimension(140, 24)); //napin koko
				
				//Luodaan useampi paneeli layouttia silm‰ll‰pit‰en
				JPanel paneeli = new JPanel();
				JPanel paneeli1 = new JPanel();
				JPanel paneeli2 = new JPanel();
				JPanel masterPanel = new JPanel();
				JPanel flowPanel = new JPanel(new FlowLayout());
				
				JLabel kirjanNimi = new JLabel("Kirjan nimi:");
				kirjanNimi.setPreferredSize(new Dimension(100,24));
				JLabel tekij‰nNimi = new JLabel("Tekij‰:");
				tekij‰nNimi.setPreferredSize(new Dimension(100, 24));
				JLabel julkaisuVuosi = new JLabel("Julkaisuvuosi:");
				julkaisuVuosi.setPreferredSize(new Dimension(100, 24));
				
				paneeli.add(kirjanNimi, BorderLayout.EAST);
				paneeli.add(tekstiKentt‰, BorderLayout.WEST); // lis‰t‰‰n kirjan nimi-kentt‰
				paneeli1.add(tekij‰nNimi, BorderLayout.EAST);
				paneeli1.add(tekstiKentt‰1, BorderLayout.WEST); // lis‰t‰‰n tekij‰-kentt‰
				paneeli2.add(julkaisuVuosi, BorderLayout.EAST);
				paneeli2.add(tekstiKentt‰2, BorderLayout.WEST); // lis‰t‰‰n julkaisuvuosi-kentt‰
				
				
				flowPanel.add(lis‰‰Kirja); //Rakennellaan muotoilu
				masterPanel.add(paneeli, BorderLayout.NORTH);
				masterPanel.add(paneeli1, BorderLayout.CENTER);
				masterPanel.add(paneeli2, BorderLayout.SOUTH);
				lis‰ys.getContentPane().add(masterPanel, BorderLayout.CENTER);
				lis‰ys.getContentPane().add(flowPanel, BorderLayout.SOUTH);
				
				
				
				//Kirjan lis‰ysnapin toiminnot
				lis‰‰Kirja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				try {
					//Alustetaan muuttujat joita tarvitaan kun kaivetaan data tekstikentist‰
					String nimi = tekstiKentt‰.getText();
					String tekij‰ = tekstiKentt‰1.getText();
					String vuosiTekstin‰ = tekstiKentt‰2.getText();
					ArrayList<Object[]> data1 = new ArrayList<Object[]>(); //Luodaan uusi array-lista jotta vanhat tiedot eiv‰t tulostu tuplana lis‰yksen j‰lkeen p‰ivitett‰ess‰
					int vuosi = Integer.parseInt(vuosiTekstin‰);
					
					// Luodaan tietokantayhteys
					Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7265083", "sql7265083", "UU8TSn6Z6G");

					// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
					Statement stmt = con.createStatement();
					
					if(nimi != null || tekij‰ != null || vuosiTekstin‰ != null){ //lis‰t‰‰n ehto jotta kaikki kent‰t t‰ytet‰‰n
					String query = " insert into kirja (kirjan_nimi, tekij‰, julkaisuvuosi)"
					        + " values (?, ?, ?)";
					
					// Luodaan preparestatement datan muokkaamista varten tietokannassa
					PreparedStatement preparedStmt = con.prepareStatement(query);
				      preparedStmt.setString (1, nimi);
				      preparedStmt.setString (2, tekij‰);
				      preparedStmt.setInt(3, vuosi);
				      
				   // Suoritetaan preparedstatement
				      preparedStmt.execute();

				      // Nakataan viesti ett‰ kirjan lis‰ys onnistui
				      JOptionPane.showMessageDialog(masterPanel, "Kirja lis‰tty!");
				      lis‰ys.dispose();
				     
				      
					} else { //Varoitellaan jos tietoja lis‰‰m‰tt‰
						JOptionPane.showMessageDialog(masterPanel, "Et antanut kaikkia tietoja!");
					}
					
						
					

					// Luodaan uudelleen tulosjoukko, johon sijoitetaan kyselyn tulos
					ResultSet rs = stmt.executeQuery("SELECT * FROM kirja");
					int i = 0;
					
					// Lis‰t‰‰n tietokannasta data arraylistiin
					while (rs.next()) {
						//konsoliin tulostus, j‰tet‰‰n optioksi kommenttina
						//System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));

						data1.add( new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) } ); //tiedot arraylistiin
						i++;
					}
					
					// Tyhjennet‰‰n vanha taulu jottei data n‰y tuplana (kˆmpelˆ tapa p‰ivitt‰‰?)	
					 DefaultTableModel model1=(DefaultTableModel)table.getModel();
			            int rc= model.getRowCount();
			            for(i = 0;i<rc;i++){
			                model1.removeRow(0);
			            }     
					
					con.close();
					
					// Tulostetaan tuore taulu kirjalis‰yksen j‰lkeen JTableen
					for (int x=0; x < data1.size(); x++ ) { 
						model.addRow(data1.get(x));
					}
					// Varaudutaan virheisiin
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Tapahtui virhe");
				}
			}
		});
			}
		});
		
		// Poistonapin toiminnallisuudet alkaa t‰st‰
		JButton poista = new JButton("Poista");
		poista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int valittuRivi = table.getSelectedRow(); 
				
				// Otetaan talteen valittu rivi oliona (index 0 = kirjan nimi-sarake)
				Object id = table.getValueAt(valittuRivi, 0);
				
				//muunnetaan kiinniotettu olio Stringiksi (kirjan nimi)
				String olioMuunnettu = String.valueOf(id);
				
				// Poistetaan poistettu kirja myˆs JTablesta
				int modelRow = table.convertRowIndexToModel(valittuRivi);
				model.removeRow(modelRow);
				
				// Poistetaan valittu kirja nimen perusteella tietokannasta
				String poistoK‰sky = "DELETE FROM kirja WHERE kirjan_nimi = " + "'"+olioMuunnettu+"'" + ";";
				
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7265083", "sql7265083", "UU8TSn6Z6G");
					
					// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
					Statement stmt = con.createStatement();
					stmt.executeUpdate(poistoK‰sky);
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "'" + olioMuunnettu + "'" + " poistettu tietokannasta!");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Tapahtui virhe");
					e1.printStackTrace();
				}
				


				
			}
		});
		JMenuBar menu = new JMenuBar();
		
		
		ikkuna.setTitle("Kirjatietokanta");
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		paneeli.add(lis‰‰);
		paneeli.add(poista);
		
		ikkuna.getContentPane().add(scrollPane, BorderLayout.CENTER);
		ikkuna.getContentPane().add(menu, BorderLayout.NORTH);
		
		JMenu mnNewMenu_1 = new JMenu("Tietoja ohjelmasta");
		menu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("N\u00E4yt\u00E4 ohjelman tiedot");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String link = "https://github.com/JukH/School";
				JOptionPane.showMessageDialog(null,
                        "(C) Jukka Heikkinen 2018\n"
                        + "Tietokantaohjelma javan olio-ohjelmointikurssille.\n"
                        + link, 
                        "", 
                        JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuBar menuBar = new JMenuBar();
		menu.add(menuBar);
		ikkuna.getContentPane().add(paneeli, BorderLayout.SOUTH);
		
		scrollPane.setViewportView(table);

		ikkuna.pack();
		ikkuna.setLocationRelativeTo(null); //Asetetaan ikkuna aukeamaan keskelle
		ikkuna.setVisible(true);

	}

}