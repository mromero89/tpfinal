package dominio;

public class Camion {
	
	private String patente;
	private String modelo;
	private int kmrec;
	private int costokm;
	private int costoh;
	private String fechacompra;
	
	public Camion(){
		
	}
	
	public Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra){
		this.patente = patente;
		this.modelo = modelo;
		this.kmrec = kmrec;
		this.costokm = costokm;
		this.costoh = costoh;
		this.fechacompra = fechacompra;
		
		
		
	}

	public String getPatente() {
		return patente;
	}

	public String getModelo() {
		return modelo;
	}

	public int getKmrec() {
		return kmrec;
	}

	public int getCostokm() {
		return costokm;
	}

	public int getCostoh() {
		return costoh;
	}

	public String getFechacompra() {
		return fechacompra;
	}

}
