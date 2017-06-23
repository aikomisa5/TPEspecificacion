package com.EyVdeSW.TP.presentacion;

	import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.Campania;
import com.EyVdeSW.TP.domainModel.Duracion;
import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.services.CampañaService;
import com.EyVdeSW.TP.services.DuracionService;
import com.EyVdeSW.TP.services.TagService;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.data.util.BeanItemContainer;
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
		
		String username = "";
		Date fechaInicio = new Date();
		
		public PantallaCampañaCliente() {
			
			
			
			List<Tag> tagsParaAsociar = null;
			
			Label titulo = new Label("Gestión de Campañas");
			titulo.setStyleName(ValoTheme.LABEL_H1);
			HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
			addComponent(hlTitulo);
			setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

			
			TextField tfNombre = new TextField("Nombre Campaña");
			TextArea taDescripcion = new TextArea("Descripción Campaña");
			TextField tfNombreMensaje = new TextField("Nombre Mensaje");
			TextArea taTextoMensaje = new TextArea("Texto del Mensaje");
			
			BeanItemContainer<Duracion> duraciones = new BeanItemContainer<Duracion>(Duracion.class);
			duraciones.addAll(duracionService.traerDuraciones());
			//TODO crear una clase duracion de campañas.
			ComboBox duracionCampaña = new ComboBox("Duración de Campaña");
			// Add some items (the given ID is used as item caption)
			duracionCampaña.addItem("Una semana");
			duracionCampaña.addItem("Un mes");
			duracionCampaña.addItem("Un bimestre");
			duracionCampaña.addItem("Un semestre");
			// User may not select a "null" item
			duracionCampaña.setNullSelectionAllowed(false);
			

			//Calendario
			
			// Create a DateField with the default style
			DateField datePickerInicio = new DateField();
			datePickerInicio.setCaption("Fecha de Inicio");

			// Set the date to present
			datePickerInicio.setValue(new Date());
			
			datePickerInicio.setDescription("Calendario para elegir la fecha de inicio de la campaña");
						
			fechaInicio=datePickerInicio.getValue();
					
			System.out.print(fechaInicio.toString());
			
			
			//Para el comboBox, al seleccionar tags para asociar a la campaña
			BeanItemContainer<Tag> tags = new BeanItemContainer<Tag>(Tag.class);
			tagService.traerTodos().forEach(tag -> tags.addBean(tag));
			
			ComboBox comboBoxTag = new ComboBox("Tags", tags);
			
			Button btnAsociarTags = new Button("Asociar Tags a la Campaña");
			//TODO sub-menu
			
			Button btnAsociarAcciones = new Button("Asociar Acciones a la Campaña");
			
			btnAsociarTags.addClickListener(e -> {
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
		        
		      //  subContent.addComponent(comboBoxTag);
		        subContent.addComponent(cerrar);
		        subContent.addComponent(agregar);
		  			    sub.setHeight("400px");
			    sub.setWidth("500px");
			   
		        cerrar.addClickListener(event -> sub.close());
		        
		        agregar.addClickListener(event -> {
		   
		        	String nombreTag = tagTree.getValue().toString();
		        	
		        	Tag tag = tagService.getTagPorNombre(nombreTag);
		        	
		        	Collection <Tag> hijos =  tagService.traerHijosDe(tag);
		        	
		        	//Me tira error, revisar
		        	
		        	boolean estado = verificadorTagYaAsociado(nombreTag,tagsParaAsociar);
		        	
		        	if (estado){
		        		Notification.show("El tag ya se encuentra asociado!", Type.WARNING_MESSAGE);
		           	}
		        	
		        	else {
		        		tagsParaAsociar.add(tag);
		        		for (Tag t : hijos){
		        			tagsParaAsociar.add(t);
		        		}
		        	}
		        	
		        	//XXX No puedo hacer eso porque todavia no existe esa campaña
		        	//en la base de datos
		        	//una opcion es agregar todos los tags asociados
		        	//al momento de crear la campaña
		        	
		        	//campañaService.getNombreCampañaSarasa();
		        	
		        	
		        	//esto va en la parte cuando se crea la campaña
		        	//campañaService.agregarTags(nombreCampaña, tags);
		        	
		        	
		        	System.out.println(tagTree.getValue().toString());
		        
		        });
		        
		        

			});
			

			btnAsociarAcciones.addClickListener(e -> {
				SubMenuAccionesPublicitariasPersonalizadas sub = new SubMenuAccionesPublicitariasPersonalizadas();

			    // Add it to the root component
			    MyUI.getCurrent().addWindow(sub);
			    
			    Button cerrar = new Button("Cerrar");
			    Button agregar = new Button("Agregar");
			    
				TextField tfDestinatario = new TextField("Nombre destinatario");
				TextField tfTitulo = new TextField("Nombre titulo");
				TextArea taTexto = new TextArea("Texto");
				
			
			    VerticalLayout subContent = new VerticalLayout();
		        sub.setContent(subContent);
		        
		       
		        // Put some components in it
		    
		        
		      //  subContent.addComponent(comboBoxTag);
		        subContent.addComponent(tfDestinatario);
		        subContent.addComponent(tfTitulo);
		        subContent.addComponent(taTexto);
		        
		        subContent.addComponent(cerrar);
		        subContent.addComponent(agregar);
		  			    sub.setHeight("400px");
			    sub.setWidth("500px");
			   
		        cerrar.addClickListener(event -> sub.close());
		        
				
			
				    
				
				
			});
			
			
			
			
			
			Button btnCrear = new Button("Crear Campaña");

			btnCrear.setStyleName(ValoTheme.BUTTON_PRIMARY);
			btnCrear.setClickShortcut(ShortcutAction.KeyCode.ENTER);
			btnCrear.addClickListener(e -> {
				if (tfNombre.getValue() == "") {
					Notification.show("El nombre está vacío!", Type.WARNING_MESSAGE);
				} 
				
				else if (taDescripcion.getData().toString() == "") {
					Notification.show("La descripción está vacía!", Type.WARNING_MESSAGE);
				}
				
				else if (tfNombreMensaje.getValue() == ""){
					Notification.show("El nombre del mensaje está vacío!", Type.WARNING_MESSAGE);
				}
				
				else if (taTextoMensaje.getData().toString() == ""){
					Notification.show("El texto del mensaje está vacío!", Type.WARNING_MESSAGE);
				}
				else {
					//XXX fix me please
					Usuario usuario = usuarioService.getUsuarioPorMail(username);
					String nombre = tfNombre.getValue();
					String descripcion = taDescripcion.getData().toString();
					String tituloMensaje = tfNombreMensaje.getValue();
					String cuerpoMensaje = taTextoMensaje.getData().toString();
					
					campañaService.guardar(usuario, nombre , descripcion, null, tagsParaAsociar , tituloMensaje,
							cuerpoMensaje , fechaInicio, duracionService.getDuracionPorDescripcion(duracionCampaña.getValue().toString()));
					
//					campañaService.guardar(null,tfNombre.getValue(),taDescripcion.getData().toString(),null,null,
//							tfNombreMensaje.getValue(),taTextoMensaje.getData().toString(),fechaInicio, null);
					
					Notification.show("Campaña Guardado", Type.TRAY_NOTIFICATION);
					limpiarCampos(tfNombre, taDescripcion, tfNombreMensaje, taTextoMensaje);
				}
				tfNombre.focus();
			});

		
		
			HorizontalLayout hlBotones = new HorizontalLayout(btnCrear, btnAsociarTags);
			hlBotones.setSpacing(true);

			FormLayout flFormCampos = new FormLayout(tfNombre,taDescripcion,tfNombreMensaje,taTextoMensaje,duracionCampaña,datePickerInicio);
			flFormCampos.setSpacing(true);
			
			VerticalLayout vlFormTags = new VerticalLayout(flFormCampos, hlBotones);
			vlFormTags.setSpacing(true);
		
			HorizontalLayout hlPrincipal = new HorizontalLayout(vlFormTags);
			hlPrincipal.setSpacing(true);
			hlPrincipal.setWidth("80%");
			addComponent(hlPrincipal);
			setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
			setMargin(true);		
				
		}


	/*
		private void limpiarCampos(TextField textFieldTag, BeanItemContainer<Tag> tags, ComboBox comboBoxTag) {
			textFieldTag.clear();
			comboBoxTag.removeAllItems();
			System.out.println("Cantidad de elementos: " + tagService.traerTodos().size());
			tagService.traerTodos().forEach(tag -> tags.addBean(tag));
			comboBoxTag.setContainerDataSource(tags);
		} */
		
		public boolean verificadorTagYaAsociado(String nombreTag, List<Tag>tags){
			boolean estado = false;
			
			for (Tag t : tags){
				estado = t.getNombre().equals(nombreTag);
			}
			
			return estado;
		}

		private void limpiarCampos(TextField textFieldNombre, TextArea textAreaDescripcion, 
				TextField textFieldNombreMensaje, TextArea textAreaTextoMensaje) {
			textFieldNombre.clear();
			textAreaDescripcion.clear();
			textFieldNombreMensaje.clear();
			textAreaTextoMensaje.clear();
		}
		
		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub
			String usernameMail = String.valueOf(getSession().getAttribute("user"));
			username=usernameMail;
		}

		
		
	
}
