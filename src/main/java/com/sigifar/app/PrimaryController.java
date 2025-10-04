package com.sigifar.app;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;


public class PrimaryController {
@FXML
    private WebView figmaView; // Debe estar anotado con @FXML

    @FXML
    public void initialize() {
        figmaView.getEngine().load(
            "https://embed.figma.com/design/NE4b263KhifylcvCntrcxP/SIGIFAR?node-id=0-1&embed-host=share"
        );
    }
}
