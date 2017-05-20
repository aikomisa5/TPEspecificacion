package tadArbol;

public class Nodo {
	private Integer dato;
	private Nodo izq;
	private Nodo der;
	
	
	public Nodo(){
		this.dato= 0;
		this.izq = null;
		this.der = null;		
	}
	
	public Nodo(Integer dato){
		this.dato = dato;
		this.izq = null;
		this.der = null;
	}
		
	public int getDato() {
		return dato;
	}
	
	public void setDato(int dato) {
		this.dato = dato;
	}
	
	public Nodo getIzq() {
		return izq;
	}
	
	public void setIzq(Nodo izq) {
		this.izq = izq;
	}
	
	public Nodo getDer() {
		return der;
	}
	
	public void setDer(Nodo der) {
		this.der = der;
	}
	
	public int compare(Integer dato) {
		return this.dato - dato;
	}
	
	@Override
	public String toString() {
		return dato.toString();
	}
	
	

}
