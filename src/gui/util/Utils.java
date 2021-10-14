package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	public static Stage currentStage(ActionEvent event) {
		//pega o stage que em que o botão event foi acionado
		return (Stage) ( ( (Node) event.getSource() ).getScene().getWindow() ) ;
	}
}
