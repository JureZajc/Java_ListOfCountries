	/* Drzave.java 	 * Razred države.	 *  
	 * tukaj smo samo naredili tako da smo vse atribute tabele drzave dali kot private String
	 * */
	public class Drzave {
		// atributi
		private int idDrzave;
		private String drzavaSlo;
		private String drzavaIso;
		private String oznakaDvomestna;
		private String oznakaTromestna;
		private String opomba;
		
		// konstruktor s parametri
		public Drzave(int idDrzave, String drzavaSlo, String drzavaIso, String oznakaDvomestna, String oznakaTromestna,
				String opomba) {
			super();
			this.idDrzave = idDrzave;
			this.drzavaSlo = drzavaSlo;
			this.drzavaIso = drzavaIso;
			this.oznakaDvomestna = oznakaDvomestna;
			this.oznakaTromestna = oznakaTromestna;
			this.opomba = opomba;
		} // konstruktor
	
		// konstruktor brez parametrov
		public Drzave() {
		}  // konstruktor

		public int getIdDrzave() {
			return idDrzave;
		} // vrniIdDrzave
	
		public void setIdDrzave(int idDrzave) {
			this.idDrzave = idDrzave;
		} // dolociIdDrzave
	
		public String getDrzavaSlo() {
			return drzavaSlo;
		} // vrniDrzavaSlo
	
		public void setDrzavaSlo(String drzavaSlo) {
			this.drzavaSlo = drzavaSlo;
		} // dolociDrzavaSlo
	
		public String getDrzavaIso() {
			return drzavaIso;
		} // vrniDrzavaIso
	
		public void setDrzavaIso(String drzavaIso) {
			this.drzavaIso = drzavaIso;
		} // dolociDrzavaIso
	
		public String getOznakaDvomestna() {
			return oznakaDvomestna;
		} // vrniOznakaDvomestna
	
		public void setOznakaDvomestna(String oznakaDvomestna) {
			this.oznakaDvomestna = oznakaDvomestna;
		} // dolociOznakaDvomestna
	
		public String getOznakaTromestna() {
			return oznakaTromestna;
		} // vrniOznakaTromestna
	
		public void setOznakaTromestna(String oznakaTromestna) {
			this.oznakaTromestna = oznakaTromestna;
		} // dolociOznakaTromestna
	
		public String getOpomba() {
			return opomba;
		} // vrniOpomba
	
		public void setOpomba(String opomba) {
			this.opomba = opomba;
		} // dolociOpomba
	
	} // razred Drzave