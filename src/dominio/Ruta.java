package dominio;

public class Ruta {
	
	private Planta origen, destino;
	private int distanciakm;
	private int duracionh;
	private int pesomax;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Planta getOrigen() {
		return origen;
	}
	public void setOrigen(Planta origen) {
		this.origen = origen;
	}
	public Planta getDestino() {
		return destino;
	}
	public void setDestino(Planta destino) {
		this.destino = destino;
	}
	public int getDistanciakm() {
		return distanciakm;
	}
	public void setDistanciakm(int distanciakm) {
		this.distanciakm = distanciakm;
	}
	public int getDuracionh() {
		return duracionh;
	}
	public void setDuracionh(int duracionh) {
		this.duracionh = duracionh;
	}
	public int getPesomax() {
		return pesomax;
	}
	public void setPesomax(int pesomax) {
		this.pesomax = pesomax;
	}
	

}
