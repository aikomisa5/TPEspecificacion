package tadArbolBB;

public class ArbolBB {
	NodoBB raiz;

	public ArbolBB() {
		raiz = null;
	}

	private boolean vacio() {
		if (raiz == null)
			return true;
		else
			return false;
	}

	public NodoBB raiz() {
		return raiz;
	}

	public void insertar(int dato) {

		raiz = insertar(raiz, dato);
	}

	public int altura() {
		if (vacio())
			return 0;
		else
			return Math.max(altura(raiz.hijoDer()), altura(raiz.hijoIzq()));
	}

	public NodoBB buscar(int dato) {
		if (vacio())
			return null;
		else {

			return buscar(this.raiz(), dato);
		}

	}

	public void eliminar(int dato) {
		raiz = eliminar(dato, raiz);
	}

	public String toString(NodoBB actual) { // inOrder String ret ="";
		String ret = "";
		if (actual.hijoIzq() != null) {
			ret = ret + toString(actual.hijoIzq());
		}
		ret = ret + actual.valor() + " ";
		if (actual.hijoDer() != null) {
			ret = ret + toString(actual.hijoDer());
		}
		return ret;

	}

	private NodoBB insertar(NodoBB actual, int dato) {
		if (actual != null) {
			int res = actual.compare(dato);
			if (res > 0)
				actual.setHIzq(insertar(actual.hijoIzq(), dato));
			if (res < 0)
				actual.setHDer(this.insertar(actual.hijoDer(), dato));
		} else {
			actual = new NodoBB(dato);
		}
		return actual;

	}

	private int altura(NodoBB actual) {
		if (actual == null)
			return 1;
		else
			return Math.max(altura(actual.hijoDer()) + 1, altura(actual.hijoIzq()) + 1);
	}

	private NodoBB buscar(NodoBB actual, int dato) {
		if (actual == null)
			return null;
		else {
			int res = actual.compare(dato);
			if (res == 0)
				return actual;
			else if (res > 0)
				return buscar(actual.hijoIzq(), dato);
			else
				return buscar(actual.hijoDer(), dato);
		}
	}

	private NodoBB eliminar(int dato, NodoBB actual) {
		if (actual == null)
			return null;
		else if (actual.compare(dato) == 0) {

			actual = eliminarNodo(actual);
			return actual;

		} else {

			if (actual.compare(dato) > 0)
				actual.setHIzq(eliminar(dato, actual.hijoIzq()));
			else
				actual.setHDer(eliminar(dato, actual.hijoDer()));
			return actual;
		}
	}

	private NodoBB eliminarNodo(NodoBB actual) {

		if (actual.hijoIzq() == null && actual.hijoDer() == null)
			return null;

		if (actual.hijoIzq() != null && actual.hijoDer() == null)
			return actual.hijoIzq();

		if (actual.hijoIzq() == null && actual.hijoDer() != null)
			return actual.hijoDer();

		NodoBB aux = buscarMin(actual.hijoDer());
		actual.setElemento(aux.valor());
		aux = null;
		return actual;
	}

	private NodoBB buscarMin(NodoBB actual) {
		if (actual.hijoIzq() == null)
			return actual;
		else {
			return buscarMin(actual.hijoIzq());
		}
	}

}
