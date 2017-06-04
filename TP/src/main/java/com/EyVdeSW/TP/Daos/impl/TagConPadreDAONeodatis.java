package com.EyVdeSW.TP.Daos.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import com.EyVdeSW.TP.Daos.TagConPadreDAO;
import com.EyVdeSW.TP.domainModel.TagConPadre;

public class TagConPadreDAONeodatis extends DAONeodatis<TagConPadre> implements TagConPadreDAO {

	@Override
	public boolean existe(String nombre) {
		Objects<TagConPadre> resultado = consultar(new CriteriaQuery(TagConPadre.class, Where.like("nombre", nombre)));
		return resultado.size() != 0;
	}

	@Override
	public TagConPadre getTagPorNombre(String nombre) {
		return consultar(new CriteriaQuery(TagConPadre.class, Where.like("nombre", nombre))).getFirst();
	}

	@Override
	public void borrar(TagConPadre tag) {

		Collection<TagConPadre> todosLosTags = traerTodos();
		List<TagConPadre> tagsABorrar = new ArrayList<>();
		Queue<TagConPadre> colaDeTags = new LinkedList<>();
		colaDeTags.add(tag);
		while (!colaDeTags.isEmpty()) {
			todosLosTags.stream().filter(t -> colaDeTags.peek().equals(t.getPadre())).forEach(colaDeTags::add);
			tagsABorrar.add(colaDeTags.poll());
		}

		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			Set<OID> oids = new HashSet<>();
			tagsABorrar.forEach(t -> {
				oids.add(odb.getObjectId(t));
			});

			oids.forEach(odb::deleteObjectWithId);
			odb.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}
	}

	@Override
	public void modificar(String nombreOriginalTag, TagConPadre modificacion) {
		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(TagConPadre.class, Where.like("nombre", nombreOriginalTag));
			Objects<TagConPadre> resultadoQuery = odb.getObjects(query);
			resultadoQuery.forEach(t -> {
				t.setPadre(modificacion.getPadre());
				t.setAccionesGenerales(modificacion.getAccionesGenerales());
				t.setNombre(modificacion.getNombre());
				odb.store(t);
			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (odb != null)
				odb.close();
		}

	}

	@Override
	public Collection<TagConPadre> traerTodos() {
		return consultar(new CriteriaQuery(TagConPadre.class)).stream().collect(Collectors.toSet());
	}

	@Override
	public Collection<TagConPadre> traerHijosDe(TagConPadre padre) {
		return consultar(new CriteriaQuery(TagConPadre.class, Where.equal("padre.nombre", padre.getNombre()))).stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<TagConPadre> traerRaices() {
		return consultar(new CriteriaQuery(TagConPadre.class, Where.isNull("padre"))).stream()
				.collect(Collectors.toSet());
	}

}
