package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.presentacion.domainModel.Tag;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PantallaTags extends VerticalLayout implements View
{
	protected static final String Name = "";
	
	public PantallaTags(){
		TextField textFieldTag = new TextField("Nuevo Tag:");
		
		BeanItemContainer<Tag> grupos =new BeanItemContainer<Tag>(Tag.class);
	    ComboBox comboBoxTag = new ComboBox("Tag Padre:", grupos);
	    
	    Button btnAgregar = new Button("Agregar tag");
	    btnAgregar.setStyleName(ValoTheme.BUTTON_PRIMARY);
	    btnAgregar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	    
	    VerticalLayout vl = new VerticalLayout(textFieldTag, comboBoxTag, btnAgregar);
	    vl.setSpacing(true);
	    
	    addComponent(vl);	    
	}
	
	@Override
	public void enter(ViewChangeEvent event)
	{
		// TODO Auto-generated method stub

	}

}
