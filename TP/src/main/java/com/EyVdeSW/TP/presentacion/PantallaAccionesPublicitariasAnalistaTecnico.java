package com.EyVdeSW.TP.presentacion;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class PantallaAccionesPublicitariasAnalistaTecnico extends VerticalLayout implements View {

	protected static final String NAME = "pantallaAccionesPublicitariasAnalistaTecnico";

	private TagTree tagTree = new TagTree();
	
	public PantallaAccionesPublicitariasAnalistaTecnico(){
	
		Label titulo = new Label("Creacion de Acciones Generales");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		
		TextField tfDestinatario = new TextField("Nombre destinatario");
		TextField tfTitulo = new TextField("Nombre titulo");
		TextArea taTexto = new TextArea("Texto");
		
	    Button cerrar = new Button("Cerrar");
	    Button agregar = new Button("Agregar");
		    
		    
		
	        
	       
	    tagTree.updateTree();
	      
	
	
	}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}


