package tadArbolBB;

public class NodoBB {

	int elemento;
	NodoBB izq;
	NodoBB der;

	NodoBB(int dato) {
		elemento = dato;
		izq = der = null;
	}

	public int compare(int dato) {
		return elemento - dato;
	}

	public NodoBB hijoIzq() {
		return izq;
	}

	public NodoBB hijoDer() {
		return der;
	}

	public int valor() {
		return elemento;
	}

	public void setHIzq(NodoBB nuevo) {
		izq = nuevo;
	}

	public void setHDer(NodoBB nuevo) {
		der = nuevo;
	}

	public void setElemento(int dato) {
		elemento = dato;
	}

}
