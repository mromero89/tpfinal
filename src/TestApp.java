
import static org.junit.jupiter.api.Assertions.*;import java.sql.SQLException;
import java.util.ArrayList;

import dominio.*;

import org.junit.jupiter.api.Test;

import dao.ABMOrdenPedido;

class TestApp {

	@Test
	void alta() {
		//Crear orden de pedido
		
		//Crear items
		ItemPedido i1 = new ItemPedido();
		i1.setCantidad(22);
		i1.setCosto(23);
		i1.setInsumo("Arena");
		
		ItemPedido i2 = new ItemPedido();
		i2.setCantidad(21);
		i2.setCosto(23);
		i2.setInsumo("cal");
		
		ArrayList<ItemPedido> items = new ArrayList<ItemPedido>();
		items.add(i1); items.add(i2);
		
		ABMOrdenPedido.guardarordenpedido("999", "Santa Fe", "2020/12/11", items);
		
	}
	
	@Test
	void verificarcreada() {
		ArrayList<OrdenPedido> creadas = new ArrayList<OrdenPedido>();
		ABMOrdenPedido.consultarordenescreadas(creadas);
		boolean encontrada = false;
		for (OrdenPedido i : creadas) {
			int pedidoact = i.getNropedido();
			if(pedidoact == 999) {
				encontrada = true;
			}
		}
		assertTrue(encontrada);
	}
	
	@Test
	void verificarcancelada() {
		try {
			ArrayList<OrdenPedido> lista = new ArrayList<OrdenPedido>();
			DetalleItems a = new DetalleItems(999, "Santa Fe");
			ABMOrdenPedido.consultarorden(lista, "CANCELADA");
			boolean verif = false;
			for (OrdenPedido i : lista) {
				int pedidoact = i.getNropedido();
				if (pedidoact == 123) {
					verif = true;
				}
			}
			assertTrue(verif);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("excepcion no esperada");
		}
	}
	

	
	

}