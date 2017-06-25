package com.EyVdeSW.TP.presentacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.services.CampañaService;
import com.EyVdeSW.TP.services.DuracionService;
import com.EyVdeSW.TP.services.TagService;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaCampañaCliente extends VerticalLayout implements View {

	protected static final String NAME = "pantallaCampañasCliente";

	private CampañaService campañaService = CampañaService.getCampañaService();
	private DuracionService duracionService = DuracionService.getDuracionService();
	private TagService tagService = TagService.getTagService();
	private UsuarioService usuarioService = UsuarioService.getUsuarioService();
	private TagTree tagTree = new TagTree();
	HierarchicalContainer tagContainer = new HierarchicalContainer();

	private List<AccionPublicitaria> accionesPublicitarias = new ArrayList<>();
	List<Tag> tagsParaAsociar = new ArrayList<>();
	boolean estado = false;

	String username = "";
	Date fechaInicio = new Date();

	String idCampaña;
	Campania campaña;

	TextField tfNombre;
	TextArea taDescripcion;
	TextField tfNombreMensaje;
	TextArea taTextoMensaje;
	ComboBox duracionCampaña;
	DateField datePickerInicio;
	BeanItemContainer<Duracion> duraciones;

	public PantallaCampañaCliente() {

		Tree accionesAgregadasHastaElMomento = new Tree("Acciones Agregadas Hasta El Momento");

		Tree tagsAgregadosHastaElMomento = new Tree("Tags Agregados Hasta El Momento");

		Label titulo = new Label("Gestión de Campañas");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		tfNombre = new TextField("Nombre Campaña");
		taDescripcion = new TextArea("Descripción Campaña");
		tfNombreMensaje = new TextField("Nombre Mensaje");
		taTextoMensaje = new TextArea("Texto del Mensaje");

		duraciones = new BeanItemContainer<Duracion>(Duracion.class);
		duraciones.addAll(duracionService.traerDuraciones());
		duracionCampaña = new ComboBox("Duración de Campaña", duraciones);
		// User may not select a "null" item
		duracionCampaña.setNullSelectionAllowed(false);

		// Calendario

		// Create a DateField with the default style
		datePickerInicio = new DateField();
		datePickerInicio.setCaption("Fecha de Inicio");

		// Set the date to present
		datePickerInicio.setValue(new Date());

		datePickerInicio.setDescription("Calendario para elegir la fecha de inicio de la campaña");

		fechaInicio = datePickerInicio.getValue();

		// Para el comboBox, al seleccionar tags para asociar a la campaña
		BeanItemContainer<Tag> tags = new BeanItemContainer<Tag>(Tag.class);
		tagService.traerTodos().forEach(tag -> tags.addBean(tag));

		ComboBox comboBoxTag = new ComboBox("Tags", tags);

		// Asociar tags a la campaña
		Button btnAsociarTags = new Button("Asociar Tags a la Campaña");
		btnAsociarTags.addClickListener(e -> {
			abrirAsociarTags(tagsAgregadosHastaElMomento);
		});

		// Asociar acciones a la campaña
		Button btnAsociarAcciones = new Button("Asociar Acciones a la Campaña");
		btnAsociarAcciones.addClickListener(e -> {
			abrirAsociarAcciones(accionesAgregadasHastaElMomento);

		});

		Button btnCrear = new Button("Crear Campaña");

		btnCrear.setStyleName(ValoTheme.BUTTON_PRIMARY);
		btnCrear.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		btnCrear.addClickListener(e -> {
			guardarCampaña(accionesAgregadasHastaElMomento, tagsAgregadosHastaElMomento, datePickerInicio);
		});

		HorizontalLayout hlBotones = new HorizontalLayout(btnCrear, btnAsociarTags, btnAsociarAcciones);
		hlBotones.setSpacing(true);

		FormLayout flFormCampos = new FormLayout(tfNombre, taDescripcion, tfNombreMensaje, taTextoMensaje,
				duracionCampaña, datePickerInicio);
		flFormCampos.setSpacing(true);

		VerticalLayout vlFormTags = new VerticalLayout(flFormCampos, hlBotones);
		vlFormTags.setSpacing(true);

		FormLayout flFormArboles = new FormLayout(accionesAgregadasHastaElMomento, tagsAgregadosHastaElMomento);
		flFormArboles.setSpacing(true);

		VerticalLayout vlFormArboles = new VerticalLayout(flFormArboles);
		vlFormArboles.setSpacing(true);

		HorizontalLayout hlPrincipal = new HorizontalLayout(vlFormTags, vlFormArboles);
		hlPrincipal.setSpacing(true);
		hlPrincipal.setWidth("80%");
		addComponent(hlPrincipal);
		setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
		setMargin(true);

	}

	private void guardarCampaña(Tree accionesAgregadasHastaElMomento, Tree tagsAgregadosHastaElMomento,
			DateField datePickerInicio) {
		if (tfNombre.getValue() == "") {
			Notification.show("El nombre está vacío!", Type.WARNING_MESSAGE);
		}

		else if (taDescripcion.getValue().toString() == "") {
			Notification.show("La descripción está vacía!", Type.WARNING_MESSAGE);
		}

		else if (tfNombreMensaje.getValue() == "") {
			Notification.show("El nombre del mensaje está vacío!", Type.WARNING_MESSAGE);
		}

		else if (taTextoMensaje.getValue().toString() == "") {
			Notification.show("El texto del mensaje está vacío!", Type.WARNING_MESSAGE);
		} else {

			Usuario usuario = usuarioService.getUsuarioPorMail(username);
			String nombre = tfNombre.getValue();
			String descripcion = taDescripcion.getValue().toString();
			String tituloMensaje = tfNombreMensaje.getValue();
			String cuerpoMensaje = taTextoMensaje.getValue().toString();
			fechaInicio = datePickerInicio.getValue();
			Duracion duracion = duracionService.getDuracionPorDescripcion(duracionCampaña.getValue().toString());
			campañaService.guardar(usuario, nombre, descripcion, accionesPublicitarias, tagsParaAsociar, tituloMensaje,
					cuerpoMensaje, fechaInicio, duracion);

			Notification.show("Campaña Guardado", Type.TRAY_NOTIFICATION);
			limpiarCampos(tfNombre, taDescripcion, tfNombreMensaje, taTextoMensaje);
			limpiarListas(tagsParaAsociar, accionesPublicitarias, accionesAgregadasHastaElMomento,
					tagsAgregadosHastaElMomento);

		}
		tfNombre.focus();
	}

	private void abrirAsociarAcciones(Tree accionesAgregadasHastaElMomento) {
		SubMenuAccionesPublicitariasPersonalizadas sub = new SubMenuAccionesPublicitariasPersonalizadas();

		// Add it to the root component
		MyUI.getCurrent().addWindow(sub);

		Button cerrar = new Button("Cerrar");
		Button asociar = new Button("Agregar");

		TextField tfDestinatario = new TextField("Nombre destinatario");
		TextField tfTitulo = new TextField("Nombre titulo");
		TextArea taTexto = new TextArea("Texto");

		ComboBox comboBoxPeriodicidad = new ComboBox("Periodicidad");
		for (int i = 1; i < 8; i++) {
			comboBoxPeriodicidad.addItem(i);
		}
		comboBoxPeriodicidad.setNullSelectionAllowed(false);

		ComboBox hora = new ComboBox("Hora de inicio");
		for (int i = 0; i < 24; i++) {
			hora.addItem(i);
		}
		hora.setNullSelectionAllowed(false);

		ComboBox minuto = new ComboBox("Minuto de inicio");
		for (int i = 0; i < 60; i++) {
			minuto.addItem(i);
		}
		minuto.setNullSelectionAllowed(false);

		VerticalLayout subContent = new VerticalLayout();
		sub.setContent(subContent);

		// Put some components in it

		// subContent.addComponent(comboBoxTag);
		subContent.addComponent(tfDestinatario);
		subContent.addComponent(tfTitulo);
		subContent.addComponent(taTexto);

		subContent.addComponent(comboBoxPeriodicidad);
		subContent.addComponent(hora);
		subContent.addComponent(minuto);

		subContent.addComponent(cerrar);
		subContent.addComponent(asociar);
		sub.setHeight("400px");
		sub.setWidth("500px");
		setMargin(true);

		cerrar.addClickListener(event -> sub.close());

		asociar.addClickListener(event -> {

			if (tfDestinatario.getValue() == "") {
				Notification.show("El destinatario está vacío!", Type.WARNING_MESSAGE);
			}

			else if (tfTitulo.getValue() == "") {
				Notification.show("El titulo está vacío!", Type.WARNING_MESSAGE);
			}

			else if (taTexto.getValue().toString() == "") {
				Notification.show("El texto está vacío!", Type.WARNING_MESSAGE);
			}

			else if (comboBoxPeriodicidad.getValue() == null) {
				Notification.show("La periodicidad está vacía!", Type.WARNING_MESSAGE);
			} else if (hora.getValue() == null) {
				Notification.show("La hora está vacía!", Type.WARNING_MESSAGE);
			} else if (minuto.getValue() == null) {
				Notification.show("Los minutos estan vacíos!", Type.WARNING_MESSAGE);
			}
			// TODO verificar si la accion ya existe
			else {

				String destinatario = tfDestinatario.getValue();
				String tituloAccion = tfTitulo.getValue();
				String texto = taTexto.getValue().toString();
				int periodicidad = (int) comboBoxPeriodicidad.getValue();
				String horaInicio = hora.getValue().toString();
				String minutoInicio = minuto.getValue().toString();

				AccionPublicitaria accion = new AccionPublicitaria(destinatario, tituloAccion, texto,
						TipoAccion.particular, periodicidad, horaInicio, minutoInicio);

				accionesPublicitarias.add(accion);
				accionesAgregadasHastaElMomento.addItem(accion.getTitulo());
				Notification.show("Accion guardada", Type.TRAY_NOTIFICATION);

				// SE BORRA UNA VEZ CREADO LA CAMPAÑA
				// accionesPublicitarias.clear();

				sub.close();
			}

		});
	}

	private void abrirAsociarTags(Tree tagsAgregadosHastaElMomento) {
		SubMenuTagsAsociadosCampaña sub = new SubMenuTagsAsociadosCampaña();

		// Add it to the root component
		MyUI.getCurrent().addWindow(sub);

		Button cerrar = new Button("Cerrar");
		Button agregar = new Button("Agregar");

		VerticalLayout subContent = new VerticalLayout();
		sub.setContent(subContent);

		tagTree.updateTree();
		// Put some components in it
		subContent.addComponent(tagTree);

		// subContent.addComponent(comboBoxTag);
		subContent.addComponent(cerrar);
		subContent.addComponent(agregar);
		sub.setHeight("400px");
		sub.setWidth("500px");
		setMargin(true);

		cerrar.addClickListener(event -> sub.close());

		agregar.addClickListener(event -> {

			if (tagTree.getValue() == null) {
				Notification.show("No has seleccionado ningun tag!", Type.WARNING_MESSAGE);
			}

			else if (tagYaAsociado(tagsAgregadosHastaElMomento, tagTree.getValue().toString())) {
				Notification.show("Ya asociaste ese tag!", Type.WARNING_MESSAGE);
				estado = false;
			} else {

				String nombreTag = tagTree.getValue().toString();

				Tag tag = tagService.getTagPorNombre(nombreTag);

				updateTree(tagsAgregadosHastaElMomento, tag);

				// En este punto ya se sabe que el tag que se quiere asociar
				// todavia no esta asociado

				List<Tag> hijos = (List<Tag>) tagService.traerHijosDe(tag);

				tagsParaAsociar.add(tag);
				for (Tag t : hijos) {
					tagsParaAsociar.add(t);
				}

				Notification.show("Tags guardados", Type.TRAY_NOTIFICATION);
				sub.close();

			}

		});
	}

	/*
	 * private void limpiarCampos(TextField textFieldTag, BeanItemContainer<Tag>
	 * tags, ComboBox comboBoxTag) { textFieldTag.clear();
	 * comboBoxTag.removeAllItems();
	 * System.out.println("Cantidad de elementos: " +
	 * tagService.traerTodos().size()); tagService.traerTodos().forEach(tag ->
	 * tags.addBean(tag)); comboBoxTag.setContainerDataSource(tags); }
	 */

	private void limpiarCampos(TextField textFieldNombre, TextArea textAreaDescripcion,
			TextField textFieldNombreMensaje, TextArea textAreaTextoMensaje) {
		textFieldNombre.clear();
		textAreaDescripcion.clear();
		textFieldNombreMensaje.clear();
		textAreaTextoMensaje.clear();
	}

	private void limpiarListas(List<Tag> tags, List<AccionPublicitaria> acciones, Tree arbolTags, Tree arbolAcciones) {
		tags.clear();
		acciones.clear();
		arbolTags.removeAllItems();
		arbolAcciones.removeAllItems();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		campaña = null;
		String usernameMail = String.valueOf(getSession().getAttribute("user"));
		username = usernameMail;
		String args[] = event.getParameters().split("/");
		try {
			idCampaña = args[0];
			campaña = campañaService.getCampañaPorId(UUID.fromString(idCampaña));
			if (campaña != null)
			cargarDatosEnFormulario();
		} catch (IllegalArgumentException e) {}
	}

	private void cargarDatosEnFormulario() {
		tfNombre.setValue(campaña.getNombre());
		taDescripcion.setValue(campaña.getDescripcion());
		tfNombreMensaje.setValue(campaña.getMensaje().getNombre());
		taTextoMensaje.setValue(campaña.getMensaje().getTextoMensaje());		
		Duracion d = duracionService.getDuracionPorCantidadDeDias(campaña.getDuracion());		
		duracionCampaña.select(d);		
		datePickerInicio.setValue(campaña.getFechaDeInicio());		
		System.out.println("Duracion de campaña: "+ campaña.getDuracion() + " dias.");
	}
	

	private boolean tagYaAsociado(Tree arbol, String tagSelect) {

		arbol.getContainerDataSource().getItemIds().forEach(item -> {
			Tag tag = ((Tag) item);
			estado = estado || tagSelect.equals(tag.getNombre());

		});

		return estado;
	}

	private void updateTree(Tree arbol, Tag tagSeleccionado) {
		cargarTree(arbol, tagSeleccionado);
		expandirArbol(arbol);
	}

	private void expandirArbol(Tree arbol) {
		arbol.getItemIds().forEach(item -> arbol.expandItem(item));
	}

	private void cargarTree(Tree arbol, Tag tagSeleccionado) {

		tagContainer.addItem(tagSeleccionado);
		tagService.traerHijosDe(tagSeleccionado).forEach(tag -> {
			tagContainer.addItem(tag);
			tagContainer.setChildrenAllowed(tag, false);
		});

		/*
		 * tagService.traerTodos().forEach(tag -> { tagContainer.addItem(tag);
		 * tagContainer.setChildrenAllowed(tag, false); });
		 */

		tagContainer.getItemIds().forEach(item -> {
			Tag tagPadre = ((Tag) item).getPadre();
			tagContainer.setChildrenAllowed(tagPadre, true);
			tagContainer.setParent(item, tagPadre);
		});

		arbol.setContainerDataSource(tagContainer);
	}

}
