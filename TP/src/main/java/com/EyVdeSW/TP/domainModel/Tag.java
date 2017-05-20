package com.EyVdeSW.TP.domainModel;

import java.util.List;

public class Tag
{
	private NodoTag raiz;
	
	
	public NodoTag getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoTag raiz) {
		this.raiz = raiz;
	}
	
	public class NodoTag{
		
		private List<NodoTag> hijos;
		private String nombre;
		private List<AccionGeneral>	accionesGenerales;
		
		public NodoTag(List<NodoTag> hijos, String nombre, List<AccionGeneral> accionesGenerales) {
			this.hijos = hijos;
			this.nombre = nombre;
			this.accionesGenerales = accionesGenerales;
		}
		
		public NodoTag(String nombre) {
			this.nombre = nombre;
			this.hijos=null;
			this.accionesGenerales=null;
		}

		public List<NodoTag> getHijos(){
			return hijos;
		}

		public void setHijos(List<NodoTag> hijos){
			this.hijos = hijos;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public List<AccionGeneral> getAccionesGenerales(){
			return accionesGenerales;
		}

		public void setAccionesGenerales(List<AccionGeneral> accionesGenerales){
			this.accionesGenerales = accionesGenerales;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NodoTag other = (NodoTag) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (accionesGenerales == null) {
				if (other.accionesGenerales != null)
					return false;
			} else if (!accionesGenerales.equals(other.accionesGenerales))
				return false;
			if (hijos == null) {
				if (other.hijos != null)
					return false;
			} else if (!hijos.equals(other.hijos))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}

		@Override
		public String toString(){
			return "Tag: " + nombre;
		}

		private Tag getOuterType() {
			return Tag.this;
		}
		
	}


}
