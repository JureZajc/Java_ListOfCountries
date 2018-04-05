import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
import javax.swing.JTable;

public class TableMouseListener extends MouseAdapter {
     
    private JTable tabela;
     
    public TableMouseListener(JTable table) {
        this.tabela = table;
    }
     
    @Override
    public void mousePressed(MouseEvent event) {
        // izbor vrstice na katero klikne uporabnik
        Point tocka = event.getPoint(); // mesto klika
        int trenutnaVrstica = tabela.rowAtPoint(tocka); // vrstica klika
        tabela.setRowSelectionInterval(trenutnaVrstica, trenutnaVrstica); // izbor vrstice
    } // mousePressed
    
} // TableMouseListener