import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

public class GUIDrzaveSeznam extends JFrame {

	private JPanel panOsnovni;
	private JTable tblDrzave;
	private DrzaveSQL drzaveSQL;
	private List <Drzave> lstDrzave;
	private TableModelDrzave tmDrzave; 
	private JScrollPane spDrzave; // drsno podro�je tabele dr�ave
	private TableRowSorter<TableModelDrzave> trsDrzave;
	
    private JPopupMenu popupMenu; // vsebinski meni, desni klik
    private JMenuItem menuItemIzbrisi; // ukaz izbri�i
    private JMenuItem menuItemDodaj;// ukaz dodaj
	
	public GUIDrzaveSeznam() {
		super("Dr�ave");
		drzaveSQL = new DrzaveSQL();
		// izvr�imo poizvedbo, vse dr�ave iz tabele dr�ave
		lstDrzave = drzaveSQL.getDrzaveVsi();
		tmDrzave = new TableModelDrzave(lstDrzave);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		panOsnovni = new JPanel();
		panOsnovni.setBorder(new EmptyBorder(5, 5, 5, 5));
		panOsnovni.setLayout(new BorderLayout(0, 0));
		setContentPane(panOsnovni);
		tblDrzave = new JTable();
		tblDrzave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent dogodek) {
				// preverimo dvoklik
				if (dogodek.getClickCount()==2) {
					int izbranaVrstica = tblDrzave.getSelectedRow();
					
					GUIDrzaveEdit edtDrzave = new GUIDrzaveEdit();
					edtDrzave.dolociVrednosti(lstDrzave.get(izbranaVrstica), drzaveSQL);
					//drzava = lstDrzave.get(izbranaVrstica);
				//	System.out.printf("\nDr�ava: %s",drzava.getDrzavaSlo());
					edtDrzave.setVisible(true);
				} // dvoklik
			}
		});
		tblDrzave.setModel(tmDrzave);
		tblDrzave.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		trsDrzave = new TableRowSorter<TableModelDrzave>(tmDrzave);
		tblDrzave.setRowSorter(trsDrzave);
		// skrijemo stolpec ID, indeks 0
		tblDrzave.getColumnModel().getColumn(0).setMinWidth(0);
		tblDrzave.getColumnModel().getColumn(0).setMaxWidth(0);
		
		spDrzave = new JScrollPane(tblDrzave);
		panOsnovni.add(spDrzave, BorderLayout.CENTER);
		
		
        // vsebinski meni, brisanje
        popupMenu = new JPopupMenu();
        menuItemIzbrisi = new JMenuItem("Izbri�i"); 
        menuItemDodaj = new JMenuItem("Dodaj");
        menuItemDodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JMenuItem menu2 = (JMenuItem) e.getSource();
				if (menu2 == menuItemDodaj) {
					GUIDrzaveAdd dodajDrzave = new GUIDrzaveAdd();
					//int izbranaVrstica = tblDrzave.getSelectedRow();
					
					//dodajDrzave.dolociVrednosti(lstDrzave.get(izbranaVrstica), drzaveSQL);
					dodajDrzave.setVisible(true);
					//lstDrzave.add(lstDrzave.get(izbranaVrstica));
					//tmDrzave.fireTableRowsInserted(1,1);
					
				}
				
			}
		});
        menuItemIzbrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        JMenuItem menu2 = (JMenuItem) e.getSource();
		        if (menu2 == menuItemIzbrisi) {
		        	  int izbranaVrstica = tblDrzave.getSelectedRow();
		        	  drzaveSQL.izbrisiDrzave(lstDrzave.get(izbranaVrstica).getIdDrzave());
		        	  lstDrzave.remove(lstDrzave.get(izbranaVrstica));
		      		  tmDrzave.fireTableDataChanged();
		        }
			}
		});
        popupMenu.add(menuItemDodaj);
        tblDrzave.setComponentPopupMenu(popupMenu);
        tblDrzave.addMouseListener(new TableMouseListener(tblDrzave));
       
        popupMenu.add(menuItemIzbrisi);
        // dolo�imo vsebinski meni tabele
        tblDrzave.setComponentPopupMenu(popupMenu);
        tblDrzave.addMouseListener(new TableMouseListener(tblDrzave));
        

        
	}
	
	public static void main(String[] args) {
			GUIDrzaveSeznam oknoGridDrzave = new GUIDrzaveSeznam();
			oknoGridDrzave.setVisible(true);
	} // main
	
} // GUIDrzaveSeznam
