package com.EyVdeSW.TP.presentacion;

import java.util.List;

import com.EyVdeSW.TP.domainModel.Tag;
import com.EyVdeSW.TP.services.TagService;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;

// Define a sub-window by inheritance
public class SubMenuTagsAsociadosCampaña extends Window {
	private Tree tagTree;
	boolean estado;
	
	
    public SubMenuTagsAsociadosCampaña(Tree tagsAgregadosHastaElMomento, TagService tagService, List<Tag> tagsParaAsociar, HierarchicalContainer tagContainer) {
    	super("Asociar tags a la campaña"); // Set window caption
        center();
        // Disable the close button
        setClosable(false);
        
        tagTree = new TagTree();
    	estado = false;       	
        

		// Add it to the root component
		MyUI.getCurrent().addWindow(this);

		Button cerrar = new Button("Cerrar");
		Button agregar = new Button("Agregar");

		VerticalLayout subContent = new VerticalLayout();
		this.setContent(subContent);

		TagTree.updateTree(tagTree);
		// Put some components in it
		subContent.addComponent(tagTree);

		// subContent.addComponent(comboBoxTag);
		subContent.addComponent(cerrar);
		subContent.addComponent(agregar);
		this.setHeight("400px");
		this.setWidth("500px");
		subContent.setMargin(true);

		cerrar.addClickListener(event -> this.close());

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

				//XXX
				TagTree.updateTree(tagsAgregadosHastaElMomento, tag, tagContainer);

				// En este punto ya se sabe que el tag que se quiere asociar
				// todavia no esta asociado

				List<Tag> hijos = (List<Tag>) tagService.traerHijosDe(tag);

				tagsParaAsociar.add(tag);
				for (Tag t : hijos) {
					tagsParaAsociar.add(t);
				}

				Notification.show("Tags guardados", Type.TRAY_NOTIFICATION);
				this.close();

			}

		});
        
    }
    
    private boolean tagYaAsociado(Tree arbol, String tagSelect) {

		arbol.getContainerDataSource().getItemIds().forEach(item -> {
			Tag tag = ((Tag) item);
			estado = estado || tagSelect.equals(tag.getNombre());

		});

		return estado;
	}
}
