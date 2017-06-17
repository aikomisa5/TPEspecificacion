package com.EyVdeSW.TP.presentacion;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

// Define a sub-window by inheritance
public class SubMenuTagsAsociadosCampaña extends Window {
    public SubMenuTagsAsociadosCampaña() {
        super("Asociar tags a la campaña"); // Set window caption
        center();

        // Disable the close button
        setClosable(false);
       

    }
}
