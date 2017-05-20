package tadArbolBB;

public class TestABB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArbolBB a = new ArbolBB();

		a.insertar(5);
		a.insertar(8);
		a.insertar(2);
		a.insertar(15);
		a.insertar(1);
		a.insertar(4);
		a.insertar(7);
		a.insertar(6);
		System.out.println(a.toString(a.raiz()));
		a.eliminar(1);
		System.out.println(a.toString(a.raiz()));
		System.out.println("La altura es " + a.altura());

	}

}
