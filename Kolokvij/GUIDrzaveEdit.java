import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GUIDrzaveEdit extends JFrame {

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
	
	public GUIDrzaveEdit() {
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
		txtDrzavaSLO.setColumns(120);// na kaj ima to vpliv? Ker v bazi je 250 vpisov
		panOsnovna.add(txtDrzavaSLO);

		JLabel lblDrzavaISO = new JLabel("Država (ISO)");
		lblDrzavaISO.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblDrzavaISO);
		txtDrzavaISO = new JTextField();
		txtDrzavaISO.setColumns(120);
		panOsnovna.add(txtDrzavaISO);
		
		JLabel lblOznaka2 = new JLabel("Oznaka (2)");
		lblOznaka2.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblOznaka2);
		txtOznaka2 = new JTextField();
		txtOznaka2.setColumns(2);
		panOsnovna.add(txtOznaka2);		
		JLabel lblOznaka3 = new JLabel("Oznaka (3)");
		lblOznaka3.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblOznaka3);
		txtOznaka3 = new JTextField();
		txtOznaka3.setColumns(3);
		panOsnovna.add(txtOznaka3);		
		JLabel lblOpombe = new JLabel("Opombe");
		lblOpombe.setHorizontalAlignment(SwingConstants.CENTER);
		panOsnovna.add(lblOpombe);
		taOpombe = new JTextArea();
		JScrollPane spOpombe = new JScrollPane(taOpombe);
		panOsnovna.add(spOpombe);
		JLabel lblAktivnosti = new JLabel("");
		panOsnovna.add(lblAktivnosti);		

		JPanel panGumbi = new JPanel();
		panOsnovna.add(panGumbi);		
		
		btnShrani = new JButton("Shrani");
		btnShrani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zapisDrzava.setDrzavaSlo(txtDrzavaSLO.getText());
				zapisDrzava.setDrzavaIso(txtDrzavaISO.getText());
				zapisDrzava.setOznakaDvomestna(txtOznaka2.getText());
				zapisDrzava.setOznakaTromestna(txtOznaka3.getText());
				zapisDrzava.setOpomba(taOpombe.getText());
				drzaveSQL.spremeniDrzave(zapisDrzava.getDrzavaSlo(), zapisDrzava.getDrzavaIso(), zapisDrzava.getOznakaDvomestna(), zapisDrzava.getOznakaTromestna(), zapisDrzava.getOpomba(), zapisDrzava.getIdDrzave() );
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

} // razred GUIDrzaveEdit
