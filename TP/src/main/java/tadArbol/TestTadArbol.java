package tadArbol;

import org.junit.Assert;
import org.junit.Test;

public class TestTadArbol {

	@Test
	public void testNodo() {
		Nodo n = new Nodo();

		Assert.assertEquals(0, n.getDato());
		Assert.assertNull(n.getDer());
		Assert.assertNull(n.getIzq());

		n.setDato(3);
		Nodo hi = new Nodo();
		Nodo hd = new Nodo();
		n.setIzq(hi);
		n.setDer(hd);

		Assert.assertEquals(3, n.getDato());
		Assert.assertNotNull(n.getIzq());
		Assert.assertNotNull(n.getDer());
	}

	@Test
	public void testArbol() {

		Arbol a = new Arbol();
		Assert.assertNull(a.getRaiz());

		a.insertar(1);
		Assert.assertNotNull(a);
		Assert.assertEquals(1, a.getRaiz().getDato());
		a.insertar(0);
		Assert.assertNotNull(a.getRaiz().getIzq());
		Assert.assertEquals(0, a.getRaiz().getIzq().getDato());
		a.insertar(3);
		Assert.assertNotNull(a.getRaiz().getDer());
		Assert.assertEquals(3, a.getRaiz().getDer().getDato());
		a.insertar(2);
		Assert.assertNotNull(a.getRaiz().getDer().getIzq());
		Assert.assertEquals(2, a.getRaiz().getDer().getIzq().getDato());
		a.insertar(5);		
		Assert.assertNotNull(a.getRaiz().getDer().getDer());
		Assert.assertEquals(5, a.getRaiz().getDer().getDer().getDato());
		
		Assert.assertNull(a.getPadre(a.getRaiz()));
		Assert.assertSame(a.getRaiz(), a.getPadre(a.getRaiz().getDer()));
		Assert.assertSame(a.getRaiz().getIzq(), a.getPadre(a.getRaiz().getIzq().getDer()));
		Assert.assertNotSame(a.getRaiz(), a.getPadre(a.getRaiz().getDer().getIzq()));
		Assert.assertSame(a.getPadre(a.getRaiz().getDer().getIzq()), a.getPadre(a.getRaiz().getDer().getDer()));

		 System.out.println("Pre orden:"+a.preOrden());
		 System.out.println("In orden:"+a.inOrden());
		 System.out.println("Pos orden:"+a.posOrden());
				
		Assert.assertEquals(1, a.getCantidadNodosNivel(0));
		Assert.assertEquals(2, a.getCantidadNodosNivel(1));
		Assert.assertEquals(2, a.getCantidadNodosNivel(2));
		Assert.assertEquals(0, a.getCantidadNodosNivel(3));
		
		Arbol b = new Arbol();

		b.insertar(5);
		b.insertar(9);
		b.insertar(2);
		b.insertar(1);
		b.insertar(3);
		b.insertar(7);
		b.insertar(10);

		Assert.assertEquals(1, b.getCantidadNodosNivel(0));
		Assert.assertEquals(2, b.getCantidadNodosNivel(1));
		Assert.assertEquals(4, b.getCantidadNodosNivel(2));
		Assert.assertEquals(0, b.getCantidadNodosNivel(3));

		Assert.assertEquals(3, a.getCantHojas());
		Assert.assertEquals(4, b.getCantHojas());
		
	}

}
