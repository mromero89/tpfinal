package dominio;

public class ItemPedido {
	private String insumo;
	private int cantidad;
	private int costo;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}
	public String getInsumo() {
		return insumo;
	}
	
	
	

}
