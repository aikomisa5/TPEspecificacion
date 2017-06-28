package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.AccionPublicitaria;
import com.EyVdeSW.TP.domainModel.AccionPublicitaria.TipoAccion;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;

public class SubMenuAccionesPublicitariasPersonalizadas extends Window {
	
	    public SubMenuAccionesPublicitariasPersonalizadas(Tree accionesAgregadasHastaElMomento, List<AccionPublicitaria> accionesParaAsociar) {
	        super("Asociar acciones publicitarias personalizadas"); // Set window caption
	        center();

	        // Disable the close button
	        setClosable(false);
	        

			// Add it to the root component
			MyUI.getCurrent().addWindow(this);

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
			setContent(subContent);

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
			setHeight("400px");
			setWidth("500px");
			

			cerrar.addClickListener(event -> this.close());

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

					accionesParaAsociar.add(accion);
					accionesAgregadasHastaElMomento.addItem(accion);
					Notification.show("Accion guardada", Type.TRAY_NOTIFICATION);

					// SE BORRA UNA VEZ CREADO LA CAMPAÑA
					// accionesPublicitarias.clear();

					this.close();
				}

			});
		}
    }
	
