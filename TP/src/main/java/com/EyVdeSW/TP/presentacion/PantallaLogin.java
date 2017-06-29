package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PantallaLogin extends CustomComponent implements View,
    Button.ClickListener {

	public static final String NAME = "login";
	
	private final TextField userMail;	
	private final PasswordField password;	
	private final Button loginButton;	
	private final Button registroButton;
	
	private UsuarioService usuarioService = UsuarioService.getUsuarioService();
	
	public PantallaLogin() {
		
		//TituloPrincipal
		Label titulo = new Label("Ingreso al Sistema");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		
	
	    // Campo para el mail
	    userMail = new TextField("Mail:");
	    userMail.setWidth("300px");
	    userMail.setRequired(true);
	    userMail.setInputPrompt("Ej: joe@email.com)");
	    userMail.addValidator(new EmailValidator(
	            "El mail debe ser válido"));
	    userMail.setInvalidAllowed(false);
	
	    // Campo para el password
	    password = new PasswordField("Password:");
	    password.setWidth("300px");
	    password.addValidator(new PasswordValidator());
	    password.setRequired(true);
	    password.setValue("");
	    password.setNullRepresentation("");
	
	    // Create login button
	    loginButton = new Button("Ingresar", this);
	    registroButton = new Button("Ir a Registrarse", this);
	
	    FormLayout fields = new FormLayout(userMail, password);
	    fields.setCaption("Por favor, ingrese sus datos para acceder a la aplicación");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();
	    
	    HorizontalLayout botones = new HorizontalLayout(loginButton,registroButton);
	    botones.setSpacing(true);
	     
	       
	    // The view root layout
	    VerticalLayout viewLayout = new VerticalLayout(hlTitulo,fields, botones);
	    viewLayout.setSizeFull();
	    viewLayout.setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);
	    viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
	    viewLayout.setComponentAlignment(botones, Alignment.MIDDLE_CENTER);    
	    setCompositionRoot(viewLayout);
	    
	    registroButton.addClickListener(event -> 
		getUI().getNavigator().navigateTo(PantallaRegistro.NAME));
	    
	}

@Override
public void enter(ViewChangeEvent event) {
    // focus en userMail
	((MyUI) getUI()).hideMenu();
    userMail.focus();
}

// Validador para validar las contraseñas
private static final class PasswordValidator extends
        AbstractValidator<String> {

    public PasswordValidator() {
        super("La contraseña ingresada no es valida");
    }

    @Override
    protected boolean isValidValue(String value) {
        //
        // La contraseña debe tener 8 digitos como minimo y al menos un numero
        // 
        //
        if (value != null
                && (value.length() < 8 || !value.matches(".*\\d.*"))) {
            return false;
        }
        return true;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}

@Override
public void buttonClick(ClickEvent event) {

    // Validamos los campos usando el navigator. Usando validadores para los campos 
	// se reduce la cantidad de queries que tendriamos que usar con la base de datos
	// en caso de que se introduzca mal la contraseña
    //
    if (!userMail.isValid() || !password.isValid()) {
    	// Contraseña erronea, se limpia el campo de password y se reenfoca
    	this.password.setValue(null);
        this.password.focus();
        return;
    }

    String mail = userMail.getValue();
    String password = this.password.getValue();

    //
    // Validamos el mail y la contraseña con la base de datos acá
    //
    //
    
    boolean isValid = false;
    
    isValid = usuarioService.validacionUsuarioYContraseña(mail, password);
    
    if (mail=="" || password==""){
    	Notification.show("Hay campos vacios!", Type.WARNING_MESSAGE);
    }

    if (isValid) {

        // Se guarda el mail en la service session
        getSession().setAttribute("user", mail);

        // Navegar a la pantalla Main
        getUI().getNavigator().navigateTo(PantallaMainView.NAME);//

    } else {

        // Contraseña erronea, se limpia el campo de password y se reenfoca
    	Notification.show("Los datos ingresados no son validos!", Type.WARNING_MESSAGE);
		
        this.password.setValue(null);
        this.password.focus();

    }
}

}

