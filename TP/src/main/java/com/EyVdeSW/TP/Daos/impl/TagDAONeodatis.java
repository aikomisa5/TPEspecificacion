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

import com.EyVdeSW.TP.Daos.TagDAO;
import com.EyVdeSW.TP.domainModel.Tag;

public class TagDAONeodatis extends DAONeodatis<Tag> implements TagDAO {

	@Override
	public boolean existe(String nombre) {
		Objects<Tag> resultado = consultar(new CriteriaQuery(Tag.class, Where.equal("nombre", nombre)));
		return resultado.size() != 0;
	}

	@Override
	public Tag getTagPorNombre(String nombre) {
			return consultar(new CriteriaQuery(Tag.class, Where.equal("nombre", nombre))).getFirst();
	}

	@Override
	public void borrar(Tag tagRaiz) {

		Collection<Tag> todosLosTags = traerTodos();
		List<Tag> tagsABorrar = new ArrayList<>();
		Queue<Tag> colaDeTags = new LinkedList<>();
		colaDeTags.add(tagRaiz);
		while (!colaDeTags.isEmpty()) {
			todosLosTags.stream().filter(tag -> colaDeTags.peek().equals(tag.getPadre())).forEach(colaDeTags::add);
			tagsABorrar.add(colaDeTags.poll());
		}

		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			Set<OID> oids = new HashSet<>();
			tagsABorrar.forEach(tag -> oids.add(odb.getObjectId(tag)));
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
	public void modificar(String nombreOriginalTag, Tag modificacion) {
		odb = null;
		try {
			odb = bdConnector.getBDConnection();
			IQuery query = new CriteriaQuery(Tag.class, Where.equal("nombre", nombreOriginalTag));
			Objects<Tag> resultadoQuery = odb.getObjects(query);
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
	public Collection<Tag> traerTodos() {
		return consultar(new CriteriaQuery(Tag.class)).stream().collect(Collectors.toSet());
	}

	@Override
	public Collection<Tag> traerHijosDe(Tag padre) {
		return consultar(new CriteriaQuery(Tag.class, Where.equal("padre.nombre", padre.getNombre()))).stream()
				.collect(Collectors.toSet());
	}

	@Override
	public Collection<Tag> traerRaices() {
		return consultar(new CriteriaQuery(Tag.class, Where.isNull("padre"))).stream()
				.collect(Collectors.toSet());
	}

}
