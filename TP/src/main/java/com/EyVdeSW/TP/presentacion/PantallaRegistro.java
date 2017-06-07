package com.EyVdeSW.TP.presentacion;

import com.EyVdeSW.TP.domainModel.Usuario;
import com.EyVdeSW.TP.domainModel.Usuario.TipoUsuario;
import com.EyVdeSW.TP.services.UsuarioService;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

public class PantallaRegistro extends VerticalLayout implements View {
	
	protected static final String NAME = "";
	
	private UsuarioService usuarioService = UsuarioService.getUsuarioService();
	
	private final TextField tfNombreUsuario;
	private final TextField tfNombreReal;
	private final TextField tfMail;

	private final PasswordField password;
	private final PasswordField verificacionPassword;

	private final Button logout; 
	private final Button registroButton;

	
	public PantallaRegistro(){
				
		//TituloPrincipal
		Label titulo = new Label("Registro en el Sistema");
		titulo.setStyleName(ValoTheme.LABEL_H1);
		HorizontalLayout hlTitulo = new HorizontalLayout(titulo);
		addComponent(hlTitulo);
		setComponentAlignment(hlTitulo, Alignment.MIDDLE_CENTER);

		//NombreUsuario
		tfNombreUsuario = new TextField("Nombre Usuario");
		tfNombreUsuario.setWidth("300px");
		tfNombreUsuario.setRequired(true);
		
		//NombreReal
		tfNombreReal = new TextField("Nombre Real");
		tfNombreReal.setWidth("300px");
		tfNombreReal.setRequired(true);
		
		//Mail
		tfMail = new TextField("Mail");
		tfMail.setWidth("300px");
		tfMail.setRequired(true);
		tfMail.setInputPrompt("Your username (eg. joe@email.com)");
		tfMail.addValidator(new EmailValidator(
	            "Username must be an email address"));
		tfMail.setInvalidAllowed(false);
	    
		//Password
		password = new PasswordField("Password");
		password.setWidth("300px");
	    password.addValidator(new PasswordValidator());
	    password.setRequired(true);
	    password.setValue("");
	    password.setNullRepresentation("");
	    
	    //VerificacionPassword
		verificacionPassword = new PasswordField("Repita la Password");
		verificacionPassword.setWidth("300px");
		verificacionPassword.addValidator(new PasswordValidator());
		verificacionPassword.setRequired(true);
		verificacionPassword.setValue("");
		verificacionPassword.setNullRepresentation("");
		
		//Botones
		registroButton = new Button("Registrarse");
		logout	= new Button("Volver a Login");
			
		 
		 
		 FormLayout fields = new FormLayout(tfNombreUsuario, tfNombreReal, tfMail, password, verificacionPassword, registroButton);
		    fields.setCaption("Por favor registrese para acceder a la aplicacion. (test@test.com/passw0rd)");
		    //fields.setSpacing(true);
		    //fields.setMargin(new MarginInfo(true, true, true, false));
		    //fields.setSizeUndefined();
		    fields.setSpacing(true);
		    
		 HorizontalLayout botones = new HorizontalLayout(registroButton, logout);
		 	botones.setSpacing(true);
		    
		 VerticalLayout viewLayout = new VerticalLayout(fields,botones);
		 	viewLayout.setSpacing(true);
		  //  viewLayout.setSizeFull();
		   // viewLayout.setComponentAlignment(fields, Alignment.TOP_CENTER);
		   // viewLayout.setComponentAlignment(hlTitulo, Alignment.TOP_CENTER);
			//viewLayout.setComponentAlignment(botones, Alignment.BOTTOM_CENTER);
			
		    
		    viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		   // setCompositionRoot(viewLayout);
			
		 HorizontalLayout hlPrincipal = new HorizontalLayout(viewLayout);
			hlPrincipal.setSpacing(true);
			hlPrincipal.setWidth("80%");
			addComponent(hlPrincipal);
			setComponentAlignment(hlPrincipal, Alignment.TOP_CENTER);
			setMargin(true);
			
		logout.addClickListener(e -> {
			getUI().getNavigator().navigateTo(SimpleLoginView.NAME);
		
		});
		
		registroButton.addClickListener(e -> {
			
			/* if(usuarioService.existeUsuario(tfNombreUsuario.getValue())){
				Notification.show("El nombre de usuario ya existe!", Type.WARNING_MESSAGE);
			}
			
			if(tfNombreUsuario.getValue() == ""){
				Notification.show("El nombre de usuario esta vacío!", Type.WARNING_MESSAGE);
			}
			
			if (tfNombreReal.getValue() == ""){
				Notification.show("El nombre real esta vacío!", Type.WARNING_MESSAGE);
			}
			
			if (tfMail.getValue() == ""){
				Notification.show("El mail esta vacío!", Type.WARNING_MESSAGE);
			}
			
			if (password.getValue() == ""){
				Notification.show("El password esta vacío!", Type.WARNING_MESSAGE);
			} */
			
			if(tfNombreUsuario.getValue() == "" || tfNombreReal.getValue() == ""
			|| tfMail.getValue() == "" || password.getValue() == "") {
				Notification.show("Uno de los campos esta vacío o es incorrecto!", Type.WARNING_MESSAGE);
			}
			
			
			
			/*if (password.equals(verificacionPassword)==false){
				Notification.show("Las password no coinciden", Type.WARNING_MESSAGE);
			}*/
			
			else {
				if(usuarioService.existeUsuario(tfNombreUsuario.getValue())){
					Notification.show("Ya existe ese nombre de usuario!", Type.WARNING_MESSAGE);
				}
				
				else{
				String nombreUsuario = tfNombreUsuario.getValue();
				String nombreReal = tfNombreReal.getValue();
				String mail = tfMail.getValue();
				String Password = password.getValue();
				
				Usuario usuario = new Usuario(nombreUsuario,nombreReal,mail,Password,TipoUsuario.CLIENTE);
				usuarioService.guardar(usuario);
				Notification.show("Usuario Guardado", Type.TRAY_NOTIFICATION);
				limpiarCampos(tfNombreUsuario, tfNombreReal, tfMail, password, verificacionPassword);
				}
			}
		});
		    
	}

	private void limpiarCampos(TextField tfNombreUsuario, TextField tfNombreReal, TextField tfMail,
			PasswordField password, PasswordField verificacionPassword) {
		
		tfNombreUsuario.clear();
		tfNombreReal.clear();
		tfMail.clear();
		password.clear();
		verificacionPassword.clear();	
	}

	@Override
	public void enter(ViewChangeEvent event) {
		tfNombreUsuario.focus();
		
		
	}
	
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
		//ACA HABIA UN OVERRIDE
	public void buttonClick(ClickEvent event) {
	
	//
	// Validate the fields using the navigator. By using validors for the
	// fields we reduce the amount of queries we have to use to the database
	// for wrongly entered passwords
	//
	if (!tfMail.isValid() || !password.isValid()) {
	    return;
	}
	
	String username = tfMail.getValue();
	String password = this.password.getValue();
	
	//
	// Validate username and password with database here. For examples sake
	// I use a dummy username and password.
	//
	boolean isValid = username.equals("test@test.com")
	        && password.equals("passw0rd");
	
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