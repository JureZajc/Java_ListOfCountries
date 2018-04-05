import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import baza1.Drzave;
import baza1.GUIDrzaveSeznam;

public class GUIDrzaveAdd extends JFrame {
	private JPanel panOsnovna;
	private JTextField txtDrzavaSLO;
	private JTextField txtDrzavaISO;
	private JTextField txtOznaka2;
	private JTextField txtOznaka3;	
	private JTextArea taOpombe;
	private JButton btnShrani;
	private JButton btnPreklici;
	private Drzave zapisDrzava;
	private DrzaveSQL drzaveSQL;

	
	public GUIDrzaveAdd() {

		setResizable(false);
		setTitle("Država");
		setBounds(100, 100, 691, 300);
		panOsnovna = new JPanel();
		panOsnovna.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panOsnovna);
		panOsnovna.setLayout(new GridLayout(6, 2, 5, 5));
		
		JLabel lblDrzavaSlo = new JLabel("Država (SLO)");
		lblDrzavaSlo.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblDrzavaSlo);
		txtDrzavaSLO = new JTextField();
		txtDrzavaSLO.setToolTipText("Vstavi ime države");
		txtDrzavaSLO.setColumns(120);// na kaj ima to vpliv? Ker v bazi je 250 vpisov
		panOsnovna.add(txtDrzavaSLO);

		JLabel lblDrzavaISO = new JLabel("Država (ISO)");
		lblDrzavaISO.setToolTipText("Vstavi ISO drzave");
		lblDrzavaISO.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblDrzavaISO);
		
		txtDrzavaISO = new JTextField();
		txtDrzavaISO.setToolTipText("Vstavi ISO drzave");
		txtDrzavaISO.setColumns(120);
		panOsnovna.add(txtDrzavaISO);
		
		JLabel lblOznaka2 = new JLabel("Oznaka (2)");
		lblOznaka2.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblOznaka2);
		txtOznaka2 = new JTextField();
		txtOznaka2.setToolTipText("Vstavi oznako2");
		txtOznaka2.setColumns(2);
		panOsnovna.add(txtOznaka2);		
		
		JLabel lblOznaka3 = new JLabel("Oznaka (3)");
		lblOznaka3.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblOznaka3);
		txtOznaka3 = new JTextField();
		txtOznaka3.setToolTipText("Vstavi oznako3");
		txtOznaka3.setColumns(3);
		panOsnovna.add(txtOznaka3);		
		
		JLabel lblOpombe = new JLabel("Opombe");
		lblOpombe.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblOpombe);
		taOpombe = new JTextArea();
		taOpombe.setToolTipText("Vstavi opombe");
		
		JScrollPane spOpombe = new JScrollPane(taOpombe);
		panOsnovna.add(spOpombe);
		JLabel lblAktivnosti = new JLabel("");
		panOsnovna.add(lblAktivnosti);		

		JPanel panGumbi = new JPanel();
		panOsnovna.add(panGumbi);		
		
		btnShrani = new JButton("Dodaj državo Jure");
		btnShrani.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				drzaveSQL = new DrzaveSQL();
				/*zapisDrzava.setDrzavaSlo(txtDrzavaSLO.getText());
				zapisDrzava.setDrzavaIso(txtDrzavaISO.getText());
				zapisDrzava.setOznakaDvomestna(txtOznaka2.getText());
				zapisDrzava.setOznakaTromestna(txtOznaka3.getText());
				zapisDrzava.setOpomba(taOpombe.getText());*/
				//drzaveSQL.vstaviDrzave(zapisDrzava.getDrzavaSlo(), zapisDrzava.getDrzavaIso(),zapisDrzava.getOznakaDvomestna(),zapisDrzava.getOznakaTromestna(), zapisDrzava.getOpomba());
				
				drzaveSQL.vstaviDrzave(txtDrzavaSLO.getText(), txtDrzavaISO.getText(), txtOznaka2.getText(), txtOznaka3.getText(), taOpombe.getText());
				//drzaveSQL.vstaviDrzave("test", "test", "32", "322", "test");
				dispose(); // zapremo in poèistimo vir okna
			} 
		});
		
		panGumbi.add(btnShrani);
		btnPreklici = new JButton("Preklièi");
		btnPreklici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panGumbi.add(btnPreklici);		
	} // konstruktor
	
	public void dolociVrednosti(Drzave drzava, DrzaveSQL drzaveSQL) {
		zapisDrzava = drzava; 
		txtDrzavaSLO.setText(drzava.getDrzavaSlo());
		txtDrzavaISO.setText(drzava.getDrzavaIso());
		txtOznaka2.setText(drzava.getOznakaDvomestna());
		txtOznaka3.setText(drzava.getOznakaTromestna());
		taOpombe.setText(drzava.getOpomba());
		this.drzaveSQL = drzaveSQL;
	}
	
	public Drzave getZapisDrzave() {
		return zapisDrzava;
	}
}
