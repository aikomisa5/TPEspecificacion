package tadArbol;

public class Arbol {
	private Nodo raiz;

	public Arbol() {
		this.raiz = null;
	}

	public boolean vacio() {
		return (raiz == null);
	}

	public Nodo getRaiz() {
		return this.raiz;
	}

	public int altura() {
		return altura(raiz);
	}

	private int altura(Nodo n) {
		if (n != null)
			return (Math.max(altura(n.getIzq()) + 1, altura(n.getDer()) + 1));
		else
			return 0;
	}

	public void insertar(int dato) {
		raiz = insertar(dato, raiz);
	}

	private Nodo insertar(int dato, Nodo n) {
		if (n == null) {
			n = new Nodo(dato);
		} else {
			if (dato < n.getDato())
				if (n.getIzq() == null)
					n.setIzq(new Nodo(dato));
				else
					insertar(dato, n.getIzq());
			else if (n.getDer() == null)
				n.setDer(new Nodo(dato));
			else
				insertar(dato, n.getDer());
		}
		return n;
	}

	public String preOrden() {
		return preOrden(raiz);
	}

	private String preOrden(Nodo n) {
		String ret = new String();
		if (n != null) {
			ret += n.toString() + " ";
			ret += preOrden(n.getIzq()) + " ";
			ret += preOrden(n.getDer()) + " ";
		} else
			ret = ".";
		return ret;
	}

	public String inOrden() {
		return inOrden(raiz);
	}

	private String inOrden(Nodo n) {
		String ret = new String();
		if (n != null) {
			ret += inOrden(n.getIzq()) + " ";
			ret += n.toString() + " ";
			ret += inOrden(n.getDer()) + " ";
		} else
			ret = ".";
		return ret;
	}

	public String posOrden() {
		return posOrden(raiz);
	}

	private String posOrden(Nodo n) {
		String ret = new String();
		if (n != null) {
			ret += posOrden(n.getIzq()) + " ";
			ret += posOrden(n.getDer()) + " ";
			ret += n.toString() + " ";
		} else
			ret = ".";
		return ret;
	}

	public int getCantidadNodosNivel(int nivelDestino) {
		return getCantidadNodosNivel(raiz, 0, nivelDestino);
	}

	private int getCantidadNodosNivel(Nodo n, int nivelActual, int nivelDestino) {
		if (n != null) {
			if (nivelActual == nivelDestino)
				return 1;
			else
				return getCantidadNodosNivel(n.getIzq(), nivelActual + 1, nivelDestino)
						+ getCantidadNodosNivel(n.getDer(), nivelActual + 1, nivelDestino);
		}
		return 0;
	}

	private boolean esHoja(Nodo n) {
		if (n.getDer() == null && n.getIzq() == null)
			return true;
		else
			return false;
	}

	public int getCantHojas() {
		return getCantHojas(raiz);
	}

	private int getCantHojas(Nodo n) {
		if (n != null) {
			if (esHoja(n)) {
				return 1;
			} else
				return getCantHojas(n.getDer()) + getCantHojas(n.getIzq());
		}
		return 0;
	}

	private boolean esPadre(Nodo padre, Nodo hijo) {
		if (padre.getDer() == hijo || padre.getIzq() == hijo)
			return true;
		else
			return false;
	}

	public Nodo getPadre(Nodo n) {
		if (n == raiz)
			return null;
		else
			return getPadre(raiz, n);
	}

	private Nodo getPadre(Nodo padre, Nodo hijo) {
		if (padre == null)
			return null;
		if (esPadre(padre, hijo))
			return padre;
		else {
			Nodo i = getPadre(padre.getIzq(), hijo);
			Nodo d = getPadre(padre.getDer(), hijo);
			if (i != null)
				return i;
			else
				return d;
		}
	}

	public boolean buscarDato(Integer dato) {
		return buscarDato(raiz, dato);
	}

	private boolean buscarDato(Nodo n, Integer dato) {
		if (n == null)
			return false;
		else if (n.getDato() == dato)
			return true;
		else if (n.getDato() > dato)
			return buscarDato(n.getIzq(), dato);
		else
			return buscarDato(n.getDer(), dato);

	}

	public Nodo buscarNodo(Integer dato) {
		return buscarNodo(raiz, dato);
	}

	private Nodo buscarNodo(Nodo n, Integer dato) {
		if (n == null)
			return null;
		else if (n.getDato() == dato)
			return n;
		else if (n.getDato() > dato)
			return buscarNodo(n.getIzq(), dato);
		else
			return buscarNodo(n.getDer(), dato);
	}
	
	public Integer minimo() {
		return minimo(raiz);
	}
	
	private Integer minimo(Nodo n) {
		if (n.getIzq() == null)
			return n.getDato();
		else
			return minimo(n.getIzq());
	}
	
	private Nodo minimoNodo(Nodo n) {
		return buscarNodo(n,minimo(n));
	}
	
	private Nodo maximoNodo(Nodo n) {
		return buscarNodo(n,maximo(n));
	}
	
	public Integer maximo() {
		return maximo(raiz);
	}
	
	private Integer maximo(Nodo n) {
		if(n.getDer() == null)
			return n.getDato();
		else
			return maximo(n.getDer());
	}	

	private void desvincularNodo(Nodo n) {
		Nodo p = getPadre(n);
		if (p != null) {
			if (p.getDer() == n)
				p.setDer(null);
			else
				p.setIzq(null);
		}
	}
	
	private void vincularNodo(Nodo padre, Nodo hijo, Nodo nuevoHijo) {
		if(padre.getDer() == hijo)
			padre.setDer(nuevoHijo);
		else
			padre.setIzq(nuevoHijo);
	} 

	/*public boolean eliminar(Integer dato) {
		Nodo n = buscarNodo(raiz, dato);
		if (n == null)
			return false;

		Nodo p = getPadre(n);// Evaluar el caso de la raiz.
		if (p != null) {
			if (esHoja(n)) {
				desvincularNodo(n);
			} else if (n.getDer() == null) {			//n solo tiene un subarbol izquierdo.
				Nodo i = maximoNodo(n.getIzq()); 		//Se busca el maximo del subarbol Izquierdo
				vincularNodo(p,n,i);					//Se lo vincula al padre de n
				n.setIzq(null);							//Se desvincula a n de su subarbol Izquierdo
			} else if (n.getIzq() == null) {			//n solo tiene un subarbol derecho.
				;
			}
		} else {
			// Caso de raiz.
		}
		return true;
	}*/
	
	
	
	public void eliminar(Integer dato) {
		raiz = eliminar(dato, raiz);
	}
	
	private Nodo eliminar(int dato, Nodo actual) {
		if (actual == null)
			return null;
		else if (actual.compare(dato) == 0) {

			actual = eliminarNodo(actual);
			return actual;

		} else {

			if (actual.compare(dato) > 0)
				actual.setIzq(eliminar(dato, actual.getIzq()));
			else
				actual.setDer(eliminar(dato, actual.getDer()));
			return actual;
		}
	}
	
	private Nodo eliminarNodo(Nodo actual) {

		if (actual.getIzq() == null && actual.getDer() == null)
			return null;

		if (actual.getIzq() != null && actual.getDer() == null)
			return actual.getIzq();

		if (actual.getIzq() == null && actual.getDer() != null)
			return actual.getDer();

		Nodo aux = minimoNodo(actual.getDer());
		actual.setDato(aux.getDato());
		aux = null;
		return actual;
	}


}
