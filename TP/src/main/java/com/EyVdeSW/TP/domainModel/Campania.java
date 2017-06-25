package com.EyVdeSW.TP.domainModel;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Campania {

	private List<AccionPublicitaria> accionesPublicitarias;
	private List<Tag> tagsAsociados;
	private Usuario usuario;
	private Mensaje mensaje;
	private String nombre;
	private String descripcion;
	private Date fechaDeInicio;
	private Date fechaDeFin;
	
	private EstadoCampania estado;


	private UUID idCampania;

	public enum EstadoCampania {
		PLANIFICADA, PRELIMINAR, CANCELADA;
	}

	public Campania(Usuario usuario, String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		if (usuario.getTipoUsuario() != Usuario.TipoUsuario.CLIENTE)
			throw new IllegalArgumentException(usuario.toString() + " no es del tipo Cliente.");
		else
			this.usuario = usuario;
		estado = EstadoCampania.PLANIFICADA;
		this.mensaje = null;
		this.fechaDeInicio = null;
		this.fechaDeFin = null;
		this.tagsAsociados = null;
		idCampania = UUID.randomUUID();
	}

	public Campania(Usuario usuario, String nombre, String descripcion, List<AccionPublicitaria> accionesPublicitarias, List<Tag> tagsAsociados, Mensaje mensaje,
			Date fechaDeInicio, Date fechaDeFin) {
		if (usuario.getTipoUsuario() != Usuario.TipoUsuario.CLIENTE)
			throw new IllegalArgumentException(usuario.toString() + " no es del tipo Cliente. " + "Tipo recibido: "+ usuario.getTipoUsuario().name());
		else
			this.usuario = usuario;
		this.accionesPublicitarias = accionesPublicitarias;
		this.tagsAsociados = tagsAsociados;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.mensaje = mensaje;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFin = fechaDeFin;
		estado = EstadoCampania.PRELIMINAR;
		idCampania = UUID.randomUUID();
	}
	
	public void addAccionPublicitaria(AccionPublicitaria accion) {
		accionesPublicitarias.add(accion);
	}
	
	public void addTag(Tag tag){
		tagsAsociados.add(tag);
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<AccionPublicitaria> getAccionesPublicitarias() {
		return accionesPublicitarias;
	}

	public void setAccionesPublicitarias(List<AccionPublicitaria> accionesPublicitarias) {
		this.accionesPublicitarias = accionesPublicitarias;
	}

	public List<Tag> getTagsAsociados() {
		return tagsAsociados;
	}

	public void setTagsAsociados(List<Tag> tagsAsociados) {
		this.tagsAsociados = tagsAsociados;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public Date getFechaDeFin() {
		return fechaDeFin;
	}

	public void setFechaDeFin(Date fechaDeFin) {
		this.fechaDeFin = fechaDeFin;
	}

	public EstadoCampania getEstado() {
		return estado;
	}

	public void setEstado(EstadoCampania estado) {
		this.estado = estado;
	}	
	
	public UUID getIdCampania() {
		return idCampania;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCampania);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campania other = (Campania) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
