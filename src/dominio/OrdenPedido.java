package dominio;

public class OrdenPedido {
	
	private int nropedido;
	private String plantaorigen, plantadestino;
	private String fechasolicitud, fechaentrega, estado;
	public int getNropedido() {
		return nropedido;
	}
	public void setNropedido(int nropedido) {
		this.nropedido = nropedido;
	}
	public String getPlantaorigen() {
		return plantaorigen;
	}
	public void setPlantaorigen(String plantaorigen) {
		this.plantaorigen = plantaorigen;
	}
	public String getPlantadestino() {
		return plantadestino;
	}
	public void setPlantadestino(String plantadestino) {
		this.plantadestino = plantadestino;
	}
	public String getFechasolicitud() {
		return fechasolicitud;
	}
	public void setFechasolicitud(String fechasolicitud) {
		this.fechasolicitud = fechasolicitud;
	}
	public String getFechaentrega() {
		return fechaentrega;
	}
	public void setFechaentrega(String fechaentrega) {
		this.fechaentrega = fechaentrega;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
