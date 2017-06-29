package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.Orientation;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Slider;
import com.vaadin.ui.themes.ValoTheme;

public class SubMenuAccionesPublicitariasPersonalizadas extends Window {

	public SubMenuAccionesPublicitariasPersonalizadas(Tree accionesAgregadasHastaElMomento,
			List<AccionPublicitaria> accionesParaAsociar) {
		super("Asociar acciones publicitarias personalizadas"); // Set window
																// caption
		center();
		this.setModal(true);
		this.setDraggable(false);

		// Add it to the root component
		MyUI.getCurrent().addWindow(this);

		Button agregarAccion = new Button("Agregar acción");		
		agregarAccion.setIcon(FontAwesome.CHECK);
		agregarAccion.setStyleName(ValoTheme.BUTTON_PRIMARY);
		agregarAccion.setClickShortcut(KeyCode.ENTER);

		TextField tfDestinatario = new TextField("Destinatario");
		tfDestinatario.setInputPrompt("Ej: joe@email.com)");
		tfDestinatario.setRequired(true);
		tfDestinatario.addValidator(new EmailValidator("El mail debe ser válido"));
		tfDestinatario.setInvalidAllowed(false);
		
		TextField tfTitulo = new TextField("Encabezado");
		tfTitulo.setRequired(true);

		TextArea taTexto = new TextArea("Mensaje");
		taTexto.setRequired(true);
		
		VerticalLayout formulario = new VerticalLayout(tfDestinatario,tfTitulo,taTexto);
		formulario.setSpacing(true);

		Slider sliderPeriodicidad = crearSlider(1, 7, "Periodicidad", "dias");
		Slider sliderHora = crearSlider(0, 23, "Hora de inicio", "horas");
		Slider sliderMinuto = crearSlider(0, 59, "Minuto de inicio", "minutos");
		VerticalLayout sliders = new VerticalLayout(sliderPeriodicidad,sliderHora,sliderMinuto);
		
		HorizontalLayout subContent = new HorizontalLayout();
		subContent.setDefaultComponentAlignment(Alignment.TOP_CENTER);		
		VerticalLayout main = new VerticalLayout(subContent);
		VerticalLayout root = new VerticalLayout(main);
		main.setWidth(subContent.getWidth(),subContent.getWidthUnits());
		root.setWidth((subContent.getWidth()*1.1f),subContent.getWidthUnits());
		root.setComponentAlignment(main, Alignment.TOP_CENTER);
		this.setContent(root);

		// Agregamos componentes

		subContent.addComponent(formulario);
		subContent.addComponent(sliders);
		subContent.addComponent(agregarAccion);
		subContent.setMargin(true);
		subContent.setSpacing(true);
		subContent.setExpandRatio(formulario, 1.0f);
		this.setHeight("450px");
		this.setWidth("700px");		

		agregarAccion.addClickListener(event -> {

			if (tfDestinatario.getValue() == "") {
				Notification.show("El destinatario está vacío!", Type.WARNING_MESSAGE);
			}

			else if (tfTitulo.getValue() == "") {
				Notification.show("El titulo está vacío!", Type.WARNING_MESSAGE);
			}

			else if (taTexto.getValue().toString() == "") {
				Notification.show("El texto está vacío!", Type.WARNING_MESSAGE);
			}		

			else {

				String destinatario = tfDestinatario.getValue();
				String tituloAccion = tfTitulo.getValue();
				String texto = taTexto.getValue().toString();
				int periodicidad = sliderPeriodicidad.getValue().intValue();
				String horaInicio = Integer.toString(sliderHora.getValue().intValue());
				String minutoInicio = Integer.toString(sliderMinuto.getValue().intValue());

				AccionPublicitaria accion = new AccionPublicitaria(destinatario, tituloAccion, texto,
						TipoAccion.particular, periodicidad, horaInicio, minutoInicio);

				accionesParaAsociar.add(accion);
				accionesAgregadasHastaElMomento.addItem(accion);
				Notification.show("Accion guardada", Type.TRAY_NOTIFICATION);

				this.close();
			}

		});
	}

	private Slider crearSlider(int limiteInferior, int limiteSuperior, String descripcion, String unidad) {
		Slider slider= new Slider(limiteInferior, limiteSuperior);
		slider.setOrientation(SliderOrientation.HORIZONTAL);
		slider.setCaption(descripcion + " " + slider.getValue().intValue() + " " + unidad);
		slider.addValueChangeListener(e -> {
			slider.setCaption(descripcion + " " + slider.getValue().intValue() + " " + unidad);
		});
		return slider;
	}
}
