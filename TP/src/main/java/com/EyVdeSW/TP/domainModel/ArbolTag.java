package com.EyVdeSW.TP.domainModel;

public class ArbolTag 
{
	private Tag raiz;
	
	public ArbolTag(Tag raiz) {
		this.raiz=raiz;
	}

	public Tag getRaiz() {
		return raiz;
	}

	public void setRaiz(Tag raiz) {
		this.raiz = raiz;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((raiz == null) ? 0 : raiz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArbolTag other = (ArbolTag) obj;
		if (raiz == null) {
			if (other.raiz != null)
				return false;
		} else if (!raiz.equals(other.raiz))
			return false;
		return true;
	}

	
}
