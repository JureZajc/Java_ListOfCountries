// razšititev vmesnika AbstractTableModel
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelDrzave extends AbstractTableModel {
	private List <Drzave> lstDrzave = null; // seznam vseh držav
	// tabela vrednosti naslovne vrstice
	String naslovnaVrstica[] = new String[] {"ID","Država SLO", "Država ISO", "Oznaka (2)", "Oznaka (3)", "Opombe"};

	public TableModelDrzave(List<Drzave> lstDrzave) {
		super();
		this.lstDrzave = lstDrzave;
	} // konstruktor TableModelDrzave

	@Override
	public int getColumnCount() {
		return 6; // število stolpcev 
	} // getColumnCount

	@Override
	public int getRowCount() {
		return lstDrzave.size(); // število elementov seznama
	} // getRowCount

	@Override
	public Object getValueAt(int vrstica, int stolpec) {
		Drzave drzava = null;
		drzava = lstDrzave.get(vrstica); 
		switch (stolpec) {
			case 0:
				return drzava.getIdDrzave();
			case 1:
				return drzava.getDrzavaSlo();
			case 2:
				return drzava.getDrzavaIso();
			case 3:
				return drzava.getOznakaDvomestna();
			case 4:
				return drzava.getOznakaTromestna();
			case 5:
				return drzava.getOpomba();
			default :
				return "";
			} // switch
		} // getValueAt

	
	public String getColumnName(int stolpec) {
		return naslovnaVrstica[stolpec];
	} // getColumnName
	
} // razred TableModelDrzave
