	/*	DrzaveSQL.java
	 * Vzpostavitev povezave z zbirko podatkov in izvajanje SQL stavkov za tabelo drzave
	 * */
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	
	public class DrzaveSQL {
		static final String DB_URL = "jdbc:postgresql://localhost:5432/poste"; // URL podatkovne baze 
		static final String UPORABNIK = "postgres"; // uporabniško ime
		static final String GESLO = "postgres"; // geslo 
		private Connection povezavaDB = null; // upravljanje povezave z zbirko podatkov
		private PreparedStatement selectDrzaveVse = null; // Select stavek, seznam vseh držav
		private PreparedStatement selectDrzaveNaziv = null; // Select stavek, seznam držav po polju drzavaSlo
		private PreparedStatement insertDrzave = null; // Insert stavek, tabela drzave
		private PreparedStatement updateDrzave = null; // Insert stavek, tabela drzave
		private PreparedStatement deleteDrzave = null; // Delete stavek, tabela drzave				
		
		public DrzaveSQL () {
			try { // vzpostavitev povezave
				povezavaDB = DriverManager.getConnection(DB_URL, UPORABNIK, GESLO);
				// ustvarimo objekt razreda PreparedStatement, select stavek
				selectDrzaveVse = povezavaDB.prepareStatement("SELECT id_drzave, drzava_slo, drzava_iso, oznaka_dvomestna, oznaka_tromestna," +
				" opomba FROM drzave order by drzava_slo");
				// ustvarimo objekt razreda PreparedStatement, select stavek
				selectDrzaveNaziv = povezavaDB.prepareStatement("SELECT id_drzave, drzava_slo, drzava_iso, oznaka_dvomestna, oznaka_tromestna, " + 
				" opomba FROM drzave where drzava_slo like ?");
				// ustvarimo objekt razreda PreparedStatement, insert stavek
				insertDrzave = povezavaDB.prepareStatement("INSERT INTO drzave(drzava_slo,drzava_iso,oznaka_dvomestna,oznaka_tromestna, opomba) " + 
				" VALUES (?, ?, ?, ?, ?) ");
				// ustvarimo objekt razreda PreparedStatement, update stavek
				updateDrzave = povezavaDB.prepareStatement("UPDATE drzave SET drzava_slo=?,drzava_iso=?,oznaka_dvomestna=?,oznaka_tromestna=?, opomba=? where id_drzave=? " );
				// ustvarimo objekt razreda PreparedStatement, delete stavek				
				deleteDrzave = povezavaDB.prepareStatement("Delete from drzave where id_drzave=? " );
				
			}	// try 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit( 1 ); // izhod iz programa
			} // catch
		} // konstruktor
		
		// vrne seznam vseh drzav, elemnti objekti razreda Države
		public List <Drzave> getDrzaveVsi() {
			List <Drzave> lstDrzave = null;  // seznam vseh držav
			ResultSet rsDrzave = null; // zapisi rezultata poizvedbe;
			
			try {
				rsDrzave = selectDrzaveVse.executeQuery(); // izvršimo poizvedbo
				lstDrzave = new ArrayList<Drzave>(); // ustvarimo povezan seznam drzav
				
				// obdelava vseh vrstic rezultata poizvedbe
				while (rsDrzave.next()) {
					// v seznam dodamo nov element, rezultat poizvedbe prenesemo v objekt razreda osebe
					// vbistvu tole dodamo paè nov row v prejšno drzave.java kjer smo deklalirali new Drzave
					lstDrzave.add(new Drzave (	rsDrzave.getInt("id_drzave"), 	rsDrzave.getString("drzava_slo"), rsDrzave.getString("drzava_iso"), 
							rsDrzave.getString("oznaka_dvomestna"), rsDrzave.getString("oznaka_tromestna"), rsDrzave.getString("opomba")			));
				} // while
			}  // try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();	
			} // catch
			finally {
				try {
					rsDrzave.close(); // zapremo rezultat poizvedbe
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}			
			} // finally
			return lstDrzave;
			
		} // vrniDrzaveVse
		
		// vrne seznam drzav, kriterij naziv drzave
		public List <Drzave> getDrzaveKriterij(String nazivDrzave) {
			List <Drzave> lstDrzave = null;  // seznam vseh držav
			ResultSet rsDrzave = null; // zapisi rezultata poizvedbe;
			
			try {
				selectDrzaveNaziv.setString(1, nazivDrzave); // doloèimo vrednost paramerra
				rsDrzave = selectDrzaveNaziv.executeQuery(); // izvršimo poizvedbo
				lstDrzave = new ArrayList<Drzave>(); // ustvarimo povezan seznam drzav
				
				// obdelava vseh vrstic rezultata poizvedbe
				while (rsDrzave.next()) {
					// v seznam dodamo nov element, rezultat poizvedbe prenesemo v objekt razreda osebe
					lstDrzave.add(new Drzave (	rsDrzave.getInt("id_drzave"), 	rsDrzave.getString("drzava_slo"), rsDrzave.getString("drzava_iso"), 
							rsDrzave.getString("oznaka_dvomestna"), rsDrzave.getString("oznaka_tromestna"), rsDrzave.getString("opomba")			));
				} // while
			}  // try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();	
			} // catch
			finally {
				try {
					rsDrzave.close(); // zapremo rezultat poizvedbe
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}			
			} // finally
			return lstDrzave;
		} // vrniDrzaveNaziv	
	
		
		// vstavi novo državo
		public int vstaviDrzave(String drzava_slo,String drzava_iso,String oznaka_dvomestna,String oznaka_tromestna,String opomba) {
			int rezultat = 0; // rezultat izvršitve insert stavka
			try {
				povezavaDB.setAutoCommit(false);
	
				// doloèimo vrednosti parametrov
				insertDrzave.setString(1,drzava_slo);
				insertDrzave.setString(2,drzava_iso);
				insertDrzave.setString(3,oznaka_dvomestna);
				insertDrzave.setString(4,oznaka_tromestna);
				insertDrzave.setString(5,opomba);
				rezultat = insertDrzave.executeUpdate(); // izvršimo insert, vrne število vstavljenih zapisov
				povezavaDB.commit();
			} // try
			catch (SQLException e) {
				e.printStackTrace();
				try {
					povezavaDB.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} // catch
			return rezultat;
		} // dodajDrzave	
		
		// spremeni državo
		public int spremeniDrzave(String drzava_slo,String drzava_iso,String oznaka_dvomestna,String oznaka_tromestna,String opomba, int idDrzave) {
			int rezultat = 0; // rezultat izvršitve insert stavka
			try {
				povezavaDB.setAutoCommit(false);
	
				// doloèimo vrednosti parametrov
				updateDrzave.setString(1,drzava_slo);
				updateDrzave.setString(2,drzava_iso);
				updateDrzave.setString(3,oznaka_dvomestna);
				updateDrzave.setString(4,oznaka_tromestna);
				updateDrzave.setString(5,opomba);
				updateDrzave.setInt(6,idDrzave);				
				rezultat = updateDrzave.executeUpdate(); // izvršimo update, vrne število vstavljenih zapisov
				povezavaDB.commit();
			} // try
			catch (SQLException e) {
				e.printStackTrace();
				try {
					povezavaDB.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} // catch
			return rezultat;
		} // spremeniDrzave			
		
		
		// izbriši državo
		public int izbrisiDrzave(int idDrzave) {
			int rezultat = 0; // rezultat izvršitve insert stavka
			try {
				povezavaDB.setAutoCommit(false);
				// doloèimo vrednosti parametrov
				deleteDrzave.setInt(1,idDrzave);
				rezultat = deleteDrzave.executeUpdate(); // izvršimo delete, vrne število vstavljenih zapisov
				povezavaDB.commit();
			} // try
			catch (SQLException e) {
				e.printStackTrace();
				try {
					povezavaDB.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} // catch
			return rezultat;
		} // spremeniDrzave			
	
		// zapre povezavo z zbirko podatkov
		public void zapri()
		{
			try{
				povezavaDB.close();
			} // try
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();
			} // catch
		} // zapri
		
	} // razred DrzaveSQLStavki
