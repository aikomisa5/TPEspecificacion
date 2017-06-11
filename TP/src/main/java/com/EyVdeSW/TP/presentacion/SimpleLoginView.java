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
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

public class SimpleLoginView extends CustomComponent implements View,
    Button.ClickListener {

public static final String NAME = "login";

private final TextField user;

private final PasswordField password;

private final Button loginButton;

private final Button registroButton;

private UsuarioService usuarioService = UsuarioService.getUsuarioService();

public SimpleLoginView() {
	
	//TituloPrincipal
	Label titulo = new Label("Ingreso al Sistema");
	titulo.setStyleName(ValoTheme.LABEL_H1);
	HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
	

    // Field para el mail
    user = new TextField("Mail:");
    user.setWidth("300px");
    user.setRequired(true);
    user.setInputPrompt("Ej: joe@email.com)");
    user.addValidator(new EmailValidator(
            "El mail debe ser válido"));
    user.setInvalidAllowed(false);

    // Field para el password
    password = new PasswordField("Password:");
    password.setWidth("300px");
    password.addValidator(new PasswordValidator());
    password.setRequired(true);
    password.setValue("");
    password.setNullRepresentation("");

    // Create login button
    loginButton = new Button("Ingresar", this);
    registroButton = new Button("Ir a Registrarse", this);

    // Add both to a panel
    /*
    VerticalLayout fields = new VerticalLayout(user, password);
    fields.setCaption("Please login to access the application. (test@test.com/passw0rd)");
    fields.setSpacing(true);
    fields.setMargin(new MarginInfo(true, true, true, false));
    fields.setSizeUndefined();
    */
    
    FormLayout fields = new FormLayout(user, password);
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
    viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
    setCompositionRoot(viewLayout);
    
    registroButton.addClickListener(event -> // Java 8
	getUI().getNavigator().navigateTo(MyUI.REGISTROVIEW));
    
}

@Override
public void enter(ViewChangeEvent event) {
    // focus the username field when user arrives to the login view
    user.focus();
}

// Validator for validating the passwords
private static final class PasswordValidator extends
        AbstractValidator<String> {

    public PasswordValidator() {
        super("The password provided is not valid");
    }

    @Override
    protected boolean isValidValue(String value) {
        //
        // Password must be at least 8 characters long and contain at least
        // one number
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

    //
    // Validate the fields using the navigator. By using validors for the
    // fields we reduce the amount of queries we have to use to the database
    // for wrongly entered passwords
    //
    if (!user.isValid() || !password.isValid()) {
        return;
    }

    String username = user.getValue();
    String password = this.password.getValue();

    //
    // Validate username and password with database here. For examples sake
    // I use a dummy username and password.
    //
    
    boolean isValid = false;
    Usuario usuario = null;
    
    if (usuarioService.existeUsuarioPorMail(username)){
    	 usuario = usuarioService.getUsuarioPorMail(username);
    	if (usuario.getPassword().equals(password)){
    			isValid=true;
    		}
    	}
   /* boolean isValid = username.equals(usuarioService.existeUsuarioPorMail(username))
            && password.equals("passw0rd");
    */
   // boolean isValid = username.equals("test@test.com")
     //       && password.equals("passw0rd");

    if (isValid) {

        // Store the current user in the service session
        getSession().setAttribute("user", username);

        // Navigate to main view
        getUI().getNavigator().navigateTo(SimpleLoginMainView.NAME);//

    } else {

        // Wrong password clear the password field and refocuses it
        this.password.setValue(null);
        this.password.focus();

    }
}

}

